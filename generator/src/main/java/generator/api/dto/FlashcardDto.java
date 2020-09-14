package generator.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FlashcardDto {

	@EqualsAndHashCode.Include
	private String front;

	private String back;
}