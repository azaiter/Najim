package org.najim.compiler.tokenizer;

import java.util.Objects;

public class Token {
	
	private TokenType type;
	
	private String value;
	
	public Token(TokenType type, String value) {
		this.type = Objects.requireNonNull(type);
		this.value = Objects.requireNonNull(value);
	}

	@Override
	public String toString() {
		return "Token: [type=" + type + ", value='" + value + "']";
	}
	
	public TokenType type() {
		return type;
	}
	
	public String value() {
		return value;
	}
	
}
