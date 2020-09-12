package generator.api.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
@RabbitListener(queues = "${rabbitmq.generator-request.queue-name}")
public class GeneratorRequestMessageReceiver {

	@RabbitHandler
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
