package io.github.robotpy.magicbot.exceptions;

public class NoFirstStateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NoFirstStateException(String message) {
		super(message);
	}
}
