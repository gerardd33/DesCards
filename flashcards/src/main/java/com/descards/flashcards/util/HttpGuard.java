package com.descards.flashcards.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Callable;

@Slf4j
public class HttpGuard {

	public static ResponseEntity<?> getResponse(Runnable function) {
		try {
			function.run();
			return ResponseEntity.ok().build();
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}

	public static ResponseEntity<?> getResponse(Callable function) {
		try {
			var result = function.call();
			return ResponseEntity.ok(result);
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
}
