package generator.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Flashcard {

	@EqualsAndHashCode.Include
	private Long deckId;

	@EqualsAndHashCode.Include
	private String front;

	private String back;
}