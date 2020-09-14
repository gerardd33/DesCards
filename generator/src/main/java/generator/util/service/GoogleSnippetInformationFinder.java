package generator.util.service;

import generator.model.GeneratorRequest;
import generator.model.Verbosity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class GoogleSnippetInformationFinder implements InformationFinder {

	private static final String GOOGLE_SEARCH_URL = "http://google.com/search?hl=en&q=";

	private static final String BRIEF_ANSWER_WINDOW_XPATH = "//div[starts-with(@data-attrid,\"kc:\")]";

	private static final String ANSWER_WINDOW_SELECTOR = "div[data-attrid=\"description\"] span";

	private static final String BIO_WINDOW_SELECTOR = "#rso div[data-attrid=\"wa:/description\"]";

	private static final String NO_ANSWER_MESSAGE = "Could not find the answer";

	private final WebDriver webDriver;

	@Override
	public String findInformation(GeneratorRequest generatorRequest) {
		return getGoogleSnippet(generatorRequest);
	}

	private String getGoogleSnippet(GeneratorRequest generatorRequest) {
		String googleQuery = generatorRequest.getQuery() + " " +
				String.join(" ", generatorRequest.getSpecialFields());

		log.info("Searching in google: " + googleQuery);
		String answer;
		try {
			webDriver.get(GOOGLE_SEARCH_URL + googleQuery);
			List<WebElement> elements = new ArrayList<>();
			if (generatorRequest.getVerbosity().equals(Verbosity.BRIEF)) {
				elements = webDriver.findElements(By.xpath(BRIEF_ANSWER_WINDOW_XPATH));
			}

			if (elements.isEmpty()) {
				elements = webDriver.findElements(By.cssSelector(ANSWER_WINDOW_SELECTOR));
				if (elements.isEmpty()) {
					elements = webDriver.findElements(By.cssSelector(BIO_WINDOW_SELECTOR));
				}
			}

			answer = elements.stream()
					.map(element -> element.getAttribute("textContent"))
					.findFirst()
					.orElse(NO_ANSWER_MESSAGE);
		} finally {
			webDriver.quit();
		}

		log.info("Found answer: <" + answer + ">");
		return answer;
	}
}
