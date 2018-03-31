package org.najim.compiler.lexer;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.najim.util.CharUtil;

import com.google.common.collect.Lists;

/**
 * <p>A {@code Lexer} is responsible for extracting {@link Lexeme}s from a stream
 * of data.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public abstract class Lexer {
	
	/**
	 * <p>The set of characters tokens extracted by this {@code Lexer} start with.</p>
	 */
	private Set<Character> initialChars;
	
	/**
	 * <p>Creates a new {@code Lexer} with the specified initial characters.</p>
	 * 
	 * @param initial The characters that {@code Lexeme}s processed by this {@code Lexer} start with.
	 */
	public Lexer(String initial) {
		checkNotNull(initial, "Initial characters cannot be null.");
		checkArgument(!initial.isEmpty(), "Must specify at least one initial character.");
		
		this.initialChars = Collections.unmodifiableSet(new HashSet<>(Lists.charactersOf(initial)));
	}
	
	/**
	 * <p>Extracts the next {@code Lexeme} from the input stream.</p>
	 * 
	 * @param in The input stream.
	 * @param pos The position of the {@code Lexeme} in the source file.
	 * @return The extracted {@code Lexeme}.
	 * @throws IOException If an error occurred while reading from the stream.
	 */
	public Lexeme lex(PushbackReader in, LexerPosition pos) throws IOException, LexerException {
		checkNotNull(in, "Cannot read from a null reader.");
		
		//Clear the Lexer state back to its default.
		resetState();
		
		//The most recently read character.
		int last = 0;
		
		//Extract the next lexeme.
		LexerResult result = null;
		while(result == null) {
			last = in.read();
			pos.advance((char) last);
			
			result = readNextChar((char) last);
			
			//Ensure that a result was returned by the end of file.
			if(result == null && CharUtil.isEOF(last)) {
				throw new LexerException("Reached end of file unexpectedly.");
			}
		}
		
		//Restore the most recently read character if necessary.
		if(result.restoreTerminal()) {
			in.unread(last);
			pos.rewind();
		}

		return result.lexeme();
	}
	
	/**
	 * <p>Clears any existing state in preparation for extracting a new {@code Lexeme}.</p>
	 */
	protected abstract void resetState();
	
	/**
	 * <p>Processes the next character in the stream.</p>
	 * 
	 * @param ch The next character.
	 * @return The {@code LexerResult} after reading the character, or {@code null}
	 * if the {@code Lexeme} is still incomplete.
	 */
	protected abstract LexerResult readNextChar(char ch) throws LexerException;
	
	/**
	 * <p>Gets the list of characters that {@code Lexeme}s parsed by this {@code Lexer} start with.</p>
	 * 
	 * @return The list of initial characters.
	 */
	public Set<Character> initialCharacters() {
		return initialChars;
	}
	

}
