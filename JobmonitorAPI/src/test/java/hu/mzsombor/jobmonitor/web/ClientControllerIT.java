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
import hu.mzsombor.jobmonitor.repository.ClientRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ClientControllerIT {

	private static final String BASE_URI = "/clients";
	
	@Autowired
	WebTestClient webTestClient;

	@Autowired 
	ClientRepository clientRepository;
	
	@BeforeEach
	public void prepareDB() {
		clientRepository.deleteAll();
	}
	
	@Test
	void testThatWeCanCreateAUser() throws Exception {
		ClientDto clientDto = new ClientDto("abc","a@b.c");
		
		String uuid = webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(String.class)
			.returnResult()
			.getResponseBody();
		
		assertThat(uuid.length()).isEqualTo(36);
	}
	
	@Test
	void testThatADuplicatedEmailThrowsAnError() throws Exception {
		ClientDto clientDto = new ClientDto("abc","a@b.c");
		
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isOk();
		
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isEqualTo(HttpStatus.BAD_REQUEST);
			
	}
	
	@Test
	void testThatAnEmptyNameThrowsAnError() throws Exception {
		ClientDto clientDto = new ClientDto("","a@b.c");
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	@Test
	void testThatAnEmptyEmailThrowsAnError() throws Exception {
		ClientDto clientDto = new ClientDto("abc","");
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isEqualTo(HttpStatus.BAD_REQUEST);	
	}
	
	@Test
	void testThatATooLongNameThrowsAnError() throws Exception {
		ClientDto clientDto = new ClientDto(
				"abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
				"a@b.c");
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	void testThatAnInvalidEmailThrowsAnError() throws Exception {
		ClientDto clientDto = new ClientDto("abc","ab.c");
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(clientDto)
			.exchange()
			.expectStatus()
			.isEqualTo(HttpStatus.BAD_REQUEST);
		
	}
	
	
}
