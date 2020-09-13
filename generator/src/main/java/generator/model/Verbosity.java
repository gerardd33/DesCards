package generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Verbosity {

	BRIEF("brief"),
	VERBOSE("verbose");

	private final String apiName;

	public static Verbosity parseApiName(String apiName) {
		return Arrays.stream(Verbosity.values())
				.filter(value -> value.getApiName().equals(apiName))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
