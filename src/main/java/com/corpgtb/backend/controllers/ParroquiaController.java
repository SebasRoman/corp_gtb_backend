package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IParroquiaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parroquia")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ParroquiaController {

    private IParroquiaService parroquiaService;
    private static final Logger logger = LoggerFactory.getLogger(ParroquiaController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public RespuestaGenerica getAllParroquias() {
        try {
            logger.info("Obteniendo todas las parroquias");
            return RespuestaGenerica.armaRespuestaCorrecta(parroquiaService.getParroquias());
        } catch (Exception ex) {
            logger.info("Error obteniendo parroquias, mensaje: " + ex.getStackTrace());
            return RespuestaGenerica.armaRespuestaError(500, ex.getLocalizedMessage(),null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getParroquiaById(@PathVariable("id") long parroquiaId) throws Exception {
        try {
            logger.info("Obteniendo parroquia con id: " + parroquiaId);
            Parroquia parroquiaOpt = parroquiaService.getParroquiaById(parroquiaId);
            if (parroquiaOpt == null) {
                throw new Exception("Provincia no existe");
            }
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(parroquiaOpt));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la parroquiaId, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveParroquia(@RequestBody Parroquia parroquia) throws Exception {
        try {
            logger.info("Parroquia para guardar: " + parroquia);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(parroquiaService.addParroquia(parroquia)));
        } catch (Exception ex) {
            logger.info("Error crear parroquia: " + parroquia + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }
}
