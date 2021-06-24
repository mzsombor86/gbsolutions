package hu.mzsombor.jobmonitor.dto;

public class PositionResultDto {

	private String title;
	private String location;
	private String url;

	public PositionResultDto(String title, String location, String url) {
		this.title = title;
		this.location = location;
		this.url = url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
