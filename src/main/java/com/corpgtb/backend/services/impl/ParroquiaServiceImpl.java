package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.repository.IParroquiaRepository;
import com.corpgtb.backend.services.ICiudadService;
import com.corpgtb.backend.services.IParroquiaService;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import com.corpgtb.backend.utils.excep.ParroquiaInvalidaException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ParroquiaService")
@AllArgsConstructor
public class ParroquiaServiceImpl implements IParroquiaService {

    private IParroquiaRepository _parroquiaRepository;
    private ICiudadService _ciudadService;


    @Override
    public List<Parroquia> getParroquias() {
       return _parroquiaRepository.findByEstado(true);
    }

    @Override
    public List<Parroquia> getParroquiasByCiudad(long ciudadId) {
        Ciudad ciudad = _ciudadService.getCiudadById(ciudadId);
        if(ciudad != null) {
            return _parroquiaRepository.findByCiudadId(ciudad);
        }
        return new ArrayList<>();
    }

    @Override
    public Parroquia getParroquiaById(long parroquiaId) {
        Parroquia obj = _parroquiaRepository.getByParroquiaId(parroquiaId);
        return obj;
    }

    @Override
    public Parroquia addParroquia(Parroquia parroquia) throws ParroquiaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", parroquia.getNombre());
            ValidarAtributoUtil.validarObjetoNulo("CIUDAD", parroquia.getCiudadId());
            return _parroquiaRepository.save(parroquia);
        } catch (AtributoInvalidoException e) {
            throw new ParroquiaInvalidaException(e);
        }
    }

    @Override
    public boolean updateParroquia(Parroquia parroquia) throws ParroquiaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", parroquia.getNombre());
            ValidarAtributoUtil.validarObjetoNulo("CIUDAD", parroquia.getCiudadId());
            _parroquiaRepository.save(parroquia);
            return true;
        } catch (AtributoInvalidoException e) {
            throw new ParroquiaInvalidaException(e);
        }


    }
}
