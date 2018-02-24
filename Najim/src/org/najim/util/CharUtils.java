package org.najim.util;

public class CharUtils {
	
	private static final char END_OF_FILE = (char) -1;
	
	public static boolean isLineTerminal(char ch) {
		return ch == '\r' || ch == '\n';
	}
	
	public static boolean isLineTerminal(int codepoint) {
		return isLineTerminal((char) codepoint);
	}
	
	public static boolean isEOF(char ch) {
		return ch == END_OF_FILE;
	}
	
	public static boolean isEOF(int codepoint) {
		return isEOF((char) codepoint);
	}

}
