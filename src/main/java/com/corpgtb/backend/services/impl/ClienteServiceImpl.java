package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.repository.GenericSearchRepository;
import com.corpgtb.backend.repository.IClienteRepository;
import com.corpgtb.backend.services.IClienteService;
import com.corpgtb.backend.services.IUsuarioService;
import com.corpgtb.backend.utils.DatosSesion;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.ValidarId;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import com.corpgtb.backend.utils.excep.CedulaInvalidaException;
import com.corpgtb.backend.utils.excep.ClienteInvalidoException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("ClienteService")
@AllArgsConstructor
public class ClienteServiceImpl  implements IClienteService {

    private IClienteRepository _clienteRepository;
    GenericSearchRepository<Cliente> _genericSearchRepository;

    IUsuarioService usuarioService;


    @Override
    public List<Cliente> getClientes(int page, int size, String sort, boolean asc) {
        List<Cliente> clientes;
        if( page!=0 || size !=0) {
            if(sort!=null && !sort.isEmpty()){
                Sort sortBy = Sort.by(sort);
                if(asc) {
                    sortBy = sortBy.ascending();
                }
                else {
                    sortBy = sortBy.descending();
                }
                clientes = _clienteRepository.findByEstado(true, PageRequest.of(page, size, sortBy));
            } else {
                clientes = _clienteRepository.findByEstado(true, PageRequest.of(page, size));
            }
        } else {
            clientes = _clienteRepository.findByEstado(true);
        }
        return clientes;
    }

    @Override
    public Cliente getClienteById(long clienteId) {
        Cliente obj = _clienteRepository.getByClienteId(clienteId);
        return obj;
    }

    @Override
    public Cliente getClienteByUsuario() {
        Usuario usuario = usuarioService.getUsuarioByNombre(new DatosSesion().recupearUsuario());
        if(usuario!=null) {
            return _clienteRepository.findByUsuarioId(usuario);
        }
        return null;
    }

    @Override
    public Cliente findByIdentificacion(String identificacion) {
        return _clienteRepository.findByIdentificacion(identificacion);
    }

    @Override
    public List<Cliente> buscarClaveBusqueda(String clave) {
        List<Cliente> clientes = _genericSearchRepository.search(clave, new Cliente(), "identificacion", "razon_social", "nombre_comercial", "email");
        if(clientes.size()>0) {
            Predicate<Cliente> byEstado = cliente -> cliente.getEstado();
            return clientes.stream().filter(byEstado).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public Cliente addCliente(Cliente cliente) throws ClienteInvalidoException, CedulaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("RAZÓN SOCIAL", cliente.getRazonSocial());
            ValidarAtributoUtil.validarStringNuloVacio("DIRECCIÓN", cliente.getDireccion());
            ValidarAtributoUtil.validarObjetoNulo("TELEFONO 1", cliente.getTelefono1());
            ValidarAtributoUtil.validarStringNuloVacio("IDENTIFICACIÓN", cliente.getIdentificacion());
            ValidarAtributoUtil.validarEmail(cliente.getEmail());
            ValidarId.validadarCed_RUC(cliente.getIdentificacion());
            return _clienteRepository.save(cliente);
        } catch (AtributoInvalidoException ex) {
            throw new ClienteInvalidoException(ex);
        }
    }

    @Override
    public boolean updateCliente(Cliente cliente) throws ClienteInvalidoException, CedulaInvalidaException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("RAZÓN SOCIAL", cliente.getRazonSocial());
            ValidarAtributoUtil.validarStringNuloVacio("DIRECCIÓN", cliente.getDireccion());
            ValidarAtributoUtil.validarStringNuloVacio("IDENTIFICACIÓN", cliente.getIdentificacion());
            ValidarAtributoUtil.validarObjetoNulo("TELEFONO 1", cliente.getTelefono1());
            ValidarAtributoUtil.validarEmail(cliente.getEmail());
            ValidarId.validadarCed_RUC(cliente.getIdentificacion());
            _clienteRepository.save(cliente);
            return true;
        } catch (AtributoInvalidoException ex) {
            throw new ClienteInvalidoException(ex);
        }

    }

    @Override
    public long getClienteCount() {
        Cliente cliente = new Cliente();
        cliente.setEstado(true);
        return _clienteRepository.count(Example.of(cliente));
    }
}
