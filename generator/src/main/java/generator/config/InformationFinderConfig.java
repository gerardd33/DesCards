package generator.config;

import generator.util.service.GoogleSnippetInformationFinder;
import generator.util.service.InformationFinder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InformationFinderConfig {

	@Bean
	public InformationFinder informationFinder() {
		return new GoogleSnippetInformationFinder(webDriver());
	}

	@Bean
	public WebDriver webDriver() {
		// TODO try changing to Firefox
		return new ChromeDriver(chromeOptions());
	}

	@Bean
	public ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		//TODO try "--window-size=1920,1200"
		return options;
	}
}
