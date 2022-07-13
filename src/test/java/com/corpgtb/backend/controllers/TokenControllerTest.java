package com.corpgtb.backend.controllers;

import com.corpgtb.backend.model.dto.DataToken;
import com.corpgtb.backend.model.dto.RespuestaGenerica;
import com.corpgtb.backend.model.dto.UsuarioDto;
import com.corpgtb.backend.services.IUsuarioService;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenControllerTest {

    @InjectMocks
    TokenController tokenController;

    @Mock
    IUsuarioService usuarioService;

    @Test
    public void ShouldGetToken() throws UsuarioInvalidoException {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername("mockUsuario");
        usuarioDto.setPassword("mockPassword");
        DataToken token = new DataToken();
        token.setToken("mockToken");
        when(usuarioService.getToken("mockUsuario", "mockPassword")).thenReturn(token);

        ResponseEntity<RespuestaGenerica> result = tokenController.getToken(usuarioDto);

        DataToken resultDataToken = (DataToken) result.getBody().getObjeto();

        assertThat(resultDataToken.getToken()).isEqualTo("mockToken");
    }
}
