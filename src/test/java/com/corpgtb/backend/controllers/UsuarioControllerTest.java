package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.enums.RolEnum;
import com.corpgtb.backend.services.IUsuarioService;
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
public class UsuarioControllerTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    IUsuarioService usuarioService;

    @Test
    public void ShouldGetAllUsuarios() {
        List<Usuario> usuarioList = new ArrayList<Usuario>();
        usuarioList.add(new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO));
        usuarioList.add(new Usuario(2L, "Nombre 2", "Password 2", true, RolEnum.USUARIO));
        usuarioList.add(new Usuario(3L, "Nombre 3", "Password 3", false, RolEnum.USUARIO));
        when(usuarioService.getUsuarios()).thenReturn(usuarioList);

        RespuestaGenerica result = usuarioController.getAllUsuarios();

        List<Usuario> resultList = (List<Usuario>) result.getObjeto();

        assertThat(resultList.size()).isEqualTo(3);
        assertThat(resultList.get(0).getNombre()).isEqualTo("Nombre 1");
        assertThat(resultList.get(2).getEstado()).isEqualTo(false);;
    }

    @Test
    public void ShouldGetUsuarioById() throws Exception {
        Usuario objUsuario = new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO);
        when(usuarioService.getUsuarioById(1L)).thenReturn(objUsuario);

        ResponseEntity<RespuestaGenerica> result = usuarioController.getUsuarioById(1L);

        Usuario resultUsuario = (Usuario) result.getBody().getObjeto();

        assertThat(resultUsuario.getNombre()).isEqualTo("Nombre 1");
        assertThat(resultUsuario.getEstado()).isEqualTo(true);;
    }

    @Test
    public void ShouldSaveUsuario() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Usuario objUsuario = new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO);
        when(usuarioService.addUsuario(any(Usuario.class))).thenReturn(objUsuario);
        ResponseEntity<RespuestaGenerica> result = usuarioController.saveUsuario(objUsuario);

        Usuario resultUsuario = (Usuario) result.getBody().getObjeto();

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(resultUsuario.getNombre()).isEqualTo("Nombre 1");
    }
}
