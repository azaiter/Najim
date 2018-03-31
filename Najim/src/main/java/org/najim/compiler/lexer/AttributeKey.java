package org.najim.compiler.lexer;

/**
 * <p>An {@code AttributeKey} uniquely identifies a {@link Lexeme}'s attributes.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 * @param <T> The type of data associated with the attribute.
 */
public class AttributeKey<T> implements Comparable<AttributeKey<?>> {
	
	/**
	 * <p>The value of a {@code Lexeme}.</p>
	 */
	public static final AttributeKey<String> VALUE = new AttributeKey<>(String.class, "value");
	
	/**
	 * <p>The {@link Token} type of a {@link Lexeme}.</p>
	 */
	public static final AttributeKey<Token> TOKEN = new AttributeKey<>(Token.class, "token");

	/**
	 * <p>The position in the source code that a {@link Lexeme} appeared.</p>
	 */
	public static final AttributeKey<LexerPosition> POSITION = new AttributeKey<>(LexerPosition.class, "position");
	
	/**
	 * <p>The attribute's value type.</p>
	 */
	private Class<T> type;
	
	/**
	 * <p>The name of the attribute.</p>
	 */
	private String name;

	/**
	 * <p>Creates a new {@code AttributeKey} instance.</p>
	 * 
	 * @param type The value type.
	 * @param name The attribute name.
	 */
	private AttributeKey(Class<T> type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * <p>Casts an object to this attribute's type.</p>
	 * 
	 * @param obj The object to cast.
	 * @return The casted object.
	 */
	public T cast(Object obj) {
		return type.cast(obj);
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(AttributeKey<?> other) {
		return name.compareTo(other.name);
	}
	
}


