package hu.mzsombor.jobmonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jobmonitor")
@Component
public class JobmonitorConfigProperties {

	private String baseUrl;
	private String externalApiKey;
	private String externalApiUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getExternalApiKey() {
		return externalApiKey;
	}

	public void setExternalApiKey(String externalApiKey) {
		this.externalApiKey = externalApiKey;
	}

	public String getExternalApiUrl() {
		return externalApiUrl;
	}

	public void setExternalApiUrl(String externalApiUrl) {
		this.externalApiUrl = externalApiUrl;
	}

}
