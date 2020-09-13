package generator.config;

import generator.model.GeneratorRequest;
import generator.service.facade.GeneratorRequestFacade;
import generator.service.impl.GeneratorRequestFacadeImpl;
import generator.util.service.FlashcardCreationRequestDispatcher;
import generator.util.service.GeneratorRequestManager;
import generator.util.service.GoogleSnippetInformationFinder;
import generator.util.service.InformationFinder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Configuration
public class ApplicationConfig {

	private final WebClient flashcardsApi;

	@Bean
	public GeneratorRequestFacade generatorRequestFacade() {
		return new GeneratorRequestFacadeImpl(generatorRequestManager());
	}

	@Bean
	public GeneratorRequestManager generatorRequestManager() {
		return new GeneratorRequestManager(generatorRequest(),
				informationFinder(), flashcardCreationRequestDispatcher());
	}

	@Bean
	public GeneratorRequest generatorRequest() {
		return new GeneratorRequest();
	}

	@Bean
	public InformationFinder informationFinder() {
		return new GoogleSnippetInformationFinder();
	}

	@Bean
	public FlashcardCreationRequestDispatcher flashcardCreationRequestDispatcher() {
		return new FlashcardCreationRequestDispatcher(flashcardsApi);
	}
}
