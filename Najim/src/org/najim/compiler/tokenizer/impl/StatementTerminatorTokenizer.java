package org.najim.compiler.tokenizer.impl;

import org.najim.compiler.tokenizer.TokenType;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.compiler.tokenizer.TokenizerResult;

public class StatementTerminatorTokenizer extends Tokenizer {

	private static final Character[] INITIAL_CHARS = {
		';'
	};

	public StatementTerminatorTokenizer() {
		super(INITIAL_CHARS);
	}
	
	@Override
	protected void resetState() {
		
	}

	@Override
	protected TokenizerResult readNextChar(StringBuilder builder, char ch) {
		builder.append(ch);
		if(ch == ';') {
			return new TokenizerResult(TokenType.STATEMENT_TERMINAL);
		} else {
			return TokenizerResult.error("Invalid initial character (" + ch + ")");
		}
	}

}
