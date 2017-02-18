package io.github.robotpy.magicbot.exceptions;

public class MultipleFirstStatesError extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public MultipleFirstStatesError(String message) {
		super(message);
	}
}
