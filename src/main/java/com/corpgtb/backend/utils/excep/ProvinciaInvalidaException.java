package com.corpgtb.backend.utils.excep;

public class ProvinciaInvalidaException extends Exception {
    public ProvinciaInvalidaException() {
    }

    public ProvinciaInvalidaException(String message) {
        super(message);
    }

    public ProvinciaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProvinciaInvalidaException(Throwable cause) {
        super(cause);
    }
}
