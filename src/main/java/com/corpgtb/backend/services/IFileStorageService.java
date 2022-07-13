package com.corpgtb.backend.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    public String storeFile(MultipartFile file, String carpeta);
    public Resource loadFileAsResource(String fileName);
    public void delete(String fileName);
}
