package org.najim.compiler.lexer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>Stores the result of a {@code Lexer} operation.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class LexerResult {
	
	/**
	 * <p>The extracted {@code Lexeme}.</p>
	 */
	private Lexeme lexeme;
	
	/**
	 * <p>Whether the terminating character should be returned to the input stream.</p>
	 */
	private boolean shouldRestoreTerminal;

	/**
	 * <p>Creates a new {@code LexerResult} with the specified token.</p>
	 * 
	 * @param token The token type.
	 * @param restore {@code true} if the terminating character should be
	 * restored to the input stream, otherwise {@code false}.
	 */
	public LexerResult(Token token, boolean restore) {
		this(new Lexeme(token), restore);
	}
	
	/**
	 * <p>Creates a new {@code LexerResult} with the specified lexeme.</p>
	 * 
	 * @param lexeme The {@code Lexeme} that was extracted.
	 * @param restore {@code true} if the terminating character should be
	 * restored to the input stream, otherwise {@code false}.
	 */
	public LexerResult(Lexeme lexeme, boolean restore) {
		this.lexeme = checkNotNull(lexeme, "Lexeme cannot be null.");
		this.shouldRestoreTerminal = restore;
	}
	
	/**
	 * <p>Gets whether the terminal should be restored to the input stream.</p>
	 * 
	 * @return {@code true} if the terminal should be restored.
	 */
	public boolean restoreTerminal() {
		return shouldRestoreTerminal;
	}
	
	/**
	 * <p>Gets the {@code Lexeme} that was extracted.</p>
	 * 
	 * @return The {@code Lexeme} or {@code null} if extraction failed.
	 */
	public Lexeme lexeme() {
		return lexeme;
	}
	
}
