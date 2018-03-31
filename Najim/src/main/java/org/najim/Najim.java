package org.najim;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.najim.compiler.Compiler;
import org.najim.compiler.CompilerContext;
import org.najim.compiler.lexer.impl.CommentLexer;
import org.najim.compiler.lexer.impl.IdentifierLexer;
import org.najim.compiler.lexer.impl.NumberLexer;
import org.najim.compiler.lexer.impl.OperatorLexer;
import org.najim.compiler.lexer.impl.SingleCharacterLexer;
import org.najim.compiler.lexer.impl.StringLexer;

/**
 * <p>The entry point for the Najim compiler.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class Najim {

	public static void main(String[] args) {		
		CompilerContext context = new CompilerContext();
				
		//Register the lexers to the context.
		context.registerLexer(new SingleCharacterLexer());
		context.registerLexer(new CommentLexer());
		context.registerLexer(new IdentifierLexer());
		context.registerLexer(new OperatorLexer());
		context.registerLexer(new StringLexer());
		context.registerLexer(new NumberLexer());
		
		//Create the compiler and attempt to compile the program.
		Compiler compiler = new Compiler(context);
		try {
			Path dest = Files.createDirectories(Paths.get("out"));
			
			Files.walkFileTree(Paths.get("data"), new SourceFileVisitor(compiler, "nj", dest));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
