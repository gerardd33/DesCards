package generator.config;

import generator.api.dto.GeneratorRequestDto;
import generator.model.GeneratorRequest;
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
//		sendTestRequests();
	}

	private void sendTestRequests() {
		GeneratorRequest generatorRequest = GeneratorRequest.builder()
				.deckId(1L)
				.query("jeff bezos age")
				.specialFields(Collections.emptyList())
				.build();

		sendRequest(generatorRequest);

		generatorRequest = GeneratorRequest.builder()
				.deckId(1L)
				.query("huayna capac")
				.specialFields(Collections.emptyList())
				.build();

		sendRequest(generatorRequest);
	}

	private void sendRequest(GeneratorRequest generatorRequest) {
		GeneratorRequestDto generatorRequestDto = GeneratorRequestDtoMapper.mapToDto(generatorRequest);
		rabbitTemplate.convertAndSend(messageQueueProperties.getQueueName(), generatorRequestDto);
	}
}