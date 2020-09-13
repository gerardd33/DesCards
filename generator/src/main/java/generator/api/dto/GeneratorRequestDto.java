package generator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorRequestDto {

	private Long deckId;

	private String query;

	private List<String> specialFields;

	private String verbosity;
}
