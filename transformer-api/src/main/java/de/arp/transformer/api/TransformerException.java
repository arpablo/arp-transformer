/**
 * 
 */
package de.arp.transformer.api;

/**
 * Basic Runtime Exception throw by the microservice Transformer
 * @author arp
 *
 */
public class TransformerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 */
	public TransformerException() {

	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TransformerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public TransformerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 */
	public TransformerException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public TransformerException(Throwable cause) {
		super(cause);
	}
	
}
