package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.ICiudadService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ciudad")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class CiudadController {

    private ICiudadService ciudadService;
    private static final Logger logger = LoggerFactory.getLogger(CiudadController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public RespuestaGenerica getAllCiudades() {
        try{
            logger.info("Obteniendo todas las ciudades");
            return RespuestaGenerica.armaRespuestaCorrecta(ciudadService.getCiudades());
        } catch (Exception ex) {
            logger.info("Error obteniendo ciudades, mensaje: " + ex.getStackTrace());
            return RespuestaGenerica.armaRespuestaError(500, ex.getLocalizedMessage(),null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCiudadById(@PathVariable("id") long ciudadId) throws Exception {
        try{
            logger.info("Obteniendo ciudad con id: " + ciudadId);
            Ciudad ciudadOpt = ciudadService.getCiudadById(ciudadId);
            if (ciudadOpt == null) {
                throw new Exception("Ciudad no existe");
            }
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(ciudadOpt));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la ciudadId, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveCiudad(@RequestBody Ciudad ciudad) throws Exception {
        try{
            logger.info("Ciudad para guardar: " + ciudad);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(ciudadService.addCiudad(ciudad)));
        } catch (Exception ex) {
            logger.info("Error crear ciudad: " + ciudad + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }
}
