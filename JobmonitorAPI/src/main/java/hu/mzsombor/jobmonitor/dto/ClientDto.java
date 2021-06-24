package hu.mzsombor.jobmonitor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ClientDto {

	@Size(min=1, max=100)
	private String name;
	
	@NotEmpty
	@Email
	private String email;

	public ClientDto() {
	}

	public ClientDto(@NotEmpty @Size(min = 1, max = 100) String name, @NotEmpty @Email String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
