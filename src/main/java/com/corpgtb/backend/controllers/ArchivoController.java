package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Archivo;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IArchivoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/archivo")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ArchivoController {

    private IArchivoService archivoService;
    private static final Logger logger = LoggerFactory.getLogger(ArchivoController.class);

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity saveArchivo(@RequestBody Archivo archivo) throws Exception {
        try{
            logger.info("Archivo para guardar: " + archivo);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(archivoService.addArchivo(archivo)));
        } catch (Exception ex) {
            logger.info("Error crear archivo: " + archivo + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getByClienteId(@PathVariable("id") Long id,
                                         @RequestParam(required = false, defaultValue = "0") int page,
                                         @RequestParam(required = false, defaultValue = "0") int size,
                                         @RequestParam(required = false, defaultValue = "true") boolean asc) throws Exception {
        try{
            logger.info(" Listando archivos de cliente: " + id);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(archivoService.getArchivoByCliente(id, page, size, asc)));
        } catch (Exception ex) {
            logger.info("Error al listar archivos del cliente: " + id + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }

    @GetMapping("/count/{id}")
    @ResponseBody
    public ResponseEntity getCountByClienteId(@PathVariable("id") Long id) throws Exception {
        try{
            logger.info(" Contando archivos de cliente: " + id);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(archivoService.getArhivoCountByCliente(id)));
        } catch (Exception ex) {
            logger.info("Error al contar archivos del cliente: " + id + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, ex.getMessage(), null));
        }
    }
}
