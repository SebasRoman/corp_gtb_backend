package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.repository.IParroquiaRepository;
import com.corpgtb.backend.utils.excep.ParroquiaInvalidaException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParroquiaServiceTest {

    @Mock
    private IParroquiaRepository parroquiaRepository;

    @InjectMocks
    private ParroquiaServiceImpl parroquiaServiceImpl;

    @Test
    public void ShouldGetParroquias(){

        List<Parroquia> parroquiaList = new ArrayList<Parroquia>();
        parroquiaList.add(new Parroquia(1L, "Nombre 1",  true, new Ciudad()));
        parroquiaList.add(new Parroquia(2L, "Nombre 2",  true, new Ciudad()));
        parroquiaList.add(new Parroquia(3L, "Nombre 3",  true, new Ciudad()));
        when(parroquiaRepository.findByEstado(true)).thenReturn(parroquiaList);

        List<Parroquia> result = parroquiaServiceImpl.getParroquias();
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetParroquiaById() {
        Parroquia objParroquia = new Parroquia(1L, "Nombre 1",  true, new Ciudad());
        when(parroquiaRepository.getByParroquiaId(1L)).thenReturn(objParroquia);

        Parroquia result = parroquiaServiceImpl.getParroquiaById(1L);

        assertEquals("Nombre 1", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddParroquia() throws ParroquiaInvalidaException {
        Parroquia objParroquia = new Parroquia(4L, "Nombre 4",  true, new Ciudad());
        when(parroquiaRepository.save(objParroquia)).thenReturn(objParroquia);

        Parroquia result = parroquiaServiceImpl.addParroquia(objParroquia);

        assertEquals("Nombre 4", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldUpdateParroquia() throws ParroquiaInvalidaException {
        Parroquia objParroquia = new Parroquia(4L, "Nombre 4",  true, new Ciudad());
        when(parroquiaRepository.save(objParroquia)).thenReturn(objParroquia);

        boolean result = parroquiaServiceImpl.updateParroquia(objParroquia);

        assertEquals(true, result);
    }
}
