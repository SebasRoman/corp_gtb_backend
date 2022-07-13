package com.corpgtb.backend.services;

import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.dto.DataToken;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> getUsuarios();

    Usuario getUsuarioById(long usuarioId);

    Usuario getUsuarioByNombre(String nombre);

    Usuario addUsuario(Usuario usuario) throws UsuarioInvalidoException;

    boolean updateUsuario(Usuario usuario) throws UsuarioInvalidoException;

    DataToken getToken(String username, String password) throws UsuarioInvalidoException;

    }
