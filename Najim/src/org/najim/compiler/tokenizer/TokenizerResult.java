package org.najim.compiler.tokenizer;

import java.util.Objects;

public class TokenizerResult {
	
	private TokenType type;
	
	private boolean restoreTerminal;
	
	private String errorMessage;
	
	private TokenizerResult(String message) {
		this.errorMessage = Objects.requireNonNull(message);
	}
	
	public TokenizerResult(TokenType type) {
		this(type, false);
	}

	public TokenizerResult(TokenType type, boolean restoreTerminal) {
		this.type = Objects.requireNonNull(type);
		this.restoreTerminal = restoreTerminal;
	}

	public TokenType type() {
		return type;
	}
	
	public boolean restoreTerminal() {
		return restoreTerminal;
	}
	
	public String errorMessage() {
		return errorMessage;
	}
	
	public static TokenizerResult error() {
		return error("An error occurred during tokenization.");
	}
	
	public static TokenizerResult error(String message) {
		return new TokenizerResult(message);
	}
	
}
