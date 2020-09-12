package generator.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	public static final String TOPIC_EXCHANGE_NAME = "generator-request-exchange";

	public static final String QUEUE_NAME = "generator-request-queue";

	@Bean
	public Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}
}