package org.najim.compiler.tokenizer;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * @author Brendan
 *
 */
public abstract class Tokenizer {
	
	/** The character indicating that the end of the stream has been reached. */
	public static final char END_OF_FILE = (char) -1;
	
	private Set<Character> initialChars;
	
	public Tokenizer(Character[] initialChars) {
		this.initialChars = new HashSet<>(Arrays.asList(initialChars));
	}
		
	/**
	 * <p>Reads the next token from the specified {@link PushbackReader}.</p>
	 * 
	 * @param in The stream to read the token from.
	 * @return The token that was read.
	 * @throws IOException If an error occurred while reading.
	 */
	public final Token tokenize(PushbackReader in) throws IOException {
		resetState();
		
		StringBuilder bldr = new StringBuilder();
			
		//The most recently read character from the stream.
		int last = 0;
		
		//Continue to read characters until a token can be generated.
		TokenizerResult result = null;
		while(result == null) {
			last = in.read();
			result = readNextChar(bldr, (char) last);
			
			if(result == null && last == -1) {
				System.err.println("Error: Tokenizer did not return result by end of stream.");
			} else if(result != null) {
				if(result.type() == null) {
					System.out.println("Syntax Error: " + result.errorMessage());
				} else if(bldr.length() == 0) {
					System.err.println("Error: Tokenizer produced an empty token.");
				}
			}
		}
		
		//If the token does not use the terminating character, return it to the stream.
		if(result.restoreTerminal()) {
			in.unread(last);
		}

		return new Token(result.type(), bldr.toString());
	}
	
	/**
	 * 
	 */
	protected abstract void resetState();
	
	/**
	 * <p>Reads the next character from the input stream.</p>
	 * 
	 * @param builder The {@link StringBuilder} to buffer the token into.
	 * @param ch The character to read.
	 * @return The {@link TokenizerResult} or {@code null} if 
	 */
	protected abstract TokenizerResult readNextChar(StringBuilder builder, char ch);
	
	
	public final Set<Character> initialChars() {
		return Collections.unmodifiableSet(initialChars);
	}
}

