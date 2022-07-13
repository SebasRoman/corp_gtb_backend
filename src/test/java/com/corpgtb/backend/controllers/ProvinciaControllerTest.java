package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IProvinciaService;
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
public class ProvinciaControllerTest {

    @InjectMocks
    ProvinciaController provinciaController;

    @Mock
    IProvinciaService provinciaService;

    @Test
    public void ShouldGetAllProvincias() {
        List<Provincia> provinciaList = new ArrayList<Provincia>();
        provinciaList.add(new Provincia(1L, "Provincia 1", true));
        provinciaList.add(new Provincia(2L, "Provincia 2", true));
        provinciaList.add(new Provincia(3L, "Provincia 3", false));

        when(provinciaService.getProvincias()).thenReturn(provinciaList);

        RespuestaGenerica result = provinciaController.getAllProvincias();

        List<Provincia> resultList = (List<Provincia>) result.getObjeto();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList.get(0).getNombre()).isEqualTo("Provincia 1");
        assertThat(resultList.get(2).getEstado()).isEqualTo(false);;
    }

    @Test
    public void ShouldGetProvinciaById() throws Exception {
        Provincia objProvincia = new Provincia(1L, "Provincia 1", true);

        when(provinciaService.getProvinciaById(1L)).thenReturn(objProvincia);

        ResponseEntity<RespuestaGenerica> result = provinciaController.getProvinciaById(1L);

        Provincia resultProvincia = (Provincia) result.getBody().getObjeto();

        assertThat(resultProvincia.getNombre()).isEqualTo("Provincia 1");
        assertThat(resultProvincia.getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldSaveProvincia() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Provincia objProvincia = new Provincia(1L, "Provincia 1", true);

        when(provinciaService.addProvincia(any(Provincia.class))).thenReturn(objProvincia);
        ResponseEntity<RespuestaGenerica> result = provinciaController.saveProvincia(objProvincia);

        Provincia resultProvincia = (Provincia) result.getBody().getObjeto();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(resultProvincia.getNombre()).isEqualTo("Provincia 1");
    }
}
