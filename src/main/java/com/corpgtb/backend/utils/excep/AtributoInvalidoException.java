package com.corpgtb.backend.utils.excep;

public class AtributoInvalidoException extends Exception{
    private String atributo;
    private Object valor;

    public AtributoInvalidoException(String atributo, Object valor, String string) {
        super(string);
        this.atributo = atributo;
        this.valor = valor;
    }

    public AtributoInvalidoException(String message) {
        super(message);
    }

    public AtributoInvalidoException(String atributo, Object valor, String string, Throwable thrwbl) {
        super(string, thrwbl);
        this.atributo = atributo;
        this.valor = valor;
    }

    public AtributoInvalidoException(String atributo, Object valor, Throwable thrwbl) {
        super(thrwbl);
        this.atributo = atributo;
        this.valor = valor;
    }
}
