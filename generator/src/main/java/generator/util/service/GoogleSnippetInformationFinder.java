package generator.util.service;

import generator.model.GeneratorRequest;

public class GoogleSnippetInformationFinder implements InformationFinder {

	@Override
	public String findInformation(GeneratorRequest generatorRequest) {
		return "no back yet generated for query: <<" + generatorRequest.getQuery() + ">>";
	}
}
