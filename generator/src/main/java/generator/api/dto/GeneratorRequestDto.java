package generator.api.dto;

import generator.model.Verbosity;
import lombok.Data;

import java.util.List;

@Data
public class GeneratorRequestDto {

	private String query;

	private List<String> specialFields;

	private String verbosity;
}
