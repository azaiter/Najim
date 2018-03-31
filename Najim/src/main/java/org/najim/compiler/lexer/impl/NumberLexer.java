package org.najim.compiler.lexer.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.najim.compiler.lexer.AttributeKey;
import org.najim.compiler.lexer.Lexeme;
import org.najim.compiler.lexer.Lexer;
import org.najim.compiler.lexer.LexerException;
import org.najim.compiler.lexer.LexerResult;
import org.najim.compiler.lexer.Token;

import com.google.common.collect.Lists;

/**
 * <p>An implementation of {@code Lexer} that extracts numbers.</p>
 * 
 * <p>In Najim, numbers can be either integers or real numbers, and integers
 * may be specified in one of four bases:</p>
 * 
 * <table border="1" style="border-collapse:collapse">
 * 	<tr>
 * 	 <th>Name</th>
 *   <th>Base</th>
 *   <th>Prefix</th>
 *   <th>Valid Digits</td>
 * 	</tr>
 * 	<tr>
 *   <td align="center">Binary</td>
 *   <td align="center">2</td>
 *   <td align="center">0b</td>
 *   <td align="center">0,1</td>
 *  </tr>
 *  <tr>
 *   <td align="center">Octal</td>
 *   <td align="center">8</td>
 *   <td align="center">0o</td>
 *   <td align="center">0,1,2,3,4,5,6,7</td>
 *  </tr>
 *  <tr>
 *   <td align="center">Decimal</td>
 *   <td align="center">10</td>
 *   <td align="center">N/A</td>
 *   <td align="center">0,1,2,3,4,5,6,7,8,9</td>
 *  </tr>
 *  <tr>
 *   <td align="center">Hexadecimal</td>
 *   <td align="center">16</td>
 *   <td align="center">0x</td>
 *   <td align="center">0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F</td>
 *  </tr>
 * </table>
 * 
 * <p>Real numbers may only be defined in base-10, and must specify at least
 * one digit before or after the decimal. For example the following are all
 * valid real numbers:</p>
 * 
 * <ul>
 *  <li>0.5</li>
 *  <li>5.0</li>
 *  <li>5.</li>
 *  <li>.5</li>
 * </ul>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class NumberLexer extends Lexer {
	
	/**
	 * <p>Specifies the different types of numbers that are supported.</p>
	 * 
	 * @author Brendan Jones
	 * @author Abdulrahman Zaiter
	 *
	 */
	private static enum NumberType {
		BINARY("01"), OCTAL("01234567"), DECIMAL("0123456789"), HEXADECIMAL("0123456789ABCDEF"), REAL("0123456789");
		
		/**
		 * <p>The set of digits that are allowed in this base.</p>
		 */
		private final Set<Character> digits;
		
		/**
		 * <p>Creates a new {@code NumberType} with the specified set of valid digits.</p>
		 * 
		 * @param digits The valid digits.
		 */
		private NumberType(String digits) {
			this.digits = Collections.unmodifiableSet(new HashSet<>(Lists.charactersOf(digits)));
		}
	}
	
	/**
	 * <p>The type of number being extracted.</p>
	 */
	private NumberType type;
	
	/**
	 * <p>Stores the textual representation of the number.</p>
	 */
	private StringBuilder builder;
	
	/**
	 * <p>Creates a new {@code NumberLexer}.</p>
	 */
	public NumberLexer() {
		super("0123456789.");
	}

	@Override
	protected void resetState() {
		this.type = NumberType.DECIMAL;
		this.builder = new StringBuilder();
	}

	@Override
	protected LexerResult readNextChar(char ch) throws LexerException {
		if(!type.digits.contains(Character.toUpperCase(ch)) && !checkChangeType(ch)) {
			return onParseComplete();
		}
		builder.append(ch);
		return null;
	}
	
	/**
	 * <p>Checks to see whether the specified character causes a change in type.</p>
	 * 
	 * @param ch The character to check.
	 * @return {@code true} if the number type changes, otherwise {@code false}.
	 */
	private boolean checkChangeType(char ch) {
		if(type == NumberType.DECIMAL) {
			if(ch == '.') {
				type = NumberType.REAL;
				return true;
			} else if(builder.length() == 1 && builder.charAt(0) == '0') {
				if(ch == 'b') {
					type = NumberType.BINARY;
				} else if(ch == 'o') {
					type = NumberType.OCTAL;
				} else if(ch == 'x') {
					type = NumberType.HEXADECIMAL;
				} else {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Creates a {@code LexerResult} based on the {@code Lexeme} that was
	 * extracted.</p>
	 * 
	 * <p>If any errors occurred, then an error message will be generated to
	 * inform the user of it. If no error occurred, the number will be converted
	 * to base-10 before setting the {@code Lexeme}'s value.</p>
	 * 
	 * @return The {@code LexerResult}.
	 */
	private LexerResult onParseComplete() throws LexerException {
		Token token = Token.REAL_LITERAL;
		String number = builder.toString();
		if(type != NumberType.REAL) {
			token = Token.INTEGER_LITERAL;
			
			//Convert the number to base-10.
			if(type != NumberType.DECIMAL) {
				if(number.length() <= 2) {
					throw new LexerException(type.name().toLowerCase() + " literal '" + number + "' is not a valid number.");
				}
				number = number.substring(2);
			}
			
			number = String.valueOf(Long.parseLong(number, type.digits.size()));
		} else if(number.length() < 2) {
			throw new LexerException("Invalid real number literal: " + number);
		}
		
		Lexeme lexeme = new Lexeme(token);
		lexeme.set(AttributeKey.VALUE, number);
				
		return new LexerResult(lexeme, true);
	}
	
}
