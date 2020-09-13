package generator.config;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
import generator.model.Verbosity;
import generator.util.api.mapper.GeneratorRequestDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class TestMessageSender implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;

	private final MessageQueueProperties messageQueueProperties;

	@Override
	public void run(String... args) {
		GeneratorRequest generatorRequest = GeneratorRequest.builder()
				.deckId(4L)
				.query("Battle of Waterloo")
				.specialFields(Stream.of("Date").collect(Collectors.toList()))
				.verbosity(Verbosity.BRIEF)
				.build();

		GeneratorRequestDto generatorRequestDto = GeneratorRequestDtoMapper.mapToDto(generatorRequest);
		rabbitTemplate.convertAndSend(messageQueueProperties.getQueueName(), generatorRequestDto);
	}
}