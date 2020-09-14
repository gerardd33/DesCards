package generator.service.facade;

import generator.api.dto.GeneratorRequestDto;

public interface GeneratorRequestFacade {

	void processRequest(GeneratorRequestDto generatorRequestDto);
}
