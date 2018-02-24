package org.najim.compiler.tokenizer.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.najim.compiler.tokenizer.TokenType;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.compiler.tokenizer.TokenizerResult;
import org.najim.util.CharUtils;

public class StringTokenizer extends Tokenizer {
	
	private static final Character[] INITIAL_CHARS = {
		'\'', '"'
	};
	
	/** The character that initiates an escape sequence. */
	private static final char ESCAPE_PREFIX = '\\';
	
	/** The list of valid characters in an escape sequence. */
	private static final Set<Character> VALID_ESCAPE_CHARS = new HashSet<>(Arrays.asList('t', 'b', 'n', 'r', 'f', '\'', '"', '\\'));
	
	/** Determines whether the next character is part of an escape sequence. */
	private boolean isNextCharacterEscaped;
	
	/** The character that terminates the string.*/
	private char terminal;
	
	public StringTokenizer() {
		super(INITIAL_CHARS);
	}
	
	@Override
	protected void resetState() {
		this.isNextCharacterEscaped = false;
		this.terminal = END_OF_FILE;
	}
	
	@Override
	protected TokenizerResult readNextChar(StringBuilder builder, char ch) {
		//End of file was reached before the string was able to terminate.
		if(ch == END_OF_FILE || CharUtils.isLineTerminal(ch)) {			
			return TokenizerResult.error("Line terminated before string could complete.");
		}

		//Add the character to the string.
		builder.append(ch);
		
		if(isNextCharacterEscaped) {
			this.isNextCharacterEscaped = false;
			
			//The next character is not a valid escape character.
			if(!VALID_ESCAPE_CHARS.contains(ch)) {
				return TokenizerResult.error("Invalid escape sequence '\\" + ch + "'.");
			}
		} else if(ch == ESCAPE_PREFIX) {
			this.isNextCharacterEscaped = true;
		} else if(ch == terminal) {
			return new TokenizerResult(TokenType.STRING_LITERAL, false);
		}
		
		//Set the terminal character if it has not already been.
		if(terminal == END_OF_FILE) {
			this.terminal = ch;
		}
		
		//Still parsing the token.
		return null;
	}
	
}
