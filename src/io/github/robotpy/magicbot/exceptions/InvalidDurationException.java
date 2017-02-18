package io.github.robotpy.magicbot.exceptions;

public class InvalidDurationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InvalidDurationException(String message) {
		super(message);
	}
}
