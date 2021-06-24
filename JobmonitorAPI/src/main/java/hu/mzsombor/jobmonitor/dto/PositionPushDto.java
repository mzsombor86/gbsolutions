package hu.mzsombor.jobmonitor.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PositionPushDto {

	@NotEmpty
	@Size(max = 50)
	private String title;
	@NotEmpty
	@Size(max = 50)
	private String location;

	public PositionPushDto(String title, String location) {
		this.title = title;
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
