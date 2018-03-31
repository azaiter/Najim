package org.najim.compiler.lexer.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.najim.compiler.lexer.AttributeKey;
import org.najim.compiler.lexer.Lexeme;
import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerException;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;
import org.najim.util.CharUtil;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * <p>An implementation of {@code Lexer} that extracts string literals.</p>
 * 
 * <p>In Najim, strings are wrapped in a pair of either single or double quotes
 * and may contain any characters. </p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class StringLexer extends Lexer {
	
	/**
	 * <p>The character that initializes an escape sequence.</p>
	 */
	private static final char ESCAPE_PREFIX = '\\';
	
	/**
	 * <p>The set of characters that can be used in an escape sequence.</p>
	 */
	private static final Set<Character> ESCAPE_CHARS = new HashSet<>(Lists.charactersOf("tbnrf'\"\\"));
	
	/**
	 * <p>Builds the string literal up as it is being read in.</p> 
	 */
	private StringBuilder builder;
	
	/**
	 * <p>Whether the string is currently in an escape sequence.</p>
	 */
	private boolean isEscaped;
	
	/**
	 * <p>The character that terminates the string.</p>
	 */
	private char terminal;

	/**
	 * <p>Creates a new {@code StringLexer}.</p>
	 */
	public StringLexer() {
		super("'\"");
	}

	@Override
	protected void resetState() {
		this.builder = new StringBuilder();
		this.terminal = CharUtils.NUL;
		this.isEscaped = false;
	}

	@Override
	protected LexerResult readNextChar(char ch) throws LexerException {
		if(CharUtil.isEOF(ch) || CharUtil.isLineTerminal(ch)) {
			throw createTerminationException();
		} else if(terminal == CharUtils.NUL) {
			this.terminal = ch;
		} else if(isEscaped) {
			builder.append(ch);
			
			if(!ESCAPE_CHARS.contains(ch)) {
				throw createInvalidEscapeException(ch);
			}
			isEscaped = false;
		} else if(ch != terminal) {
			builder.append(ch);
			if(ch == ESCAPE_PREFIX) {
				isEscaped = true;
			}
		} else {
			Lexeme lexeme = new Lexeme(Token.STRING_LITERAL);
			lexeme.set(AttributeKey.VALUE, builder.toString());
			
			return new LexerResult(lexeme, false);
		}
		
		return null;
	}
	
	/**
	 * <p>Gets the name of the terminating character.</p>
	 * 
	 * @return The name of the terminating character.
	 */
	private String getTerminalName() {
		switch(terminal) {
		case '\'':
			return "single-quote";
		case '"':
			return "double-quote";
		default:
			return String.valueOf(terminal);
		}
	}
	
	/**
	 * <p>Generates an exception describing an error where the string literal
	 * was not properly closed.</p>
	 * 
	 * @return The {@code LexerException} describing the error.
	 */
	private LexerException createTerminationException() {
		String string = builder.toString();
		
		StringBuilder err = new StringBuilder("String literal was not properly closed by ");
		err.append(getTerminalName()).append('\n');
		err.append("> ").append(terminal).append(string).append('\n');
		err.append(Strings.repeat(" ", 3 + string.length())).append("^");
		
		return new LexerException(err.toString());
	}
	
	/**
	 * <p>Generates an exception describing an error where the string literal
	 * contained an invalid escape sequence.</p>
	 * 
	 * @param ch The escape character that generated the exception.
	 * @return The {@code LexerException} describing the error.
	 */
	private LexerException createInvalidEscapeException(char ch) {
		String string = builder.toString();
		
		StringBuilder err = new StringBuilder("String literal contains invalid escape sequence '\\");
		err.append(ch).append("'\n");
		err.append("> ").append(terminal).append(string).append(" ...").append(terminal).append('\n');
		err.append(Strings.repeat(" ", 1 + string.length())).append("^^");
		
		return new LexerException(err.toString());
	}

}
