package com.exception;

/**
 * SupplyChainException is a custom checked exception used across all modules
 * for domain-specific validation and processing errors.
 */
public class SupplyChainException extends Exception {

    private static final long serialVersionUID = 1L;

    public SupplyChainException(String message) {
        super(message);
    }

    public SupplyChainException(String message, Throwable cause) {
        super(message, cause);
    }
}