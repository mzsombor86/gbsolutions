package hu.mzsombor.jobmonitor.dto;

import java.util.List;

public class ExternalApiResultsDto {

	private List<ExternalApiJobDto> results;

	public ExternalApiResultsDto(List<ExternalApiJobDto> results) {
		this.results = results;
	}

	public ExternalApiResultsDto() {

	}

	public List<ExternalApiJobDto> getResults() {
		return results;
	}

	public void setResults(List<ExternalApiJobDto> results) {
		this.results = results;
	}

}
