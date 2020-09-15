package generator.util.service;

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

	private static final String EMPTY_FIELD = "";

	private final WebDriver webDriver;

	@Override
	public String findInformation(String query) {
		return getGoogleSnippet(query);
	}

	@Override
	public String findInformation(String query, String specialField) {
		return getGoogleSnippet(query, specialField);
	}

	private String getGoogleSnippet(String query) {
		return getGoogleSnippet(query, EMPTY_FIELD);
	}

	private String getGoogleSnippet(String query, String specialField) {
		String googleQuery = query + " " + specialField;
		log.info("Searching in google: " + googleQuery);

		String answer;
		webDriver.get(GOOGLE_SEARCH_URL + googleQuery);
		List<WebElement> elements = new ArrayList<>();
		if (!specialField.equals(EMPTY_FIELD)) {
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

		log.info("Found answer: " + answer);
		return answer;
	}
}
