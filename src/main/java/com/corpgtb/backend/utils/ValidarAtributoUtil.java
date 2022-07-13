package com.corpgtb.backend.utils;

import com.corpgtb.backend.utils.excep.AtributoInvalidoException;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarAtributoUtil {
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static void validarStringNuloVacio(String nombre,String valor) throws AtributoInvalidoException
    {
        if (valor==null )
            throw new AtributoInvalidoException(nombre,valor,"VALOR DE ATRIBUTO "+nombre+" NO PUEDE SER VALOR NULO");
        else if (valor.trim().length() ==0 )
            throw new AtributoInvalidoException(nombre,valor,"VALOR DE ATRIBUTO "+nombre+"  NO PUEDE ESTAR VACIO");

    }
    public static void validarFechaValida(String nombre, Date fecha) throws AtributoInvalidoException
    {
        Date d = new Date();
        if (fecha==null)
            throw new AtributoInvalidoException(nombre,fecha,"VALOR DE ATRIBUTO "+nombre+"  NO PUEDE ESTAR VACIO");
        else if(fecha.compareTo(d)>0)
            throw new AtributoInvalidoException(nombre,fecha,"VALOR DE ATRIBUTO "+nombre+"  NO PUEDE SER MAYOR QUE LA FECHA ACTUAL");
    }
    public static void validarObjetoNulo(String nombre, Object objeto) throws AtributoInvalidoException
    {
        if (objeto == null)
            throw new AtributoInvalidoException(nombre,objeto,"VALOR DE ATRIBUTO "+nombre+" NO PUEDE SER VALOR NULO");
    }
    public static void validarDoubleNegativo(String nombre,double valor) throws AtributoInvalidoException
    {
        if (valor <0)
            throw new AtributoInvalidoException(nombre,valor,"VALOR DE ATRIBUTO "+nombre+" NO PUEDE SER NEGATIVO");
    }
    public static void validarDoubleCero(String nombre,double valor) throws AtributoInvalidoException
    {
        if (valor  <= 0)
            throw new AtributoInvalidoException(nombre,valor,"VALOR DE ATRIBUTO "+nombre+" NO PUEDE SER NEGATIVO NI CERO");
    }

    public static void validarFecha(String nombre,Date fechaIngreso, Date fechaEntrega) throws AtributoInvalidoException
    {
        if (fechaIngreso.compareTo(fechaEntrega)>0)
            throw new AtributoInvalidoException(nombre,fechaIngreso,"FECHA DE INGRESO"+fechaIngreso.getDate()+" NO PUEDE SER MAYOR QUE LA FECHA DE ENTREGA "+fechaEntrega.getDate());
    }

    public static void validarEmail(String email) throws AtributoInvalidoException {
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new AtributoInvalidoException("EMAIL INCORRECTO");
        }
    }
}
