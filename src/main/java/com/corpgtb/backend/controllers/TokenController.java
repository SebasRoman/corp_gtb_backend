package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.dto.UsuarioDto;
import com.corpgtb.backend.services.IUsuarioService;
import com.corpgtb.backend.utils.JWTTokenUtil;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TokenController {

    IUsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @PostMapping("/getToken")
    @ResponseBody
    public ResponseEntity<RespuestaGenerica> getToken(@RequestBody UsuarioDto usuarioDto){
        try {
            logger.info("Obteniendo token");
            return new ResponseEntity<RespuestaGenerica>(RespuestaGenerica.armaRespuestaCorrecta(usuarioService.getToken(usuarioDto.getUsername(),usuarioDto.getPassword())), HttpStatus.OK);
        } catch (UsuarioInvalidoException ex) {
            logger.error("Error Obteniendo token: " + ex.getMessage());
            return new ResponseEntity<RespuestaGenerica>(RespuestaGenerica.armaRespuestaError(401,ex.getLocalizedMessage(),null),HttpStatus.OK);
        }
    }

    @GetMapping("/validateToken")
    @ResponseBody
    public RespuestaGenerica validateToken(@RequestParam("token") String token){
        return new RespuestaGenerica().armaRespuestaCorrecta(new JWTTokenUtil().validateToken(token));
    }


    @GetMapping("/refreshToken")
    @ResponseBody
    public RespuestaGenerica refreshToken(@RequestParam("token") String token){
        return new RespuestaGenerica().armaRespuestaCorrecta(new JWTTokenUtil().refreshToken(token));
    }
}
