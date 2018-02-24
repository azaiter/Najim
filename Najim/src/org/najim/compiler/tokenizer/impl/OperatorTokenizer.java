package org.najim.compiler.tokenizer.impl;

import org.najim.compiler.tokenizer.TokenType;
import org.najim.compiler.tokenizer.Tokenizer;
import org.najim.compiler.tokenizer.TokenizerResult;

public class OperatorTokenizer extends Tokenizer {

	private static final Character[] INITIAL_CHARS = {
		'+', '-', '*', '/', '%', '^', '<', '>', '=', '!', '&', '|'
	};
	
	private TokenType type;
	
	public OperatorTokenizer() {
		super(INITIAL_CHARS);
	}
	
	@Override
	protected void resetState() {
		this.type = null;
	}

	@Override
	protected TokenizerResult readNextChar(StringBuilder builder, char ch) {
		if(builder.length() == 0) {
			switch(ch) {
			case '+':
				type = TokenType.OP_ADD;
				break;
			case '-':
				type = TokenType.OP_SUB;
				break;
			case '*':
				type = TokenType.OP_MULT;
				break;
			case '/':
				type = TokenType.OP_DIV;
				break;
			case '%':
				type = TokenType.OP_MOD;
				break;
			case '^':
				type = TokenType.OP_POW;
				break;
			case '&':
				type = TokenType.OP_AND;
				break;
			case '|':
				type = TokenType.OP_OR;
				break;
			case '!':
				type = TokenType.OP_NOT;
				break;
			case '=':
				type = TokenType.OP_ASSIGN;
				break;
			case '<':
				type = TokenType.OP_LESS;
				break;
			case '>':
				type = TokenType.OP_GREATER;
				break;
			default:
				return TokenizerResult.error();
			}
			
			builder.append(ch);
		} else if(builder.length() == 1) {
			if(ch == '=') {
				builder.append(ch);
				
				switch(type) {
				case OP_ADD:
					return new TokenizerResult(TokenType.OP_ADD_ASSIGN);
				case OP_SUB:
					return new TokenizerResult(TokenType.OP_SUB_ASSIGN);
				case OP_MULT:
					return new TokenizerResult(TokenType.OP_MULT_ASSIGN);
				case OP_DIV:
					return new TokenizerResult(TokenType.OP_DIV_ASSIGN);
				case OP_MOD:
					return new TokenizerResult(TokenType.OP_MOD_ASSIGN);
				case OP_POW:
					return new TokenizerResult(TokenType.OP_POW_ASSIGN);
				case OP_AND:
					return new TokenizerResult(TokenType.OP_AND_ASSIGN);
				case OP_OR:
					return new TokenizerResult(TokenType.OP_OR_ASSIGN);
				case OP_ASSIGN:
					return new TokenizerResult(TokenType.OP_EQUAL);
				case OP_NOT:
					return new TokenizerResult(TokenType.OP_NOT_EQUAL);
				case OP_LESS:
					return new TokenizerResult(TokenType.OP_LESS_EQUAL);
				case OP_GREATER:
					return new TokenizerResult(TokenType.OP_GREATER_EQUAL);
				default:
					builder.deleteCharAt(builder.length() - 1);
					break;
				}
			}
			
			return new TokenizerResult(type, true);
		}
		
		return null;
	}
	
}
