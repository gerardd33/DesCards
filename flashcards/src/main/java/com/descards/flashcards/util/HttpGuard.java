package com.descards.flashcards.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.concurrent.Callable;

@Slf4j
@UtilityClass
public class HttpGuard {

	public ResponseEntity<?> getResponse(Runnable function) {
		try {
			function.run();
			return ResponseEntity.ok().build();
		} catch (NoSuchElementException exception) {
			log.info("Thrown " + exception.getClass().getName(), exception.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			log.info(exception.getClass().getName(), exception.getMessage());
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}

	public ResponseEntity<?> getResponse(Callable<?> function) {
		try {
			Object result = function.call();
			return ResponseEntity.ok(result);
		} catch (NoSuchElementException exception) {
			log.info("Thrown " + exception.getClass().getName(), exception.getMessage());
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			log.error(exception.getMessage(), exception);
			log.info("Thrown " + exception.getClass().getName(), exception.getMessage());
			return ResponseEntity.badRequest().body(exception.getMessage());
		}
	}
}
