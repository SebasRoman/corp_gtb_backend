package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.utils.excep.ParroquiaInvalidaException;

import java.util.List;

public interface IParroquiaService {

    List<Parroquia> getParroquias();

    List<Parroquia> getParroquiasByCiudad(long ciudadId);

    Parroquia getParroquiaById(long parroquiaId);

    Parroquia addParroquia(Parroquia parroquia) throws ParroquiaInvalidaException;

    boolean updateParroquia(Parroquia parroquia) throws ParroquiaInvalidaException;

}
