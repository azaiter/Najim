package org.najim.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PushbackReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.najim.compiler.tokenizer.Token;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.util.CharUtils;

public class Compiler {
	
	private CompilerContext context;
		
	public Compiler(CompilerContext context) {
		this.context = Objects.requireNonNull(context);
	}
	
	public void compile(Path in, Path out) throws IOException {
		try(InputStream is = Files.newInputStream(in)) {
			try(OutputStream os = Files.newOutputStream(out)) {
				compile(is, os);
			}
		}
	}
		
	public void compile(InputStream in, OutputStream out) throws IOException {
		readTokens(in);
	}
	
	private void readTokens(InputStream in) throws IOException {
		PushbackReader reader = new PushbackReader(new InputStreamReader(in));
		
		Token token = null;
		while((token = getNextToken(reader)) != null) {
			if(token.type() == null) {
				System.err.println("Syntax error: " + token.value());
				break;
			} else {
				System.out.println(token);
			}
		}
	}
	
	private Token getNextToken(PushbackReader reader) throws IOException {
		int next = reader.read();
		while(Character.isWhitespace(next) || CharUtils.isLineTerminal(next)) {
			next = reader.read();
		}
				
		if(!CharUtils.isEOF(next)) {
			reader.unread(next);
		} else {
			return null;
		}
		
		Tokenizer tokenizer = context.getTokenizer((char) next);
		if(tokenizer != null) {
			return tokenizer.tokenize(reader);
		}
		
		return null;
	}
	
}