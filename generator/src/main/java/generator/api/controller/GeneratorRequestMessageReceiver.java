package generator.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import generator.api.dto.GeneratorRequestDto;
import generator.service.facade.GeneratorRequestFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Slf4j
@Controller
@AllArgsConstructor
@RabbitListener(queues = "${rabbitmq.generator-request.queue-name}")
public class GeneratorRequestMessageReceiver {

	GeneratorRequestFacade generatorRequestFacade;

	ObjectMapper objectMapper;

	@RabbitHandler
	public void receiveMessage(String message) throws IOException {
		log.info("Received message from Rabbit: " + message);
		GeneratorRequestDto generatorRequestDto = objectMapper
				.readValue(message, GeneratorRequestDto.class);

		log.info("Parsed to: " + generatorRequestDto);
		generatorRequestFacade.processRequest(generatorRequestDto);
	}
}
