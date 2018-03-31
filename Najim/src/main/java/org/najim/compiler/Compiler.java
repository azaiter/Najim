package org.najim.compiler;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PushbackReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;

import org.najim.compiler.lexer.AttributeKey;
import org.najim.compiler.lexer.Lexeme;
import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerException;
import org.najim.compiler.lexer.LexerPosition;
import org.najim.util.CharUtil;

/**
 * <p>A configurable {@code Compiler} that converts source code into executable code.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class Compiler {
	
	/**
	 * <p>The {@link CompilerContext} that was used to create this {@code Compiler}.
	 */
	private CompilerContext context;
	
	/**
	 * <p>Creates a new {@code Compiler} instance using the specified {@link CompilerContext}.</p>
	 * 
	 * @param context The {@code CompilerContext} used to configure the compiler.
	 */
	public Compiler(CompilerContext context) {
		this.context = checkNotNull(context, "CompilerContext cannot be null.");
	}
		
	/**
	 * <p>Compiles a file containing source code and saves the result in another file.</p>
	 * 
	 * @param in The file containing the source code.
	 * @param out The file to save the compiled program to.
	 * @return {@code true} if compilation was successful, otherwise {@code false}.
	 * @throws IOException If an error occurred while reading from or writing to the streams.
	 */
	public boolean compile(Path in, Path out) throws IOException {
		try(InputStream is = Files.newInputStream(in)) {
			try(OutputStream os = Files.newOutputStream(out)) {
				return compile(is, os);
			}
		}
	}
	
	/**
	 * <p>Compiles a stream of source code and writes the resulting executable to another stream.</p>
	 * 
	 * @param in The stream containing the source code.
	 * @param out The stream to write the resulting executable to.
	 * @return {@code true} if compilation was successful, otherwise {@code false}.
	 * @throws IOException If an error occurred while reading from or writing to the streams.
	 */
	public boolean compile(InputStream in, OutputStream out) throws IOException {
		PrintWriter writer = new PrintWriter(out);
		
		//Tokenize returns null if an error occurred while parsing.
		Queue<Lexeme> lexemes = tokenize(in, writer);
		if(lexemes == null) {
			return false;
		}
		
		while(!lexemes.isEmpty()) {
			writer.println(lexemes.poll());
		}
		writer.flush();
		
		return true;
	}
	
	/**
	 * <p>Converts the incoming source code into a stream of {@link Lexemes}.</p>
	 * 
	 * @param in The stream containing the source code.
	 * @return The {@link Lexeme}s that were extracted from the source code.
	 * @throws IOException If an error occurred while reading input.
	 */
	private Queue<Lexeme> tokenize(InputStream in, PrintWriter out) throws IOException {
		//Tracks the position in the file of each Lexeme as it is read.
		LexerPosition position = new LexerPosition();
		LexerPosition startPosition = new LexerPosition();
		
		//Allows us to push data back into the queue if it is rejected by a lexer.
		PushbackReader reader = new PushbackReader(new InputStreamReader(in));

		//The Lexemes that were extracted from the stream.
		Queue<Lexeme> tokens = new LinkedList<>();
		
		//Extracts the tokens from the stream.
		try {
			Lexeme token = null;
			while((token = readNextToken(reader, position, startPosition)) != null) {
				tokens.add(token);
			}
		} catch(LexerException e) {
			out.print("Error: " + startPosition + ": " + e.getMessage());
			out.flush();
			
			return null;
		}
		
		return tokens;
	}
	
	/**
	 * <p>Extracts a single {@link Lexeme} from a stream.</p>
	 * 
	 * @param reader The input reader.
	 * @param pos Tracks the current position in the source code.
	 * @return The {@code Lexeme} if one could be extracted, or {@code null} if the stream ended.
	 * @throws IOException If an error occurred while reading input.
	 */
	private Lexeme readNextToken(PushbackReader reader, LexerPosition pos, LexerPosition startPos) throws IOException, LexerException {
		//Discard any whitespace and newline characters.
		int next = -1;
		do {
			next = reader.read();
			pos.advance((char) next);
		} while(Character.isWhitespace(next) || CharUtil.isLineTerminal(next));
		
		//End of file encountered, so no token could be read.
		if(CharUtil.isEOF(next)) {
			return null;
		}
		
		//Pushes the most recently read character back into the stream so it can
		//be processed by the lexer.
		reader.unread(next);
		pos.rewind();
		
		//Set the starting position of the lexeme.
		startPos.set(pos);
		
		//Find the Lexer associated with the initial character.
		Lexer lexer = context.getLexer(next);
		checkState(lexer != null, "Unexpected token while parsing: " + (char) next);
		
		//Extract the next lexeme.
		Lexeme lexeme = lexer.lex(reader, pos);
		lexeme.set(AttributeKey.POSITION, new LexerPosition(startPos));
		
		return lexeme;
	}

}
