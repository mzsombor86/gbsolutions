package hu.mzsombor.jobmonitor.dto;

public class ExternalApiJobDto {

	private String jobTitle;
	private String locationName;
	private String jobUrl;

	public ExternalApiJobDto(String jobTitle, String locationName, String jobUrl) {
		this.jobTitle = jobTitle;
		this.locationName = locationName;
		this.jobUrl = jobUrl;
	}

	public ExternalApiJobDto() {

	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getJobUrl() {
		return jobUrl;
	}

	public void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}

}