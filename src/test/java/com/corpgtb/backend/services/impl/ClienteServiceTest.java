package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.model.Parroquia;
import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.enums.RolEnum;
import com.corpgtb.backend.repository.IClienteRepository;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.ValidarId;
import com.corpgtb.backend.utils.excep.CedulaInvalidaException;
import com.corpgtb.backend.utils.excep.ClienteInvalidoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteServiceTest {

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    @Test
    public void ShouldGetClientes(){

        List<Cliente> clienteList = new ArrayList<Cliente>();
        clienteList.add(new Cliente(1L, "Identificacion 1", "Razon social 1", "Nombre comercial 1", "Direccion 1", "Telefono 1.1", "Telefono 1.2", (double) 0, 0, "Cuenta contable 1", "Direccion referencial 1","email1@email1.com", true, new Usuario(), new Parroquia()));
        clienteList.add(new Cliente(2L, "Identificacion 2", "Razon social 2", "Nombre comercial 2", "Direccion 2", "Telefono 2.1", "Telefono 2.2", (double) 0, 0, "Cuenta contable 2", "Direccion referencial 2","email2@email2.com", true, new Usuario(), new Parroquia()));
        clienteList.add(new Cliente(3L, "Identificacion 3", "Razon social 3", "Nombre comercial 3", "Direccion 3", "Telefono 3.1", "Telefono 3.2", (double) 0, 0, "Cuenta contable 3", "Direccion referencial 3","email3@email3.com", true, new Usuario(), new Parroquia()));
        when(clienteRepository.findByEstado(true)).thenReturn(clienteList);

        List<Cliente> result = clienteServiceImpl.getClientes(0,0,null,true);
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetClienteById() {
        Cliente objCliente = new Cliente(1L, "Identificacion 1", "Razon social 1", "Nombre comercial 1", "Direccion 1", "Telefono 1.1", "Telefono 1.2", (double) 0, 0, "Cuenta contable 1", "Direccion referencial 1","email1@email1.com", true, new Usuario(), new Parroquia());
        when(clienteRepository.getByClienteId(1L)).thenReturn(objCliente);

        Cliente result = clienteServiceImpl.getClienteById(1L);

        assertEquals("Identificacion 1", result.getIdentificacion());
        assertEquals("Razon social 1", result.getRazonSocial());
        assertEquals("Nombre comercial 1", result.getNombreComercial());
        assertEquals("Direccion 1", result.getDireccion());
        assertEquals("Telefono 1.1", result.getTelefono1());
        assertEquals("Telefono 1.2", result.getTelefono2());
        assertEquals("Cuenta contable 1", result.getCuentaContable());
        assertEquals("Direccion referencial 1", result.getDireccionReferencial());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddCliente() throws ClienteInvalidoException, CedulaInvalidaException {
        Cliente objCliente = new Cliente(4L, "0104475785", "Razon social 4", "Nombre comercial 4", "Direccion 4", "Telefono 4.1", "Telefono 4.2", (double) 0, 0, "Cuenta contable 4", "Direccion referencial 4","email4@email4.com", true, new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO), new Parroquia());
        when(clienteRepository.save(objCliente)).thenReturn(objCliente);

        Cliente result = clienteServiceImpl.addCliente(objCliente);

        assertEquals("0104475785", result.getIdentificacion());
        assertEquals("Razon social 4", result.getRazonSocial());
        assertEquals("Nombre comercial 4", result.getNombreComercial());
        assertEquals("Direccion 4", result.getDireccion());
        assertEquals("Telefono 4.1", result.getTelefono1());
        assertEquals("Telefono 4.2", result.getTelefono2());
        assertEquals("Cuenta contable 4", result.getCuentaContable());
        assertEquals("Direccion referencial 4", result.getDireccionReferencial());
        assertEquals("email4@email4.com",result.getEmail());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldUpdateCliente() throws ClienteInvalidoException, CedulaInvalidaException {
        Cliente objCliente = new Cliente(4L, "0104475785", "Razon social 4", "Nombre comercial 4", "Direccion 4", "Telefono 4.1", "Telefono 4.2", (double) 0, 0, "Cuenta contable 4", "Direccion referencial 4","email4@email4.com", true, new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO), new Parroquia());
        when(clienteRepository.save(objCliente)).thenReturn(objCliente);
        boolean result = clienteServiceImpl.updateCliente(objCliente);

        assertEquals(true, result);
    }
}
