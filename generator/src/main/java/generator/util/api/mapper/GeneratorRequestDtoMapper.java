package generator.util.api.mapper;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;

public class GeneratorRequestDtoMapper {

	public static GeneratorRequestDto mapToDto(GeneratorRequest generatorRequest) {
		return GeneratorRequestDto.builder()
				.deckId(generatorRequest.getDeckId())
				.query(generatorRequest.getQuery())
				.specialFields(generatorRequest.getSpecialFields())
				.build();
	}

	public static GeneratorRequest mapFromDto(GeneratorRequestDto generatorRequestDto) {
		return GeneratorRequest.builder()
				.deckId(generatorRequestDto.getDeckId())
				.query(generatorRequestDto.getQuery())
				.specialFields(generatorRequestDto.getSpecialFields())
				.build();
	}
}
