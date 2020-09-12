package generator.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GeneratorRequest {

	private String query;

	private List<String> specialFields;

	private Verbosity verbosity;
}
