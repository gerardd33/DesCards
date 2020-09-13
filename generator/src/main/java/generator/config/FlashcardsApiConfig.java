package generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FlashcardsApiConfig {

	// TODO extract to properties
	@Bean
	public WebClient flashcardsApi() {
		// "http://flashcards-server:8080"
		return WebClient.builder()
				.baseUrl("http://localhost:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
}
