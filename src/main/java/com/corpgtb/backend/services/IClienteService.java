package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.utils.excep.CedulaInvalidaException;
import com.corpgtb.backend.utils.excep.ClienteInvalidoException;

import java.util.List;

public interface IClienteService {

    List<Cliente> getClientes(int page, int size, String sort, boolean asc);

    Cliente getClienteById(long clienteId);

    Cliente getClienteByUsuario();

    Cliente findByIdentificacion(String identificacion);

    public List<Cliente> buscarClaveBusqueda(String clave);

    Cliente addCliente(Cliente cliente) throws ClienteInvalidoException, CedulaInvalidaException;

    boolean updateCliente(Cliente cliente) throws ClienteInvalidoException, CedulaInvalidaException;

    long getClienteCount();

}
