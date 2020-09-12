package generator.api.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
public class GeneratorRequestMessageReceiver {

	// TODO extract queue etc names to application.properties
	@RabbitListener(queues = "generator-request-queue")
	public void receiveMessage(String message) {
		System.out.println("Received <" + message + ">");
	}
}
