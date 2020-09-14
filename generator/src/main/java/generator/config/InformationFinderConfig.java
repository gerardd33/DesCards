package generator.config;

import generator.util.service.GoogleSnippetInformationFinder;
import generator.util.service.InformationFinder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@AllArgsConstructor
public class InformationFinderConfig {

	private final InformationFinderProperties informationFinderProperties;

	@Bean
	public InformationFinder informationFinder() {
		return new GoogleSnippetInformationFinder(webDriver());
	}

	@Bean(destroyMethod = "quit")
	public WebDriver webDriver() {
		URL webDriverUrl;
		try {
			webDriverUrl = new URL(informationFinderProperties.getSeleniumAddress());
		} catch (MalformedURLException exception) {
			exception.printStackTrace();
			throw new IllegalStateException();
		}

		return new RemoteWebDriver(webDriverUrl, firefoxOptions());
	}

	@Bean
	public FirefoxOptions firefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);
		return options;
	}
}
