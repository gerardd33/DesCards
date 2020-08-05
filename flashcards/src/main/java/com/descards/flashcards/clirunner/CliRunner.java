package com.descards.flashcards.clirunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CliRunner implements CommandLineRunner {

	@Override
	public void run(String... args) {
		System.out.println("Hello world");
	}
}
