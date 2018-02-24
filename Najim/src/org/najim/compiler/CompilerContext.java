package org.najim.compiler;

import java.util.HashMap;
import java.util.Map;

import org.najim.compiler.tokenizer.Tokenizer;

import com.google.common.base.Preconditions;

public class CompilerContext {
	
	private Map<Character, Tokenizer> tokenizers;
	
	public CompilerContext() {
		this.tokenizers = new HashMap<>();
	}
	
	public void registerTokenizer(Tokenizer tokenizer) {
		Preconditions.checkNotNull(tokenizer, "Attempted to register null tokenizer.");
		
		for(Character ch : tokenizer.initialChars()) {
			Preconditions.checkState(!tokenizers.containsKey(ch), "A tokenizer has already been registered to character '" + ch + "'");
			tokenizers.put(ch, tokenizer);
		}
	}
	
	public Tokenizer getTokenizer(char ch) {
		return tokenizers.get(ch);
	}
	
	public Tokenizer getTokenizer(int codepoint) {
		return getTokenizer((char) codepoint);
	}

}

