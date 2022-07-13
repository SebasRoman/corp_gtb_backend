package com.corpgtb.backend.utils.excep;

public class CiudadInvalidaException extends Exception{
    public CiudadInvalidaException() {
    }

    public CiudadInvalidaException(String message) {
        super(message);
    }

    public CiudadInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CiudadInvalidaException(Throwable cause) {
        super(cause);
    }
}
