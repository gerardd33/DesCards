package generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "rabbitmq.generator-request")
public class RabbitMqProperties {

	private String exchangeName;

	private String queueName;
}
