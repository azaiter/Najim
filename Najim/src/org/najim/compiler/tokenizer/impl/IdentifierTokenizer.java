package org.najim.compiler.tokenizer.impl;

import org.najim.compiler.tokenizer.TokenType;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.compiler.tokenizer.TokenizerResult;

import com.google.common.base.Preconditions;

/**
 * <p>Reads in a {@link TokenType#IDENTIFIER} token.</p>
 * 
 * @author Brendan Jones
 *
 */
public class IdentifierTokenizer extends Tokenizer {
	
	private static final Character[] INITIAL_CHARS = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'_'
	};

	public IdentifierTokenizer() {
		super(INITIAL_CHARS);
	}

	@Override
	protected void resetState() { }

	@Override
	protected TokenizerResult readNextChar(StringBuilder builder, char ch) {
		if(!isValidCharacter(builder.length(), ch)) {
			Preconditions.checkState(builder.length() > 0, "Invalid initial character: " + ch);
			
			TokenType type = determineType(builder.toString());
			return new TokenizerResult(type, true);
		}

		builder.append(ch);
		
		return null;
	}
	
	/**
	 * <p>Determines if the specified character is a valid token character.</p>
	 * 
	 * <p><b>Note:</b> An identifier can can only begin with a letter or an
	 * underscore, but may contain digits elsewhere.</p>
	 * 
	 * @param length The length of the token currently.
	 * @param ch The character to check.
	 * @return {@code true} if the token
	 */
	private boolean isValidCharacter(int length, char ch) {
		boolean isValid = Character.isLetter(ch) || ch == '_';
		if(!isValid && length > 0) {
			isValid = Character.isDigit(ch);
		}
		return isValid;
	}
	
	private TokenType determineType(String message) {
		switch(message) {
		case "if":
			return TokenType.KEYWORD_IF;
		case "else":
			return TokenType.KEYWORD_ELSE;
		case "for":
			return TokenType.KEYWORD_FOR;
		case "while":
			return TokenType.KEYWORD_WHILE;
		case "function":
			return TokenType.KEYWORD_FUNCTION;
		case "true":
			return TokenType.KEYWORD_TRUE;
		case "false":
			return TokenType.KEYWORD_FALSE;
		case "bool":
			return TokenType.TYPE_BOOL;
		case "int":
			return TokenType.TYPE_INT;
		case "real":
			return TokenType.TYPE_REAL;
		case "string":
			return TokenType.TYPE_STRING;
		}	
		return TokenType.IDENTIFIER;
	}
	
}
