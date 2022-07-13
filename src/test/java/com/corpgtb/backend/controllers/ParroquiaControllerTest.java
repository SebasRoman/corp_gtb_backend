package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.services.IParroquiaService;
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
public class ParroquiaControllerTest {

    @InjectMocks
    ParroquiaController parroquiaController;

    @Mock
    IParroquiaService parroquiaService;

    @Test
    public void ShouldGetAllParroquias() {
        List<Parroquia> parroquiaList = new ArrayList<Parroquia>();
        parroquiaList.add(new Parroquia(1L, "Nombre 1",  true, new Ciudad()));
        parroquiaList.add(new Parroquia(2L, "Nombre 2",  true, new Ciudad()));
        parroquiaList.add(new Parroquia(3L, "Nombre 3",  false, new Ciudad()));

        when(parroquiaService.getParroquias()).thenReturn(parroquiaList);

        RespuestaGenerica result = parroquiaController.getAllParroquias();

        List<Parroquia> resultList = (List<Parroquia>) result.getObjeto();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList.get(0).getNombre()).isEqualTo("Nombre 1");
        assertThat(resultList.get(2).getEstado()).isEqualTo(false);;
    }

    @Test
    public void ShouldGetParroquiaById() throws Exception {
        Parroquia objParroquia = new Parroquia(1L, "Nombre 1",  true, new Ciudad());
        when(parroquiaService.getParroquiaById(1L)).thenReturn(objParroquia);

        ResponseEntity<RespuestaGenerica> result = parroquiaController.getParroquiaById(1L);

        Parroquia resultParroquia = (Parroquia) result.getBody().getObjeto();

        assertThat(resultParroquia.getNombre()).isEqualTo("Nombre 1");
        assertThat(resultParroquia.getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldSaveParroquia() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Parroquia objParroquia = new Parroquia(1L, "Nombre 1",  true, new Ciudad());

        when(parroquiaService.addParroquia(any(Parroquia.class))).thenReturn(objParroquia);
        ResponseEntity<RespuestaGenerica> result = parroquiaController.saveParroquia(objParroquia);

        Parroquia resultParroquia = (Parroquia) result.getBody().getObjeto();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(resultParroquia.getNombre()).isEqualTo("Nombre 1");
    }

}
