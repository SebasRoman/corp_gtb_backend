package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Archivo;
import com.corpgtb.backend.utils.excep.ArchivoInvalidoException;

import java.util.List;

public interface IArchivoService {

    List<Archivo> getArchivos();

    List<Archivo> getArchivoByCliente(Long id, int page, int size, boolean asc);

    Archivo getArchivoById(long archivoId);

    Archivo addArchivo(Archivo archivo) throws ArchivoInvalidoException;

    boolean updateArchivo(Archivo archivo) throws ArchivoInvalidoException;

    long getArhivoCountByCliente(Long id);

}
