package generator.util.api.mapper;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
import generator.model.Verbosity;

public class GeneratorRequestDtoMapper {

	public static GeneratorRequestDto mapToDto(GeneratorRequest generatorRequest) {
		return GeneratorRequestDto.builder()
				.deckId(generatorRequest.getDeckId())
				.query(generatorRequest.getQuery())
				.specialFields(generatorRequest.getSpecialFields())
				.verbosity(generatorRequest.getVerbosity().getApiName())
				.build();
	}

	public static GeneratorRequest mapFromDto(GeneratorRequestDto generatorRequestDto) {
		Verbosity verbosity = Verbosity.VERBOSE;
		if (generatorRequestDto.getVerbosity() != null) {
			verbosity = Verbosity.parseApiName(generatorRequestDto.getVerbosity());
		}

		return GeneratorRequest.builder()
				.deckId(generatorRequestDto.getDeckId())
				.query(generatorRequestDto.getQuery())
				.specialFields(generatorRequestDto.getSpecialFields())
				.verbosity(verbosity)
				.build();
	}
}
