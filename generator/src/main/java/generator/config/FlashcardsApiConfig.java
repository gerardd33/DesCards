package generator.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class FlashcardsApiConfig {

	private final FlashcardsApiProperties flashcardsApiProperties;

	@Bean
	public WebClient flashcardsApi() {
		return WebClient.builder()
				.baseUrl(flashcardsApiProperties.getServerAddress())
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}
}
