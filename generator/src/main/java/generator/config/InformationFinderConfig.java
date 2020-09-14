package generator.config;

import generator.util.service.GoogleSnippetInformationFinder;
import generator.util.service.InformationFinder;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
		System.out.println("Creating webdriver bean");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability("headless", true);

		System.out.println(informationFinderProperties);
		System.out.println("Address: " + informationFinderProperties.getSeleniumAddress());
		URL webDriverUrl;
		try {
			webDriverUrl = new URL(informationFinderProperties.getSeleniumAddress());
			System.out.println("jest ok");
		} catch (MalformedURLException exception) {
			System.out.println("wyjebalo");
			exception.printStackTrace();
			throw new IllegalStateException();
		}

		// TODO try firefox

		return new RemoteWebDriver(webDriverUrl, capabilities);
	}
}
