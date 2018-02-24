package org.najim.compiler.tokenizer.impl;

import org.najim.compiler.tokenizer.TokenType;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.compiler.tokenizer.TokenizerResult;

public class BracketTokenizer extends Tokenizer {
	
	private static final Character[] INITIAL_CHARS = {
		'[', ']', '(', ')', '{', '}'
	};
		
	public BracketTokenizer() {
		super(INITIAL_CHARS);
	}

	@Override
	protected void resetState() { }

	@Override
	protected TokenizerResult readNextChar(StringBuilder builder, char ch) {
		builder.append(ch);
		
		switch(ch) {
		case '(':
			return new TokenizerResult(TokenType.OPEN_PARENTHESIS);
		case ')':
			return new TokenizerResult(TokenType.CLOSE_PARENTHESIS);
		case '[':
			return new TokenizerResult(TokenType.OPEN_BRACKET);
		case ']':
			return new TokenizerResult(TokenType.CLOSE_BRACKET);
		case '{':
			return new TokenizerResult(TokenType.OPEN_BRACE);
		case '}':
			return new TokenizerResult(TokenType.CLOSE_BRACE);
		default:
			return TokenizerResult.error("Unexpected character (" + ch + ").");
		}
	}

}
