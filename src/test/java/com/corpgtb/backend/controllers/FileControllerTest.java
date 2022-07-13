package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.dto.UploadFileResponse;
import com.corpgtb.backend.services.IFileStorageService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class FileControllerTest {

    @InjectMocks
    FileController fileController;

    @Mock
    IFileStorageService fileStorageService;

    @Mock
    ServletUriComponentsBuilder servletUriComponentsBuilder;

    byte[] content = null;

    @Test
    public void ShouldUploadFile() {

        MultipartFile mockFile = new MockMultipartFile("mockFileName",
                "mockFileName", "text/plain", content);
        when(fileStorageService.storeFile(any(MultipartFile.class),"files")).thenReturn("mockFileName");

        RespuestaGenerica result = fileController.uploadFile(mockFile,"0000000000");

        UploadFileResponse resultList = (UploadFileResponse) result.getObjeto();

        assertThat(resultList.getFileDownloadUri()).isEqualTo("/file/download/mockFileName");
    }
}
