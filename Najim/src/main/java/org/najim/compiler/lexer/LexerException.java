package org.najim.compiler.lexer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>Thrown when an error occurs during the {@code lexical analysis} phase of
 * program compilation.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class LexerException extends Exception {

	/**
	 * <p>The unique serialization id for this class.</p>
	 */
	private static final long serialVersionUID = -7841021239369218417L;

	/**
	 * <p>Creates a new {@code LexerException} with the specified detail message.</p>
	 * 
	 * @param message The message.
	 */
	public LexerException(String message) {
		super(checkNotNull(message, "message cannot be null"));
	}

}