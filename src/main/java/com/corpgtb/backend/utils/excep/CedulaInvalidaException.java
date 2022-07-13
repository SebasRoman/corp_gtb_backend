package com.corpgtb.backend.utils.excep;

public class CedulaInvalidaException extends Exception{

    public CedulaInvalidaException(String message) {
        super(message);
    }

    public CedulaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}

