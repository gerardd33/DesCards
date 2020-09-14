package generator.config;

import generator.model.GeneratorRequest;
import generator.service.facade.GeneratorRequestFacade;
import generator.service.impl.GeneratorRequestFacadeImpl;
import generator.util.service.FlashcardCreationRequestDispatcher;
import generator.util.service.GeneratorRequestManager;
import generator.util.service.GoogleSnippetInformationFinder;
import generator.util.service.InformationFinder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Semaphore;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {

	private final WebClient flashcardsApi;

	private final InformationFinder informationFinder;

	@Bean
	public GeneratorRequestFacade generatorRequestFacade() {
		return new GeneratorRequestFacadeImpl(generatorRequestManager());
	}

	@Bean
	public GeneratorRequestManager generatorRequestManager() {
		return new GeneratorRequestManager(generatorRequest(), informationFinder,
				flashcardCreationRequestDispatcher(), semaphore());
	}

	@Bean
	public GeneratorRequest generatorRequest() {
		return new GeneratorRequest();
	}

	@Bean
	public FlashcardCreationRequestDispatcher flashcardCreationRequestDispatcher() {
		return new FlashcardCreationRequestDispatcher(flashcardsApi);
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Semaphore semaphore() {
		return new Semaphore(1);
	}
}
