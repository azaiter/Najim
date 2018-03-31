package org.najim.compiler.lexer.impl;

import org.najim.compiler.lexer.AttributeKey;
import org.najim.compiler.lexer.Lexeme;
import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;
import org.najim.util.CharUtil;

/**
 * <p>An implementation of {@code Lexer} that extracts comments.</p>
 * 
 * <p>Najim supports only single-line comments which start with the '#' character
 * and terminate when a newline character or end-of-file character has been reached.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class CommentLexer extends Lexer {
	
	/**
	 * <p>Builds the comment string up as the Lexeme is being extracted.</p>
	 */
	private StringBuilder builder;
	
	/**
	 * <p>Creates a new {@code CommentLexer}.</p>
	 */
	public CommentLexer() {
		super("#");
	}
	
	@Override
	protected void resetState() {
		this.builder = new StringBuilder();
	}

	@Override
	protected LexerResult readNextChar(char ch) {
		//Terminate the comment when an EOL or EOF character is encountered.
		if(CharUtil.isEOF(ch) || CharUtil.isLineTerminal(ch)) {
			Lexeme lexeme = new Lexeme(Token.COMMENT_SINGLE);
			lexeme.set(AttributeKey.VALUE, builder.toString());
			
			return new LexerResult(lexeme, false);
		}
		
		//Add the character to the end of the string.
		builder.append(ch);
		
		//Comment has not yet been terminated.
		return null;
	}

}
