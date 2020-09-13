package generator.util.service;

import generator.model.GeneratorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
public class GeneratorRequestManager {

	private GeneratorRequest generatorRequest;

	public void processRequest() {
		System.out.println("Processed: " + generatorRequest);
	}
}
