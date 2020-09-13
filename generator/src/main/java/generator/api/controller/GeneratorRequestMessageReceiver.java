package generator.api.controller;

import generator.api.dto.GeneratorRequestDto;
import generator.service.facade.GeneratorRequestFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
@RabbitListener(queues = "${rabbitmq.generator-request.queue-name}")
public class GeneratorRequestMessageReceiver {

	GeneratorRequestFacade generatorRequestFacade;

	@RabbitHandler
	public void receiveMessage(GeneratorRequestDto message) {
		log.info("Received: " + message);
		generatorRequestFacade.processRequest(message);
	}
}
