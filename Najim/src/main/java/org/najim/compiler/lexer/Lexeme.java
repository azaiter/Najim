package org.najim.compiler.lexer;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

/**
 * <p>Represents a single element of a source file. Each {@code Lexeme} can have
 * a number of attributes attached to it.3</p>
 * 
 * @author Brendan Jones
 *
 */

/**
 * <p>
 * Represents a single element within a source file.
 * </p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class Lexeme {

	/**
	 * <p>
	 * The attributes of this {@code Lexeme}.
	 * </p>
	 */
	private Map<AttributeKey<?>, Object> attributes;

	/**
	 * <p>
	 * Creates a new {@code Lexeme} with the specified token type.
	 * </p>
	 * 
	 * @param token
	 *            The token.
	 */
	public Lexeme(Token token) {
		this.attributes = new TreeMap<>();
		attributes.put(AttributeKey.TOKEN, checkNotNull(token, "Token cannot be null."));
	}

	/**
	 * <p>
	 * Sets the value for an attribute.
	 * </p>
	 * 
	 * @param key
	 *            The attribute to set.
	 * @param value
	 *            The value to set the attribute to.
	 * @return {@code this} Lexeme.
	 */
	public <T> Lexeme set(AttributeKey<T> key, T value) {
		checkNotNull(key, "Attribute key cannot be null.");

		if (value == null) {
			attributes.remove(key);
		} else {
			attributes.put(key, value);
		}
		
		return this;
	}

	/**
	 * <p>
	 * Gets the value for an attribute.
	 * </p>
	 * 
	 * @param key
	 *            The attribute to get.
	 * @return The value associated with the attribute, or {@code null} if this
	 *         {@code Lexeme} does not have the attribute.
	 */
	public <T> T get(AttributeKey<T> key) {
		checkNotNull(key, "Attribute key cannot be null.");
		return key.cast(attributes.get(key));
	}

	/**
	 * <p>
	 * Gets whether this {@code Lexeme} has the specified attribute.
	 * </p>
	 * 
	 * @param key
	 *            The attribute to check for.
	 * @return {@code true} if the attribute exists, otherwise {@code false}.
	 */
	public <T> boolean has(AttributeKey<T> key) {
		checkNotNull(key, "Attribute key cannot be null.");

		return attributes.containsKey(key);
	}

	@Override
	public String toString() {
		ToStringHelper helper = MoreObjects.toStringHelper(this);
		for (Entry<AttributeKey<?>, Object> attrib : attributes.entrySet()) {
			helper.add(attrib.getKey().toString(), "'" + attrib.getValue() + "'");
		}
		return helper.toString();
	}

}
