package hu.mzsombor.jobmonitor.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.mzsombor.jobmonitor.dto.ClientDto;
import hu.mzsombor.jobmonitor.dto.PositionPushDto;
import hu.mzsombor.jobmonitor.repository.ClientRepository;
import hu.mzsombor.jobmonitor.repository.PositionRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PositionControllerIT {
	private static final String CLIENTS_BASE_URI = "/clients";
	private static final String POSITIONS_BASE_URI = "/positions";
	
	@Autowired
	WebTestClient webTestClient;

	@Autowired 
	ClientRepository clientRepository;
	
	@Autowired 
	PositionRepository positionRepository;
	
	@BeforeEach
	public void prepareDB() {
		clientRepository.deleteAll();
		positionRepository.deleteAll();
	}
	
	public String createClient() {
		ClientDto clientDto = new ClientDto("abc","a@b.c");
		return webTestClient
				.post()
				.uri(CLIENTS_BASE_URI)
				.bodyValue(clientDto)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
	}
	
	@Test
	void testThatWeCanCreateAJob() throws Exception {
		String uuid = createClient();
		PositionPushDto positionDto = new PositionPushDto("Java Developer","London");
		
		String url = webTestClient
				.post()
				.uri(POSITIONS_BASE_URI + "?apiKey=" + uuid)
				.bodyValue(positionDto)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(url.length()).isGreaterThan(10);
		
	}
	
	@Test
	void testThatWeCantCreateAJobWithEmptyTitle() throws Exception {
		String uuid = createClient();
		PositionPushDto positionDto = new PositionPushDto("","London");
		
		webTestClient
				.post()
				.uri(POSITIONS_BASE_URI + "?apiKey=" + uuid)
				.bodyValue(positionDto)
				.exchange()
				.expectStatus()
				.isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	void testThatWeCantCreateAJobWithTooLongTitle() throws Exception {
		String uuid = createClient();
		PositionPushDto positionDto = 
				new PositionPushDto(
						"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
						"London");
		
		webTestClient
				.post()
				.uri(POSITIONS_BASE_URI + "?apiKey=" + uuid)
				.bodyValue(positionDto)
				.exchange()
				.expectStatus()
				.isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	void testThatWeCantCreateAJobWithEmptyLocation() throws Exception {
		String uuid = createClient();
		PositionPushDto positionDto = new PositionPushDto("Java Developer","");
		
		webTestClient
				.post()
				.uri(POSITIONS_BASE_URI + "?apiKey=" + uuid)
				.bodyValue(positionDto)
				.exchange()
				.expectStatus()
				.isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void testThatWeCantCreateAJobWithTooLongLocation() throws Exception {
		String uuid = createClient();
		PositionPushDto positionDto = 
				new PositionPushDto(
						"Java Developer",
						"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
		
		webTestClient
				.post()
				.uri(POSITIONS_BASE_URI + "?apiKey=" + uuid)
				.bodyValue(positionDto)
				.exchange()
				.expectStatus()
				.isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	

}
