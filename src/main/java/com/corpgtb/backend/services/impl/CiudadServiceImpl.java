package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.repository.ICiudadRepository;
import com.corpgtb.backend.services.ICiudadService;
import com.corpgtb.backend.services.IProvinciaService;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import com.corpgtb.backend.utils.excep.CiudadInvalidaException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("CiudadService")
@AllArgsConstructor
public class CiudadServiceImpl implements ICiudadService {

    private ICiudadRepository _ciudadRepository;
    IProvinciaService _proProvinciaService;


    @Override
    public List<Ciudad> getCiudades() {
        List<Ciudad> list = new ArrayList<>();
        _ciudadRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<Ciudad> getCiudadesByProvinciaId(Long provinciaId) {
        Provincia provincia = _proProvinciaService.getProvinciaById(provinciaId);
        if(provincia!=null) {
            return _ciudadRepository.getCiudadByProvinciaId(provincia);
        }
        return new ArrayList<>();
    }

    @Override
    public Ciudad getCiudadById(long ciudadId) {
        Ciudad obj = _ciudadRepository.getByCiudadId(ciudadId);
        return obj;
    }

    @Override
    public Ciudad addCiudad(Ciudad ciudad) throws CiudadInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", ciudad.getNombre());
            ValidarAtributoUtil.validarObjetoNulo("PROVINCIA", ciudad.getProvinciaId());
            return _ciudadRepository.save(ciudad);
        } catch (AtributoInvalidoException ex) {
            throw new CiudadInvalidaException(ex);
        }
    }

    @Override
    public boolean updateCiudad(Ciudad ciudad) throws CiudadInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", ciudad.getNombre());
            ValidarAtributoUtil.validarObjetoNulo("PROVINCIA", ciudad.getProvinciaId());
            _ciudadRepository.save(ciudad);
            return true;
        } catch (AtributoInvalidoException ex) {
            throw new CiudadInvalidaException(ex);
        }

    }
}
