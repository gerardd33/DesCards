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
		generatorRequestManager.processRequest(GeneratorRequestDtoMapper.mapFromDto(generatorRequestDto));
	}
}
