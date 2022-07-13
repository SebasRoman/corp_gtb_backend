package com.corpgtb.backend.utils.excep;

public class ParroquiaInvalidaException extends Exception{
    public ParroquiaInvalidaException() {
    }

    public ParroquiaInvalidaException(String message) {
        super(message);
    }

    public ParroquiaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParroquiaInvalidaException(Throwable cause) {
        super(cause);
    }
}
