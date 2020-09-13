package generator.service.impl;

import generator.api.dto.GeneratorRequestDto;
import generator.service.facade.GeneratorRequestFacade;
import generator.util.api.mapper.GeneratorRequestDtoMapper;
import generator.util.service.GeneratorRequestManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeneratorRequestFacadeImpl implements GeneratorRequestFacade {

	GeneratorRequestManager generatorRequestManager;

	@Override
	public void processRequest(GeneratorRequestDto generatorRequestDto) {
		// TODO add waiting on manager's semaphore while it's busy

		generatorRequestManager.setGeneratorRequest(
				GeneratorRequestDtoMapper.mapFromDto(generatorRequestDto)
		);

		generatorRequestManager.processRequest();
	}
}
