package generator.util.api.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class GeneratorRequestDtoMapperTest {

  @Test
  public void testMapToDto() {
    // given
    GeneratorRequest generatorRequest = GeneratorRequest.builder()
        .deckId(13L)
        .query("some simple query")
        .specialFields(Stream.of("field1", "field2", "field3").collect(Collectors.toList()))
        .build();

    // when
    GeneratorRequestDto generatorRequestDto = GeneratorRequestDtoMapper.mapToDto(generatorRequest);

    //then
    assertEquals(generatorRequest.getDeckId(), generatorRequestDto.getDeckId());
    assertEquals(generatorRequest.getQuery(), generatorRequestDto.getQuery());
    assertEquals(generatorRequest.getSpecialFields(), generatorRequestDto.getSpecialFields());
  }

  @Test
  public void testMapFromDto() {
    // given
    GeneratorRequestDto generatorRequestDto = GeneratorRequestDto.builder()
        .deckId(13L)
        .query("some simple query")
        .specialFields(Stream.of("field1", "field2", "field3").collect(Collectors.toList()))
        .build();

    // when
    GeneratorRequest mappedGeneratorRequest = GeneratorRequestDtoMapper
        .mapFromDto(generatorRequestDto);

    //then
    assertEquals(generatorRequestDto.getDeckId(), mappedGeneratorRequest.getDeckId());
    assertEquals(generatorRequestDto.getQuery(), mappedGeneratorRequest.getQuery());
    assertEquals(generatorRequestDto.getSpecialFields(), mappedGeneratorRequest.getSpecialFields());
  }
}