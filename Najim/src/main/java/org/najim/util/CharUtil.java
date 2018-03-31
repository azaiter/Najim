package org.najim.util;

/**
 * <p>Contains several utility functions for working with characters.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 * 
 */
public class CharUtil {
	
	/**
	 * <p>The character that marks the end of a stream.</p>
	 */
	private static final char END_OF_FILE = (char) -1;
	
	/**
	 * <p>Checks whether the specified character terminates a line.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the character is a line terminal.
	 */
	public static boolean isLineTerminal(char ch) {
		return ch == '\r' || ch == '\n';
	}
	
	/**
	 * <p>Checks whether the specified character terminates a line.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the character is a line terminal.
	 */
	public static boolean isLineTerminal(int ch) {
		return isLineTerminal((char) ch);
	}
	
	/**
	 * <p>Checks whether the specified character marks the end of a stream.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the character ends the stream.
	 */
	public static boolean isEOF(char ch) {
		return ch == END_OF_FILE;
	}
	
	/**
	 * <p>Checks whether the specified character marks the end of a stream.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the character ends the stream.
	 */
	public static boolean isEOF(int ch) {
		return isEOF((char) ch);
	}

}
