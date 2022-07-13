package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.ICiudadService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CiudadControllerTest {

    @InjectMocks
    CiudadController ciudadController;

    @Mock
    ICiudadService ciudadService;

    @Test
    public void ShouldGetAllCiudades() {
        List<Ciudad> ciudadList = new ArrayList<Ciudad>();
        ciudadList.add(new Ciudad(1L, "Nombre 1", true, new Provincia()));
        ciudadList.add(new Ciudad(2L, "Nombre 2", true, new Provincia()));
        ciudadList.add(new Ciudad(3L, "Nombre 3", false, new Provincia()));

        when(ciudadService.getCiudades()).thenReturn(ciudadList);

        RespuestaGenerica result = ciudadController.getAllCiudades();

        List<Ciudad> resultList = (List<Ciudad>) result.getObjeto();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList.get(0).getNombre()).isEqualTo("Nombre 1");
        assertThat(resultList.get(2).getEstado()).isEqualTo(false);;
    }

    @Test
    public void ShouldGetCiudadById() throws Exception {
        Ciudad objCiudad = new Ciudad(1L, "Nombre 1", true, new Provincia());

        when(ciudadService.getCiudadById(1L)).thenReturn(objCiudad);

        ResponseEntity<RespuestaGenerica> result = ciudadController.getCiudadById(1L);

        Ciudad resultCiudad = (Ciudad) result.getBody().getObjeto();

        assertThat(resultCiudad.getNombre()).isEqualTo("Nombre 1");
        assertThat(resultCiudad.getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldSaveCiudad() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Ciudad objCiudad = new Ciudad(1L, "Nombre 1", true, new Provincia());

        when(ciudadService.addCiudad(any(Ciudad.class))).thenReturn(objCiudad);
        ResponseEntity<RespuestaGenerica> result = ciudadController.saveCiudad(objCiudad);

        Ciudad resultCiudad = (Ciudad) result.getBody().getObjeto();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(resultCiudad.getNombre()).isEqualTo("Nombre 1");
    }
}
