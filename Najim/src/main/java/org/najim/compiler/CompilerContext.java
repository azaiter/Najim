package org.najim.compiler;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.najim.compiler.lexer.Lexer;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * <p>Represents a configurable pipeline which is used by the {@link Compiler}
 * to determine how it {@code Tokenization}, {@code Parsing}, and 
 * {@code Conversion} should be handled.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class CompilerContext {
	
	/**
	 * <p>Maps each registered Lexer's initial characters to the Lexer.</p>
	 */
	private Map<Character, Lexer> registeredLexers;
			
	/**
	 * <p>Creates a new {@code CompilerContext}.</p>
	 */
	public CompilerContext() {
		this.registeredLexers = new HashMap<>();
	}
	
	/**
	 * <p>Registers a {@link Lexer} to this {@code CompilerContext}.</p>
	 * 
	 * @param lexer The {@code Lexer} to register.
	 */
	public void registerLexer(Lexer lexer) {
		checkNotNull(lexer, "Attempted to register a null Lexer.");
	
		Set<Character> currentChars = registeredLexers.keySet();
		Set<Character> newChars = lexer.initialCharacters();

		//Ensure that no two Lexers share initialization characters.
		SetView<Character> duplicates = Sets.intersection(currentChars, newChars);
		checkState(duplicates.isEmpty(), "A lexer has already been registered for the characters: %s", duplicates);
		
		//There are no conflicting characters, so we can safely register the lexer.
		newChars.forEach(ch -> registeredLexers.put(ch, lexer));
	}
	
	/**
	 * <p>Retrieves the {@link Lexer} that is registered to the specified character.</p>
	 * 
	 * @param ch The character.
	 * @return The registered {@code Lexer} if there is one, or {@code null}.
	 */
	public Lexer getLexer(char ch) {
		return registeredLexers.get(ch);
	}
	
	/**
	 * <p>Retrieves the {@link Lexer} that is registered to the specified character.</p>
	 * 
	 * @param ch The character.
	 * @return The registered {@code Lexer} if there is one, or {@code null}.
	 */
	public Lexer getLexer(int ch) {
		return getLexer((char) ch);
	}
	
}
