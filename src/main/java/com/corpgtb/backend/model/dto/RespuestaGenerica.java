package com.corpgtb.backend.model.dto;


import com.corpgtb.backend.utils.DatosSesion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RespuestaGenerica {
    int estado;
    String mensaje;
    Object objeto;
    String cliente;

    public static RespuestaGenerica armaRespuestaCorrecta(Object objeto) {
        RespuestaGenerica respuestaGenerica = new RespuestaGenerica();
        respuestaGenerica.setEstado(200);
        respuestaGenerica.setMensaje("OK");
        respuestaGenerica.setObjeto(objeto);
        respuestaGenerica.setCliente(new DatosSesion().recuperaClienteRequest());
        return respuestaGenerica;
    }
    public static  RespuestaGenerica armaRespuestaError(int estado, String mensaje, Object objeto) {
        RespuestaGenerica respuestaGenerica = new RespuestaGenerica();
        respuestaGenerica.setEstado(estado);
        respuestaGenerica.setMensaje(mensaje);
        respuestaGenerica.setObjeto(objeto);
        respuestaGenerica.setCliente(new DatosSesion().recuperaClienteRequest());
        return respuestaGenerica;
    }

}
