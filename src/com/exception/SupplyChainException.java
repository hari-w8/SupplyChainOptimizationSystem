package com.exception;

/**
 * EXCEPTION CLASS: SupplyChainException
 *
 * A custom checked exception used throughout all modules.
 * Instead of throwing generic exceptions (like RuntimeException),
 * we throw this with meaningful messages for domain-specific errors.
 *
 * USAGE EXAMPLES:
 *   throw new SupplyChainException("Invalid Product ID: " + productId);
 *   throw new SupplyChainException("Insufficient stock for product: " + productId);
 *   throw new SupplyChainException("Order cannot be cancelled in status: " + status);
 */
public class SupplyChainException extends Exception {

    // serialVersionUID is required for serializable classes (best practice for Exception subclasses)
    private static final long serialVersionUID = 1L;

    /**
     * Constructor that accepts a custom error message.
     * @param message – descriptive explanation of what went wrong
     */
    public SupplyChainException(String message) {
        super(message);  // passes the message to the parent Exception class
    }

    /**
     * Constructor that wraps another exception (useful for chaining exceptions).
     * @param message – descriptive explanation
     * @param cause   – the original exception that triggered this one
     */
    public SupplyChainException(String message, Throwable cause) {
        super(message, cause);
    }
}
