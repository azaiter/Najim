package org.najim.compiler.lexer.impl;

import org.najim.compiler.lexer.AttributeKey;
import org.najim.compiler.lexer.Lexeme;
import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;

/**
 * <p>An implementation of {@code Lexer} that extracts identifiers and reserved keywords.</p>
 * 
 * <p>In Najim, an identifier is any series of characters that begins with either
 * a letter or an underscore, and contains only alphanumeric characters and
 * underscores. Certain identifiers are reserved by the language and have
 * special meaning. These are extracted in the same way, but have a different
 * {@link Token} than other identifiers.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class IdentifierLexer extends Lexer {

	/**
	 * <p>Builds the {@code Lexeme}s name up as it is being extracted.</p>
	 */
	private StringBuilder builder;
	
	/**
	 * <p>Creates a new {@code IdentifierLexer}.</p>
	 */
	public IdentifierLexer() {
		super("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_");
	}

	@Override
	protected void resetState() {
		this.builder = new StringBuilder();
	}

	@Override
	protected LexerResult readNextChar(char ch) {
		if(isValidCharacter(ch)) {
			builder.append(ch);
		} else {
			return new LexerResult(createLexeme(), true);
		}
		return null;
	}
	
	/**
	 * <p>Checks whether the specified character is a valid next character
	 * for the identifier.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the character is a valid identifier character.
	 */
	private boolean isValidCharacter(char ch) {
		boolean isValid = Character.isLetter(ch) || ch == '_';
		if(!isValid && builder.length() > 0) {
			isValid = Character.isDigit(ch);
		}
		return isValid;
	}
	
	/**
	 * <p>Creates a {@code Lexeme} whose {@code Token} is dependent on the extracted text.</p>
	 * 
	 * @return The {@code Lexeme} that was extracted.
	 */
	private Lexeme createLexeme() {
		String text = builder.toString();
		switch(text) {
		case "if":
			return new Lexeme(Token.KEYWORD_IF);
		case "else":
			return new Lexeme(Token.KEYWORD_ELSE);
		case "for":
			return new Lexeme(Token.KEYWORD_FOR);
		case "while":
			return new Lexeme(Token.KEYWORD_WHILE);
		case "function":
			return new Lexeme(Token.KEYWORD_FUNCTION);
		case "return":
			return new Lexeme(Token.KEYWORD_RETURN);
		case "string":
			return new Lexeme(Token.KEYWORD_STRING);
		case "bool":
			return new Lexeme(Token.KEYWORD_BOOLEAN);
		case "int":
			return new Lexeme(Token.KEYWORD_INT);
		case "real":
			return new Lexeme(Token.KEYWORD_REAL);
		case "true":
		case "false":
			return new Lexeme(Token.BOOLEAN_LITERAL).set(AttributeKey.VALUE, text);
		default:
			return new Lexeme(Token.IDENTIFIER).set(AttributeKey.VALUE, text);
		}
	}

}
