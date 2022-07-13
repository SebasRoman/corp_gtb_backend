package com.corpgtb.backend.services.impl;
import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.repository.IProvinciaRepository;
import com.corpgtb.backend.utils.excep.ProvinciaInvalidaException;
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
public class ProvinciaServiceTest {

    @Mock
    private IProvinciaRepository provinciaRepository;

    @InjectMocks
    private ProvinciaServiceImpl provinciaServiceImpl;

    @Test
    public void ShouldGetProvincias(){

        List<Provincia> provinciaList = new ArrayList<Provincia>();
        provinciaList.add(new Provincia(1L, "Provincia 1", true));
        provinciaList.add(new Provincia(2L, "Provincia 2", true));
        provinciaList.add(new Provincia(3L, "Provincia 3", false));
        when(provinciaRepository.findByEstado(true)).thenReturn(provinciaList);

        List<Provincia> result = provinciaServiceImpl.getProvincias();
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetProvinciaById() {
        Provincia objProvincia = new Provincia(1L, "Provincia 1", true);
        when(provinciaRepository.getByProvinciaId(1L)).thenReturn(objProvincia);

        Provincia result = provinciaServiceImpl.getProvinciaById(1L);

        assertEquals("Provincia 1", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddProvincia() throws ProvinciaInvalidaException {
        Provincia objProvincia = new Provincia(4L, "Provincia 4", true);
        when(provinciaRepository.save(objProvincia)).thenReturn(objProvincia);

        Provincia result = provinciaServiceImpl.addProvincia(objProvincia);

        assertEquals("Provincia 4", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldUpdateProvincia() throws ProvinciaInvalidaException {
        Provincia objProvincia = new Provincia(4L, "Provincia 4", true);
        when(provinciaRepository.save(objProvincia)).thenReturn(objProvincia);

        boolean result = provinciaServiceImpl.updateProvincia(objProvincia);

        assertEquals(true, result);
    }
}
