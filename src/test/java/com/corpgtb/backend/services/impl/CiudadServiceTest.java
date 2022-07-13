package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.repository.ICiudadRepository;
import com.corpgtb.backend.utils.excep.CiudadInvalidaException;
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
public class CiudadServiceTest {

    @Mock
    private ICiudadRepository ciudadRepository;

    @InjectMocks
    private CiudadServiceImpl ciudadServiceImpl;

    @Test
    public void ShouldGetCiudades(){

        List<Ciudad> ciudadList = new ArrayList<Ciudad>();
        ciudadList.add(new Ciudad(1L, "Nombre 1", true, new Provincia()));
        ciudadList.add(new Ciudad(2L, "Nombre 2", true, new Provincia()));
        ciudadList.add(new Ciudad(3L, "Nombre 3", true, new Provincia()));
        when(ciudadRepository.findAll()).thenReturn(ciudadList);

        List<Ciudad> result = ciudadServiceImpl.getCiudades();
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetCiudadById() {
        Ciudad objCiudad = new Ciudad(1L, "Nombre 1", true, new Provincia());
        when(ciudadRepository.getByCiudadId(1L)).thenReturn(objCiudad);

        Ciudad result = ciudadServiceImpl.getCiudadById(1L);

        assertEquals("Nombre 1", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddCiudad() throws CiudadInvalidaException {
        Ciudad objCiudad = new Ciudad(4L, "Nombre 4", true, new Provincia());
        when(ciudadRepository.save(objCiudad)).thenReturn(objCiudad);

        Ciudad result = ciudadServiceImpl.addCiudad(objCiudad);

        assertEquals("Nombre 4", result.getNombre());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldUpdateCiudad() throws CiudadInvalidaException {
        Ciudad objCiudad = new Ciudad(4L, "Nombre 4", true, new Provincia());
        when(ciudadRepository.save(objCiudad)).thenReturn(objCiudad);

        boolean result = ciudadServiceImpl.updateCiudad(objCiudad);

        assertEquals(true, result);
    }
}
