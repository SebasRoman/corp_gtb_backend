package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.enums.RolEnum;
import com.corpgtb.backend.repository.IUsuarioRepository;
import com.corpgtb.backend.utils.ShaUtils;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;
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
public class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Test
    public void ShouldGetUsuarios(){

        List<Usuario> usuarioList = new ArrayList<Usuario>();
        usuarioList.add(new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO));
        usuarioList.add(new Usuario(2L, "Nombre 2", "Password 2", true, RolEnum.USUARIO));
        usuarioList.add(new Usuario(3L, "Nombre 3", "Password 3", true, RolEnum.USUARIO));
        when(usuarioRepository.findAll()).thenReturn(usuarioList);

        List<Usuario> result = usuarioServiceImpl.getUsuarios();
        assertEquals(3, result.size());
    }

    @Test
    public void ShouldGetUsuarioById() {
        Usuario objUsuario = new Usuario(1L, "Nombre 1", "Password 1", true, RolEnum.USUARIO);
        when(usuarioRepository.getByUsuarioId(1L)).thenReturn(objUsuario);

        Usuario result = usuarioServiceImpl.getUsuarioById(1L);

        assertEquals("Nombre 1", result.getNombre());
        assertEquals("Password 1", result.getPassword());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldAddUsuario() throws UsuarioInvalidoException {
        Usuario objUsuario = new Usuario(4L, "Nombre 4", "Password 4", true, RolEnum.USUARIO);
        when(usuarioRepository.save(objUsuario)).thenReturn(objUsuario);

        Usuario result = usuarioServiceImpl.addUsuario(objUsuario);

        assertEquals("Nombre 4", result.getNombre());
        assertEquals(ShaUtils.codificarTexto("Password 4"), result.getPassword());
        assertEquals(true, result.getEstado());
    }

    @Test
    public void ShouldUpdateUsuario() throws UsuarioInvalidoException {
        Usuario objUsuario = new Usuario(4L, "Nombre 4", "Password 4", true, RolEnum.USUARIO);
        when(usuarioRepository.save(objUsuario)).thenReturn(objUsuario);

        boolean result = usuarioServiceImpl.updateUsuario(objUsuario);

        assertEquals(true, result);
    }
}
