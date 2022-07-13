package com.corpgtb.backend.utils.excep;

public class ArchivoInvalidoException extends Exception{
    public ArchivoInvalidoException() {
    }

    public ArchivoInvalidoException(String message) {
        super(message);
    }

    public ArchivoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArchivoInvalidoException(Throwable cause) {
        super(cause);
    }
}
