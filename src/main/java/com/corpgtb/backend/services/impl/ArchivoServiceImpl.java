package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Archivo;
import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.repository.IArchivoRepository;
import com.corpgtb.backend.services.IArchivoService;
import com.corpgtb.backend.services.IClienteService;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.excep.ArchivoInvalidoException;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ArchivoService")
@AllArgsConstructor
public class  ArchivoServiceImpl implements IArchivoService {

    private IArchivoRepository _archivoRepository;
    private IClienteService _clienteService;

    @Override
    public List<Archivo> getArchivos() {
        List<Archivo> list = new ArrayList<>();
        _archivoRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public List<Archivo> getArchivoByCliente(Long id, int page, int size, boolean asc) {

        Cliente cliente = _clienteService.getClienteById(id);
        if(cliente!=null) {

            List<Archivo> archivos;
            if( page!=0 || size !=0) {
                Sort sortBy = Sort.by("fecha");
                if(asc) {
                    sortBy = sortBy.ascending();
                } else {
                    sortBy = sortBy.descending();
                }
                archivos = _archivoRepository.findByClienteId(cliente, PageRequest.of(page, size, sortBy));
            } else {
                    archivos = _archivoRepository.findByClienteId(cliente);
            }
            return archivos;
        }
        return null;
    }

    @Override
    public Archivo getArchivoById(long archivoId) {
        Archivo obj = _archivoRepository.getByArchivoId(archivoId);
        return obj;
    }

    @Override
    public Archivo addArchivo(Archivo archivo) throws ArchivoInvalidoException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("Ruta", archivo.getRuta());
            ValidarAtributoUtil.validarStringNuloVacio("Descripción", archivo.getDescripcion());
            return _archivoRepository.save(archivo);
        } catch (AtributoInvalidoException ex) {
            throw new ArchivoInvalidoException(ex);
        }
    }

    @Override
    public boolean updateArchivo(Archivo archivo) throws ArchivoInvalidoException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("Ruta", archivo.getRuta());
            ValidarAtributoUtil.validarStringNuloVacio("Descripción", archivo.getDescripcion());
            _archivoRepository.save(archivo);
            return true;
        } catch (AtributoInvalidoException ex) {
            throw new ArchivoInvalidoException(ex);
        }

    }

    @Override
    public long getArhivoCountByCliente(Long id) {
        Cliente cliente = _clienteService.getClienteById(id);
        if(cliente!=null) {
            Archivo archivo = new Archivo();
            archivo.setClienteId(cliente);
            archivo.setEstado(true);
            return _archivoRepository.count(Example.of(archivo));
        }
        return 0;
    }
}
