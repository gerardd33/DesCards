package generator.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MessageQueueConfig {

	private final MessageQueueProperties properties;

	@Bean
	public Queue queue() {
		return new Queue(properties.getQueueName(), false);
	}

	@Bean
	public Declarables generatorRequestBindings() {
		Queue generatorRequestQueue = new Queue(properties.getQueueName(), false);
		FanoutExchange generatorRequestExchange = new FanoutExchange(properties.getExchangeName());

		return new Declarables(generatorRequestQueue, generatorRequestExchange,
				BindingBuilder.bind(generatorRequestQueue).to(generatorRequestExchange));
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}