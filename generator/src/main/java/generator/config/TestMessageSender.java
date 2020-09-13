package generator.config;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
import generator.model.Verbosity;
import generator.util.api.mapper.GeneratorRequestDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class TestMessageSender implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;

	private final MessageQueueProperties messageQueueProperties;

	@Override
	public void run(String... args) {
//		sendTestRequest();
	}

	private void sendTestRequest() {
		GeneratorRequest generatorRequest = GeneratorRequest.builder()
				.deckId(1L)
				.query("Battle of the Somme")
				.specialFields(Collections.singletonList("Date"))
				.verbosity(Verbosity.BRIEF)
				.build();

		GeneratorRequestDto generatorRequestDto = GeneratorRequestDtoMapper.mapToDto(generatorRequest);
		rabbitTemplate.convertAndSend(messageQueueProperties.getQueueName(), generatorRequestDto);
	}
}