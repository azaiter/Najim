package org.najim.compiler.lexer.impl;

import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerException;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;

/**
 * <p>An implementation of {@code Lexer} that extracts operators.</p>
 */
public class OperatorLexer extends Lexer {

	/**
	 * <p>The {@code Token} of the extracted operator.</p>
	 */
	private Token token;
	
	/**
	 * <p>Creates a new {@code OperatorLexer}</p>
	 */
	public OperatorLexer() {
		super("+-*/%^!&|=<>");
	}

	@Override
	protected void resetState() {
		this.token = null;
	}

	@Override
	protected LexerResult readNextChar(char ch) throws LexerException {
		if(token == null) {
			return readFirstCharacter(ch);
		} else {
			return readSecondCharacter(ch);
		}
	}
	
	/**
	 * <p>Determines the {@code Token} based on the first operator character.</p>
	 * 
	 * @param ch The character.
	 * @return The {@code LexerResult} if there was an error, otherwise {@code null}.
	 */
	private LexerResult readFirstCharacter(char ch) throws LexerException {
		switch(ch) {
		case '+':
			this.token = Token.OP_ADD;
			return null;
		case '-':
			this.token = Token.OP_SUB;
			return null;
		case '*':
			this.token = Token.OP_MUL;
			return null;
		case '/':
			this.token = Token.OP_DIV;
			return null;
		case '%':
			this.token = Token.OP_MOD;
			return null;
		case '^':
			this.token = Token.OP_POW;
			return null;
		case '!':
			this.token = Token.OP_NOT;
			return null;
		case '&':
			this.token = Token.OP_AND;
			return null;
		case '|':
			this.token = Token.OP_OR;
			return null;
		case '=':
			this.token = Token.OP_ASSIGN;
			return null;
		case '<':
			this.token = Token.OP_LESS;
			return null;
		case '>':
			this.token = Token.OP_EQUAL;
			return null;
		default:
			throw new LexerException("Unexpected first operator character: " + ch);
		}
		
	}
	
	/**
	 * <p>Determines the operator's {@code Token} based on the second character.</p>
	 * 
	 * @param ch The character.
	 * @return The {@code LexerResult} after reading the character.
	 */
	private LexerResult readSecondCharacter(char ch) {
		if(ch == '=') {
			switch(token) {
			case OP_ADD:
				return new LexerResult(Token.OP_ADD_ASSIGN, false);
			case OP_SUB:
				return new LexerResult(Token.OP_ADD_ASSIGN, false);
			case OP_MUL:
				return new LexerResult(Token.OP_ADD_ASSIGN, false);
			case OP_DIV:
				return new LexerResult(Token.OP_ADD_ASSIGN, false);
			case OP_MOD:
				return new LexerResult(Token.OP_MOD_ASSIGN, false);
			case OP_POW:
				return new LexerResult(Token.OP_POW_ASSIGN, false);
			case OP_NOT:
				return new LexerResult(Token.OP_NOT_EQUAL, false);
			case OP_AND:
				return new LexerResult(Token.OP_AND_ASSIGN, false);
			case OP_OR:
				return new LexerResult(Token.OP_OR_ASSIGN, false);
			case OP_ASSIGN:
				return new LexerResult(Token.OP_EQUAL, false);
			case OP_LESS:
				return new LexerResult(Token.OP_LESS_EQUAL, false);
			case OP_GREATER:
				return new LexerResult(Token.OP_GREATER_EQUAL, false);
			default:
				break;
			}
		}
		return new LexerResult(token, true);
	}
	
}
