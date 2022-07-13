package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.utils.excep.CiudadInvalidaException;

import java.util.List;

public interface ICiudadService {

    List<Ciudad> getCiudades();

    List<Ciudad> getCiudadesByProvinciaId(Long provinciaId);

    Ciudad getCiudadById(long ciudadId);

    Ciudad addCiudad(Ciudad ciudad) throws CiudadInvalidaException;

    boolean updateCiudad(Ciudad ciudad) throws CiudadInvalidaException;
}
