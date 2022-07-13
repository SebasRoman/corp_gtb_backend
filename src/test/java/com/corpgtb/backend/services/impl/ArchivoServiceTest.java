package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Archivo;
import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.repository.IArchivoRepository;
import com.corpgtb.backend.utils.excep.ArchivoInvalidoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ArchivoServiceTest {

    @Mock
    private IArchivoRepository archivoRepository;

    @InjectMocks
    private ArchivoServiceImpl archivoServiceImpl;

    @Test
    public void ShouldGetArchivos(){

        List<Archivo> archivoList = new ArrayList<Archivo>();
        archivoList.add(new Archivo(1L, "Ruta 1", "Descripcion 1", new Date(), true, new Cliente()));
        archivoList.add(new Archivo(2L, "Ruta 2", "Descripcion 2", new Date(), true, new Cliente()));
        archivoList.add(new Archivo(3L, "Ruta 3", "Descripcion 3", new Date(), true, new Cliente()));
        when(archivoRepository.findAll()).thenReturn(archivoList);

        List<Archivo> result = archivoServiceImpl.getArchivos();
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetArchivoById() {
        Archivo objArchivo = new Archivo(1L, "Ruta 1", "Descripcion 1", new Date(), true, new Cliente());
        when(archivoRepository.getByArchivoId(1L)).thenReturn(objArchivo);

        Archivo result = archivoServiceImpl.getArchivoById(1L);

        assertEquals("Ruta 1", result.getRuta());
        assertEquals("Descripcion 1", result.getDescripcion());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddArchivo() throws ArchivoInvalidoException {
        Archivo objArchivo = new Archivo(4L, "Ruta 4", "Descripcion 4", new Date(), true, new Cliente());
        when(archivoRepository.save(objArchivo)).thenReturn(objArchivo);

        Archivo result = archivoServiceImpl.addArchivo(objArchivo);

        assertEquals("Ruta 4", result.getRuta());
        assertEquals("Descripcion 4", result.getDescripcion());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void NoRutaShouldThrowArchivoInvalidoException() {
        Archivo objArchivo = new Archivo(4L, "", "Descripcion 4", new Date(), true, new Cliente());
        when(archivoRepository.save(objArchivo)).thenReturn(objArchivo);
        Exception exception = assertThrows(ArchivoInvalidoException.class, () -> archivoServiceImpl.addArchivo(objArchivo));
    }

    @Test
    public void NoDescripcionShouldThrowArchivoInvalidoException() {
        Archivo objArchivo = new Archivo(4L, "Ruta 4", "", new Date(), true, new Cliente());
        when(archivoRepository.save(objArchivo)).thenReturn(objArchivo);
        Exception exception = assertThrows(ArchivoInvalidoException.class, () -> archivoServiceImpl.addArchivo(objArchivo));
    }

    @Test
    public void ShouldUpdateArchivo() throws ArchivoInvalidoException {
        Archivo objArchivo = new Archivo(4L, "Ruta 4", "Descripcion 4", new Date(), true, new Cliente());
        when(archivoRepository.save(objArchivo)).thenReturn(objArchivo);
        boolean result = archivoServiceImpl.updateArchivo(objArchivo);
        assertEquals(true, result);
    }
}
