package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.*;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.enums.RolEnum;
import com.corpgtb.backend.services.IClienteService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClienteControllerTest {

    @InjectMocks
    ClienteController clienteController;

    @Mock
    IClienteService clienteService;

    @Test
    public void ShouldGetAllClientes() {
        List<Cliente> clienteList = new ArrayList<Cliente>();
        clienteList.add(new Cliente(1L, "Identificacion 1", "Razon social 1", "Nombre comercial 1", "Direccion 1", "Telefono 1.1", "Telefono 1.2", (double) 0, 0, "Cuenta contable 1", "Direccion referencial 1","email1@email1.com", true, new Usuario(), new Parroquia()));
        clienteList.add(new Cliente(2L, "Identificacion 2", "Razon social 2", "Nombre comercial 2", "Direccion 2", "Telefono 2.1", "Telefono 2.2", (double) 0, 0, "Cuenta contable 2", "Direccion referencial 2","email2@email2.com", true, new Usuario(), new Parroquia()));
        clienteList.add(new Cliente(3L, "Identificacion 3", "Razon social 3", "Nombre comercial 3", "Direccion 3", "Telefono 3.1", "Telefono 3.2", (double) 0, 0, "Cuenta contable 3", "Direccion referencial 3","email3@email3.com", true, new Usuario(), new Parroquia()));

        when(clienteService.getClientes(0,0, "mockSort", false)).thenReturn(clienteList);

        RespuestaGenerica result = clienteController.getAllClientes(0,0, "mockSort", false);

        List<Cliente> resultList = (List<Cliente>) result.getObjeto();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList.get(0).getRazonSocial()).isEqualTo("Razon social 1");
        assertThat(resultList.get(2).getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldGetClientesByIdentification() throws Exception {
        Cliente objCliente = new Cliente(1L, "Identificacion 1", "Razon social 1", "Nombre comercial 1", "Direccion 1", "Telefono 1.1", "Telefono 1.2", (double) 0, 0, "Cuenta contable 1", "Direccion referencial 1","email1@email1.com", true, new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO), new Parroquia());

        when(clienteService.findByIdentificacion("Identificacion 1")).thenReturn(objCliente);

        ResponseEntity<RespuestaGenerica> result = clienteController.getByIdentification("Identificacion 1");

        Cliente resultCliente = (Cliente) result.getBody().getObjeto();

        assertThat(resultCliente.getRazonSocial()).isEqualTo("Razon social 1");
        assertThat(resultCliente.getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldSaveCliente() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Cliente objCliente = new Cliente(1L, "Identificacion 1", "Razon social 1", "Nombre comercial 1", "Direccion 1", "Telefono 1.1", "Telefono 1.2", (double) 0, 0, "Cuenta contable 1", "Direccion referencial 1","email1@email1.com", true, new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO), new Parroquia());

        when(clienteService.addCliente(any(Cliente.class))).thenReturn(objCliente);
        ResponseEntity<RespuestaGenerica> result = clienteController.saveCliente(objCliente);

        Cliente resultCliente = (Cliente) result.getBody().getObjeto();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(resultCliente.getRazonSocial()).isEqualTo("Razon social 1");
    }
}
