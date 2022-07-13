package com.corpgtb.backend.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class DatosSesion implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;




    public String recuperaatributo(String atributo) {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String usuario = request.getSession().getAttribute(atributo).toString();
            return usuario;

        } catch (Exception e) {
            return "No hay dato";
        }
    }

    public String recuperarEmpresa() {

        return recuperaatributo("empresa");
    }

    public String recupearUsuario() {
        return recuperaatributo("usuario");
    }


    public String recuperaUrlRequest() {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getRequestURL().toString();
        } catch (Exception e) {
            return "No hay dato";
        }
    }

    public String recuperaClienteRequest() {

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request.getHeader("User-Agent").toString();
        } catch (Exception e) {
            return "No hay dato";
        }
    }



}
