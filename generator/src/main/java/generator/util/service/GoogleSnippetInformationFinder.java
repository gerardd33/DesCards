package generator.util.service;

import generator.model.GeneratorRequest;
import generator.model.Verbosity;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class GoogleSnippetInformationFinder implements InformationFinder {

	private static final String GOOGLE_SEARCH_URL = "http://google.com/search?hl=en&q=";

	WebDriver webDriver;

	@Override
	public String findInformation(GeneratorRequest generatorRequest) {
//		return "no back yet";
		return getGoogleSnippet(generatorRequest);
	}

	private String getGoogleSnippet(GeneratorRequest generatorRequest) {
		String googleQuery = generatorRequest.getQuery() + " " +
				String.join(" ", generatorRequest.getSpecialFields());

		System.out.println("google query: " + googleQuery);
		String answer = "Could not find the answer";
		try {
			webDriver.get(GOOGLE_SEARCH_URL + googleQuery);
			// TODO extract constants
			List<WebElement> elements = new ArrayList<>();
			if (generatorRequest.getVerbosity() == Verbosity.BRIEF) {
				elements = webDriver.findElements(By.xpath(
						"//div[starts-with(@data-attrid,\"kc:\")]"));
			}

			if (elements.isEmpty()) {
				elements = webDriver.findElements(By.cssSelector(
						"div[data-attrid=\"description\"] span"));
			}

			if (elements.isEmpty()) {
				elements = webDriver.findElements(By.cssSelector(
						"#rso div[data-attrid=\"wa:/description\"]"));
			}

			System.out.println("2" + elements);
			elements.forEach(element -> System.out.println(element.getText()));
			elements.forEach(element -> System.out.println(element.getAttribute("textContent")));

			if (!elements.isEmpty()) {
				answer = elements.stream()
						.map(element -> element.getAttribute("textContent"))
						.findFirst()
						.orElse("Could not find the answer");
			}
		} finally {
			webDriver.quit();
		}

		System.out.println("answer: <" + answer + ">");
		return answer;
	}
}
