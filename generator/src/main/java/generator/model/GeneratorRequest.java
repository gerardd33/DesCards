package generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorRequest {

	private Long deckId;

	private String query;

	private List<String> specialFields;

	private Verbosity verbosity;
}
