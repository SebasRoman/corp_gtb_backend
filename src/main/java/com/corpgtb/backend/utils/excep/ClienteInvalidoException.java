package com.corpgtb.backend.utils.excep;

public class ClienteInvalidoException extends Exception{
    public ClienteInvalidoException() {
    }

    public ClienteInvalidoException(String message) {
        super(message);
    }

    public ClienteInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteInvalidoException(Throwable cause) {
        super(cause);
    }
}
