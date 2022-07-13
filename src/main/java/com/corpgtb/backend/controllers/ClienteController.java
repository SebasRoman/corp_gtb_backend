package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IClienteService;
import com.corpgtb.backend.utils.DatosSesion;
import com.corpgtb.backend.utils.ShaUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

    IClienteService clienteService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/")
    @ResponseBody RespuestaGenerica getAllClientes(@RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "0") int size,
                                                   @RequestParam(required = false, defaultValue = "") String sort,
                                                   @RequestParam(required = false, defaultValue = "true") boolean asc){
        try{
            logger.info("Obteniendo todos los clientes");
            return RespuestaGenerica.armaRespuestaCorrecta(clienteService.getClientes(page, size, sort, asc));
        } catch (Exception ex) {
            logger.info("Error obteniendo clientes, mensaje: " + ex.getStackTrace());
            return RespuestaGenerica.armaRespuestaError(500, ex.getLocalizedMessage(),null);
        }
    }

    @GetMapping("/getByKey/{key}")
    @ResponseBody
    ResponseEntity getByKey(@PathVariable("key") String clave) {
        try{
            logger.info("Obteniendo por la claveL:" + clave);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.buscarClaveBusqueda(clave)));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la clave, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @GetMapping("/getByIdentification/{identification}")
    @ResponseBody
    ResponseEntity getByIdentification(@PathVariable("identification") String identificacion) {
        try{
            logger.info("Obteniendo por la identificacion:" + identificacion);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.findByIdentificacion(identificacion)));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la identificacion, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @GetMapping("/getByUsuario")
    @ResponseBody
    ResponseEntity getByUsuario() {
        try{
            logger.info("Obteniendo por usuario:" + new DatosSesion().recupearUsuario());
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.getClienteByUsuario()));
        } catch (Exception ex) {
            logger.info("Error obteniendo por la identificacion, mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity count() {
        try{
            logger.info("Obteniendo contador:");
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.getClienteCount()));
        } catch (Exception ex) {
            logger.info("Error contador: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, null, ex.getLocalizedMessage()));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity saveCliente(@RequestBody Cliente cliente) {
        try{
            logger.info("Codificando password..: " + cliente.getEmail());
            cliente.getUsuarioId().setPassword(ShaUtils.codificarTexto(cliente.getUsuarioId().getPassword()));
            logger.info("Creando cliente: " + cliente.getEmail());
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.addCliente(cliente)));
        } catch (Exception ex) {
            logger.info("Error crear cliente: " + cliente + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, "Error crear cliente, mensaje: " + ex.getMessage(), null));
        }
    }

    @PatchMapping("/update")
    @ResponseBody
    public ResponseEntity updateCliente(@RequestBody Cliente cliente) {
        try{
            logger.info("Actualizando cliente: " + cliente);
            return ResponseEntity.ok(RespuestaGenerica.armaRespuestaCorrecta(clienteService.updateCliente(cliente)));
        } catch (Exception ex) {
            logger.info("Error actualizar cliente: " + cliente + ", mensaje: " + ex.getStackTrace());
            return ResponseEntity.internalServerError().body(RespuestaGenerica.armaRespuestaError(500, "Error actualizar cliente, mensaje: " + ex.getMessage(), null));
        }
    }
}
