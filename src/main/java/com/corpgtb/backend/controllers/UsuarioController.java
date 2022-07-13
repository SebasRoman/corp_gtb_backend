package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IUsuarioService;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class UsuarioController {

    private IUsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public RespuestaGenerica getAllUsuarios() {
        try {
            logger.info("Obteniendo todos los usuarios");
            return RespuestaGenerica.armaRespuestaCorrecta(usuarioService.getUsuarios());
        } catch (Exception ex) {
            logger.info("Error obteniendo usuarios, mensaje: " + ex.getStackTrace());
            return RespuestaGenerica.armaRespuestaError(500, ex.getLocalizedMessage(),null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUsuarioById(@PathVariable("id") long usuarioId) throws Exception {
        try {
            logger.info("Obteniendo usuario con id: " + usuarioId);
            Usuario usuarioOpt = usuarioService.getUsuarioById(usuarioId);
            if (usuarioOpt == null) {
                throw new Exception("Usuario no existe");
            }
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(usuarioOpt));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la usuarioId, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveUsuario(@RequestBody Usuario usuario) {
        try {
            logger.info("Usuario para guardar: " + usuario);
            return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica().armaRespuestaCorrecta(usuarioService.addUsuario(usuario)), HttpStatus.OK);
        } catch (UsuarioInvalidoException ex) {
            logger.info("Error crear usuario: " + usuario + ", mensaje: " + ex.getStackTrace());
            return new ResponseEntity<RespuestaGenerica>(new RespuestaGenerica().armaRespuestaError(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
