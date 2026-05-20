package com.exception;

<<<<<<< HEAD

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
=======
public class SupplyChainException extends Exception {

    public SupplyChainException(String message) {
        super(message);
>>>>>>> branch 'master' of https://github.com/hari-w8/SupplyChainOptimizationSystem.git
    }
}