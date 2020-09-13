package generator.config;

import generator.model.GeneratorRequest;
import generator.service.facade.GeneratorRequestFacade;
import generator.service.impl.GeneratorRequestFacadeImpl;
import generator.util.service.GeneratorRequestManager;
import org.springframework.cglib.core.KeyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	GeneratorRequestFacade generatorRequestFacade() {
		return new GeneratorRequestFacadeImpl(generatorRequestManager());
	}

	@Bean
	GeneratorRequestManager generatorRequestManager() {
		return new GeneratorRequestManager(generatorRequest());
	}

	@Bean
	GeneratorRequest generatorRequest() {
		return new GeneratorRequest();
	}
}
