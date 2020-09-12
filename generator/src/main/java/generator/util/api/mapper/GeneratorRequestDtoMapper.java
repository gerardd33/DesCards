package generator.util.api.mapper;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
import generator.model.Verbosity;

public class GeneratorRequestDtoMapper {

	public static GeneratorRequest mapFromDto(GeneratorRequestDto generatorRequestDto) {
		Verbosity verbosity = Verbosity.BRIEF;
		if (generatorRequestDto.getVerbosity() != null) {
			Verbosity.parseApiName(generatorRequestDto.getVerbosity());
		}

		return GeneratorRequest.builder()
				.query(generatorRequestDto.getQuery())
				.specialFields(generatorRequestDto.getSpecialFields())
				.verbosity(verbosity)
				.build();
	}
}
