package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.utils.excep.ProvinciaInvalidaException;

import java.util.List;

public interface IProvinciaService {

    List<Provincia> getProvincias();

    Provincia getProvinciaById(long provinciaId);

    Provincia addProvincia(Provincia provincia) throws ProvinciaInvalidaException;

    boolean updateProvincia(Provincia provincia) throws ProvinciaInvalidaException;

}
