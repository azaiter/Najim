package org.najim;

import java.nio.file.Paths;

import org.najim.compiler.Compiler;
import org.najim.compiler.CompilerContext;
import org.najim.compiler.tokenizer.impl.BracketTokenizer;
import org.najim.compiler.tokenizer.impl.IdentifierTokenizer;
import org.najim.compiler.tokenizer.impl.OperatorTokenizer;
import org.najim.compiler.tokenizer.impl.StatementTerminatorTokenizer;
import org.najim.compiler.tokenizer.impl.StringTokenizer;

public class Najim {
	
	public static void main(String[] args) throws Exception {		
		CompilerContext context = new CompilerContext();
		context.registerTokenizer(new BracketTokenizer());
		context.registerTokenizer(new IdentifierTokenizer());
		context.registerTokenizer(new OperatorTokenizer());
		context.registerTokenizer(new StatementTerminatorTokenizer());
		context.registerTokenizer(new StringTokenizer());
				
		Compiler compiler = new Compiler(context);
		compiler.compile(Paths.get("test.nj"), Paths.get("test.java"));
	}

}

