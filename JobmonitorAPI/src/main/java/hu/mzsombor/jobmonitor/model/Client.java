package hu.mzsombor.jobmonitor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue
	long id;

	private String name;

	private String email;

	private String uuid;

	public Client() {
	}

	public Client(long id, String name, String email, String uuid) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.uuid = uuid;
	}

	public Client(String name, String email, String uuid) {
		this.name = name;
		this.email = email;
		this.uuid = uuid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
