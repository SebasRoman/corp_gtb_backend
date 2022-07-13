package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.dto.UploadFileResponse;
import com.corpgtb.backend.services.IFileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileStorageService fileStorageService;

    @PostMapping("/upload/{id}")
    public RespuestaGenerica uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") String id) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String fecha = df.format(new Date());
        String fileName = fileStorageService.storeFile(file,"files/"+id+"/"+fecha);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(fileName)
                .toUriString();

        return new RespuestaGenerica().armaRespuestaCorrecta(new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()));
    }

    @GetMapping("/download/files/{id}/{fecha}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") String id, @PathVariable("fecha") String fecha , @PathVariable String fileName, HttpServletRequest request) {
        return getFile("files/"+id+"/"+fecha+"/"+fileName, request);
    }

    @DeleteMapping("/files/{id}/{fecha}/{fileName:.+}")
    public RespuestaGenerica deleteFile(@PathVariable("id") String id, @PathVariable("fecha") String fecha, @PathVariable String fileName) {
        try{
            fileStorageService.delete("files/"+id+"/"+fecha+"/"+fileName);
            return new RespuestaGenerica().armaRespuestaCorrecta("OK");
        } catch (Exception ex) {
            return new RespuestaGenerica().armaRespuestaError(500,ex.getLocalizedMessage(), ex );
        }


    }

    private ResponseEntity<Resource> getFile(String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
