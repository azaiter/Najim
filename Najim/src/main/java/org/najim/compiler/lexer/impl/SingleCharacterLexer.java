package org.najim.compiler.lexer.impl;

import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerException;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;

/**
 * <p>An implementation of {@code Lexer} that extracts single miscellaneous
 * single-character symbols that don't fit into any other category.</p>
 * 
 * <p>These include:</p>
 * <ul>
 * 	<li><strong>Square brackets</strong>: [ ]</li>
 *  <li><strong>Curly braces</strong>: { }</li>
 *  <li><strong>Parentheses</strong>: ( )</li>
 *  <li><strong>Comma Separators</strong>: ,</li>
 *  <li><strong>Statement Terminals</strong>: ;</li>
 * </ul>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class SingleCharacterLexer extends Lexer {

	/**
	 * <p>Creates a new {@code SingleCharacterLexer}.</p>
	 */
	public SingleCharacterLexer() {
		super("{}[]();,");
	}

	@Override
	protected void resetState() {
		//Nothing to do.
	}

	@Override
	protected LexerResult readNextChar(char ch) throws LexerException {
		switch(ch) {
		case '{':
			return new LexerResult(Token.OPEN_BRACE, false);
		case '}':
			return new LexerResult(Token.CLOSE_BRACE, false);
		case '[':
			return new LexerResult(Token.OPEN_BRACKET, false);
		case ']':
			return new LexerResult(Token.CLOSE_BRACKET, false);
		case '(':
			return new LexerResult(Token.OPEN_PARENTHESIS, false);
		case ')':
			return new LexerResult(Token.CLOSE_PARENTHESIS, false);
		case ';':
			return new LexerResult(Token.TERMINAL, false);
		case ',':
			return new LexerResult(Token.LIST_SEPARATOR, false);
		default:
			throw new LexerException("Invalid character: " + ch);
		}
	}

}
