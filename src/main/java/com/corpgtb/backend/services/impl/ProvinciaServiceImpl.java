package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Provincia;
import com.corpgtb.backend.repository.IProvinciaRepository;
import com.corpgtb.backend.services.IProvinciaService;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import com.corpgtb.backend.utils.excep.ProvinciaInvalidaException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProvinciaService")
@AllArgsConstructor
public class ProvinciaServiceImpl implements IProvinciaService {

    private IProvinciaRepository _provinciaRepository;

    @Override
    public List<Provincia> getProvincias() {
        return _provinciaRepository.findByEstado(true);
    }

    @Override
    public Provincia getProvinciaById(long provinciaId) {
        Provincia obj = _provinciaRepository.getByProvinciaId(provinciaId);
        return obj;
    }

    @Override
    public Provincia addProvincia(Provincia provincia) throws ProvinciaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", provincia.getNombre());
            return _provinciaRepository.save(provincia);
        } catch (AtributoInvalidoException e) {
            throw new ProvinciaInvalidaException(e);
        }
    }

    @Override
    public boolean updateProvincia(Provincia provincia) throws ProvinciaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", provincia.getNombre());
            _provinciaRepository.save(provincia);
            return true;
        } catch (AtributoInvalidoException e) {
            throw new ProvinciaInvalidaException(e);
        }

    }

}
