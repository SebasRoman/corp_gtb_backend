package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IProvinciaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/provincia")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "*")
public class ProvinciaController {

    private IProvinciaService provinciaService;
    private static final Logger logger = LoggerFactory.getLogger(ProvinciaController.class);

    @GetMapping("/")
    @ResponseBody
    public RespuestaGenerica getAllProvincias() {
        try {
            logger.info("Obteniendo todas las provincias");
            return RespuestaGenerica.armaRespuestaCorrecta(provinciaService.getProvincias());
        } catch (Exception ex) {
            logger.info("Error obteniendo provincias, mensaje: " + ex.getStackTrace());
            return RespuestaGenerica.armaRespuestaError(500, ex.getLocalizedMessage(),null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getProvinciaById(@PathVariable("id") long provinciaId) throws Exception {
        try {
            logger.info("Obteniendo provincia con id: " + provinciaId);
            Provincia provinciaOpt = provinciaService.getProvinciaById(provinciaId);
            if (provinciaOpt == null) {
                throw new Exception("Provincia no existe");
            }
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(provinciaOpt));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la provinciaId, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity saveProvincia(@RequestBody Provincia provincia) throws Exception {
        try {
            logger.info("Provincia para guardar: " + provincia);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(provinciaService.addProvincia(provincia)));
        } catch (Exception ex) {
            logger.info("Error crear provincia: " + provincia + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }
}
