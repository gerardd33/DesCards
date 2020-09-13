package generator.api.controller;

import generator.api.dto.GeneratorRequestDto;
import generator.service.facade.GeneratorRequestFacade;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@RabbitListener(queues = "${rabbitmq.generator-request.queue-name}")
public class GeneratorRequestMessageReceiver {

	GeneratorRequestFacade generatorRequestFacade;

	@RabbitHandler
	public void receiveMessage(GeneratorRequestDto message) {
		System.out.println("Received:\n" + message);
		generatorRequestFacade.processRequest(message);
	}
}
