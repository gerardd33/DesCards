package generator.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestMessageSender implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void run(String... args) {
		rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_NAME, "Hello, world!");
	}
}