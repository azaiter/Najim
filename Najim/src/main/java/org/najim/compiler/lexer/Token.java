package org.najim.compiler.lexer;

/**
 * <p>Specifies the different types of tokens that make up a language.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public enum Token {
	
	/**
	 * <p>An opening square bracket.</p>
	 */
	OPEN_BRACKET,
	
	/**
	 * <p>A closing square bracket.</p>
	 */
	CLOSE_BRACKET,
	
	/**
	 * <p>An opening parenthesis.</p>
	 */
	OPEN_PARENTHESIS,
	
	/**
	 * <p>A closing parenthesis.</p>
	 */
	CLOSE_PARENTHESIS,
	
	/**
	 * <p>An opening brace.</p>
	 */
	OPEN_BRACE,
	
	/**
	 * <p>A closing brace.</p>
	 */
	CLOSE_BRACE,
	
	/**
	 * <p>A list separator.</p>
	 */
	LIST_SEPARATOR,
	
	/**
	 * <p>A statement terminal.</p>
	 */
	TERMINAL,
	
	/**
	 * <p>A single-line comment.</p>
	 */
	COMMENT_SINGLE,
	
	/**
	 * <p>A boolean literal.</p>
	 */
	BOOLEAN_LITERAL,
	
	/**
	 * <p>An integer literal.</p>
	 */
	INTEGER_LITERAL,
	
	/**
	 * <p>A real number literal.</p>
	 */
	REAL_LITERAL,
	
	/**
	 * <p>A string literal.</p>
	 */
	STRING_LITERAL,
	
	/**
	 * <p>A boolean type.</p>
	 */
	KEYWORD_BOOLEAN,
	
	/**
	 * <p>An integer type.</p>
	 */
	KEYWORD_INT,
	
	/**
	 * <p>A real number type.</p>
	 */
	KEYWORD_REAL,
	
	/**
	 * <p>A string type.</p>
	 */
	KEYWORD_STRING,
	
	/**
	 * <p>The "if" keyword.</p>
	 */
	KEYWORD_IF,
	
	/**
	 * <p>The "else" keyword.</p>
	 */
	KEYWORD_ELSE,
	
	/**
	 * <p>The "for" keyword.</p>
	 */
	KEYWORD_FOR,
	
	/**
	 * <p>The "while" keyword.</p>
	 */
	KEYWORD_WHILE,
	
	/**
	 * <p>The "break" keyword.</p>
	 */
	KEYWORD_BREAK,
	
	/**
	 * <p>The "continue" keyword.</p>
	 */
	KEYWORD_CONTINUE,
	
	/**
	 * <p>The "function" keyword.</p>
	 */
	KEYWORD_FUNCTION,
	
	/**
	 * <p>The "return" keyword.</p>
	 */
	KEYWORD_RETURN,
	
	/**
	 * <p>An identifier.</p>
	 */
	IDENTIFIER,
	
	/**
	 * <p>The assignment operator.</p>
	 */
	OP_ASSIGN,
	
	/**
	 * <p>The addition operator.</p>
	 */
	OP_ADD,
	
	/**
	 * <p>The compound addition-assignment operator.</p>
	 */
	OP_ADD_ASSIGN,
	
	/**
	 * <p>The subtraction operator.</p>
	 */
	OP_SUB,
	
	/**
	 * <p>The compound subtraction-assignment operator.</p>
	 */
	OP_SUB_ASSIGN,
	
	/**
	 * <p>The multiplication operator.</p>
	 */
	OP_MUL,
	
	/**
	 * <p>The compound multiplication-assignment operator.</p>
	 */
	OP_MUL_ASSIGN,
	
	/**
	 * <p>The division operator.</p>
	 */
	OP_DIV,
	
	/**
	 * <p>The compound division-assignment operator.</p>
	 */
	OP_DIV_ASSIGN,
	
	/**
	 * <p>The modulus operator.</p>
	 */
	OP_MOD,
	
	/**
	 * <p>The compound modulus-assignment operator.</p>
	 */
	OP_MOD_ASSIGN,
	
	/**
	 * <p>The power operator.</p>
	 */
	OP_POW,
	
	/**
	 * <p>The compound power-assignment operator.</p>
	 */
	OP_POW_ASSIGN,
	
	/**
	 * <p>The logical AND operator.</p>
	 */
	OP_AND,
	
	/**
	 * <p>The compound logical AND-assignment operator.</p>
	 */
	OP_AND_ASSIGN,
	
	/**
	 * <p>The logical OR operator.</p>
	 */
	OP_OR,
	
	/**
	 * <p>The compound logical OR-assignment operator.</p>
	 */
	OP_OR_ASSIGN,

	/**
	 * <p>The logical NOT operator.</p>
	 */
	OP_NOT,
	
	/**
	 * <p>The less than operator.</p>
	 */
	OP_LESS,
	
	/**
	 * <p>The less than or equal operator.</p>
	 */
	OP_LESS_EQUAL,
	
	/**
	 * <p>The equality operator.</p>
	 */
	OP_EQUAL,
	
	/**
	 * <p>The inequality operator.</p>
	 */
	OP_NOT_EQUAL,

	/**
	 * <p>The greater than or equal operator.</p>
	 */
	OP_GREATER_EQUAL,
	
	/**
	 * <p>The greater than operator.</p>
	 */
	OP_GREATER,

}
