package com.corpgtb.backend.utils.excep;

public class UsuarioInvalidoException extends Exception{
    public UsuarioInvalidoException() {
    }

    public UsuarioInvalidoException(String message) {
        super(message);
    }

    public UsuarioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioInvalidoException(Throwable cause) {
        super(cause);
    }
}
