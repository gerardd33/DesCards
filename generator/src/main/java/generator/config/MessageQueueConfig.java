package generator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MessageQueueConfig {

	private final MessageQueueProperties properties;

	@Bean
	public Queue queue() {
		return new Queue(properties.getQueueName(), true);
	}

	@Bean
	public Declarables generatorRequestBindings() {
		Queue generatorRequestQueue = new Queue(properties.getQueueName(), true);
		FanoutExchange generatorRequestExchange = new FanoutExchange(properties.getExchangeName());

		return new Declarables(generatorRequestQueue, generatorRequestExchange,
				BindingBuilder.bind(generatorRequestQueue).to(generatorRequestExchange));
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}