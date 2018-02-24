package org.najim.util;

import org.najim.compiler.tokenizer.TokenType;

public class OperatorUtils {
	
	/**
	 * <p>Determines whether a {@code TokenType} is classified as a relational operator.</p>
	 * 
	 * <p><b>The following {@code TokenType}s are relational operators:</b></p>
	 * <ul>
	 *  <li>{@link TokenType#OP_LESS}</li>
	 * 	<li>{@link TokenType#OP_LESS_EQUAL}</li>
	 * 	<li>{@link TokenType#OP_EQUAL}</li>
	 * 	<li>{@link TokenType#OP_GREATER_EQUAL}</li>
	 * 	<li>{@link TokenType#OP_GREATER}</li>
	 * </ul>
	 * 
	 * @param type The {@code TokenType} to check.
	 * @return {@code true} if {@code type} is a relational operator.
	 */
	public static boolean isRelational(TokenType type) {
		return type == TokenType.OP_LESS
			|| type == TokenType.OP_LESS_EQUAL
			|| type == TokenType.OP_EQUAL
			|| type == TokenType.OP_GREATER_EQUAL
			|| type == TokenType.OP_GREATER;
	}
	
	/**
	 * <p>Determines whether a {@code TokenType} is classified as a logical operator.</p>
	 * 
	 * @param type The {@code TokenType} to check.
	 * @return {@code true} if {@code type} is a logical operator.
	 */
	public static boolean isLogical(TokenType type) {
		return type == TokenType.OP_AND
			|| type == TokenType.OP_OR
			|| type == TokenType.OP_NOT;
	}
	
	/**
	 * <p>Determines whether a that is classified as an
	 * assignment operator.</p>
	 * 
	 * <p><b>The following {@code TokenType}s are assignment operators:</b></p>
	 * <ul>
	 * 	<li>{@link TokenType#OP_ASSIGN}</li>
	 * 	<li>{@link TokenType#OP_ADD_ASSIGN}</li>
	 * 	<li>{@link TokenType#OP_SUB_ASSIGN}</li>
	 * 	<li>{@link TokenType#OP_MULT_ASSIGN}</li>
	 * 	<li>{@link TokenType#OP_DIV_ASSIGN}</li>
	 * 	<li>{@link TokenType#OP_MOD_ASSIGN}</li>
	 *  <li>{@link TokenType#OP_POW_ASSIGN}</li>
	 * </ul>
	 * @param type The {@code TokenType} to check.
	 * @return {@code true} if {@code type} is arithmetic.
	 */
	public static boolean isAssignment(TokenType type) {
		return type == TokenType.OP_ASSIGN
			|| type == TokenType.OP_ADD_ASSIGN
			|| type == TokenType.OP_SUB_ASSIGN
			|| type == TokenType.OP_MULT_ASSIGN
			|| type == TokenType.OP_DIV_ASSIGN
			|| type == TokenType.OP_MOD_ASSIGN
			|| type == TokenType.OP_POW_ASSIGN;
	}
	
	/**
	 * <p>Returns true for any {@code TokenType} that is classified as an
	 * arithmetic operator.</p>
	 * 
	 * <p><b>The following {@code TokenType}s are arithmetic operators:</b></p>
	 * <ul>
	 * 	<li>{@link TokenType#OP_ADD}</li>
	 * 	<li>{@link TokenType#OP_SUB}</li>
	 * 	<li>{@link TokenType#OP_MULT}</li>
	 * 	<li>{@link TokenType#OP_DIV}</li>
	 * 	<li>{@link TokenType#OP_MOD}</li>
	 *  <li>{@link TokenType#OP_POW}</li>
	 * </ul>
	 * @param type The {@code TokenType} to check.
	 * @return {@code true} if {@code type} is arithmetic.
	 */
	public static boolean isArithmetic(TokenType type) {
		return type == TokenType.OP_ADD
			|| type == TokenType.OP_SUB
			|| type == TokenType.OP_MULT
			|| type == TokenType.OP_DIV
			|| type == TokenType.OP_MOD
			|| type == TokenType.OP_POW;
	}

}
