package org.najim.compiler.lexer;

/**
 * <p>Represents a position in a source file that can be stepped through.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class LexerPosition {
	
	/**
	 * <p>The line the cursor was previously on.</p>
	 */
	private int prevLine;
	
	/**
	 * <p>The line the cursor is currently on.</p>
	 */
	private int line;
	
	/**
	 * <p>The column the cursor was previously on.</p>
	 */
	private int prevCol;
	
	/**
	 * <p>The column the cursor is currently on.</p>
	 */
	private int col;
	
	/**
	 * <p>Creates a new {@code LexerPosition} instance.</p>
	 */
	public LexerPosition() {
		this.prevLine = 1;
		this.line = 1;
		
		this.prevCol = 0;
		this.col = 0;
	}
	
	public LexerPosition(LexerPosition pos) {
		set(pos);
	}
	
	public void set(LexerPosition pos) {
		this.prevLine = pos.prevLine;
		this.line = pos.line;
		
		this.prevCol = pos.prevCol;
		this.col = pos.col;
	}
	
	/**
	 * <p>Advances the cursor position based on the specified character.</p>
	 * 
	 * @param ch The character to advance by.
	 */
	public void advance(int ch) {
		advance((char) ch);
	}
	
	/**
	 * <p>Advances the cursor position based on the specified character.</p>
	 * 
	 * @param ch The character to advance by.
	 */
	public void advance(char ch) {
		//Ignore the carriage-return character.
		if(ch == '\r') {
			return;
		}
				
		//Save the current position before advancing.
		this.prevCol = col;
		this.prevLine = line;
		
		if(ch == '\n') {
			line++;
			col = 0;
		} else {
			col++;
		}
	}
	
	/**
	 * <p>Rewinds the cursor to it's previous position.</p>
	 */
	public void rewind() {
		this.line = prevLine;
		this.col = prevCol;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("(").append(line).append(':').append(col).append(")").toString();
	}

}
