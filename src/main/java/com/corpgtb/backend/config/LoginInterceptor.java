package com.corpgtb.backend.config;

import com.corpgtb.backend.model.dto.JWTUser;
import com.corpgtb.backend.utils.JWTTokenUtil;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String nuevoToken = "";
        String usuario = "";
        String cedula = "";
        String ip = "";
        String cliente = "";
        JWTTokenUtil utilitarioToken = new JWTTokenUtil();
        JWTUser usuarioToken = null;
        String recurso = request.getRequestURL().toString();
        String token = "";
        // si es metodo options es un request de tipo pre flight, debe ser aceptada
        // pues este tipo de resquest no tienen la cabecera token ni ejecuta
        // el codigo del rest
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }

        try {
            Object headers = request.getHeaderNames();
            token = request.getHeader("Authorization").toString();
            //if (!token.equals("noToken")){
            if(token.startsWith("Bearer")) {
                token = token.substring(7);
                if (utilitarioToken.validateToken(token)) {
                    // si el token es válido
                    nuevoToken = utilitarioToken.refreshToken(token);
                    usuario = utilitarioToken.getUsernameFromToken(token);
                    cliente = request.getHeader("User-Agent").toString();
                    ip = request.getHeader("X-FORWARDED-FOR");
                    if (ip == null || "".equals(ip)) {
                        ip = request.getRemoteAddr();
                    }
                    recurso = request.getRequestURL().toString();
                    request.getSession().setAttribute("usuario", usuario);
                    request.getSession().setAttribute("cliente", cliente);
                    request.getSession().setAttribute("ip", ip);
                    request.getSession().setAttribute("url", recurso);
                    System.out.println(" recurso solicitado: " + recurso);
                    request.getSession().setAttribute("token", nuevoToken);
                    response.setHeader("Access-Control-Expose-Headers", "token, Content-Disposition");
                    response.setHeader("token", nuevoToken);
                } else {
                    // el token no es válidp

                    response.setStatus(403);
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Token no válido");
                    //response.addHeader("Access-Control-Allow-Origin", "*");
                    return false;
                }
            } else {
                // el token no es válidp

                response.setStatus(403);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Token no válido");
                //response.addHeader("Access-Control-Allow-Origin", "*");
                return false;
            }

        } catch (Exception e) {
            // si no hay token

            response.setStatus(403);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Token no válido");
            //response.addHeader("Access-Control-Allow-Origin", "*");
            return false;
        }

        return true;
    }


}
