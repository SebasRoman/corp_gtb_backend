package com.corpgtb.backend.services.impl;

import com.corpgtb.backend.model.Usuario;
import com.corpgtb.backend.model.dto.DataToken;
import com.corpgtb.backend.model.dto.JWTUser;
import com.corpgtb.backend.repository.IUsuarioRepository;
import com.corpgtb.backend.services.IUsuarioService;
import com.corpgtb.backend.utils.JWTTokenUtil;
import com.corpgtb.backend.utils.ShaUtils;
import com.corpgtb.backend.utils.ValidarAtributoUtil;
import com.corpgtb.backend.utils.excep.AtributoInvalidoException;
import com.corpgtb.backend.utils.excep.UsuarioInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UsuarioService")
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository _usuarioRepository;

    @Value("${token.validity}")
    long validez;

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> list = new ArrayList<>();
        _usuarioRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Usuario getUsuarioById(long usuarioId) {
        Usuario obj = _usuarioRepository.getByUsuarioId(usuarioId);
        return obj;
    }

    @Override
    public Usuario getUsuarioByNombre(String nombre) {
        return _usuarioRepository.findByNombreAndEstado(nombre,true);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) throws UsuarioInvalidoException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", usuario.getNombre());
            ValidarAtributoUtil.validarStringNuloVacio("PASSWORD", usuario.getPassword());
            ValidarAtributoUtil.validarStringNuloVacio("ROL", usuario.getRol().toString());
            usuario.setPassword(ShaUtils.codificarTexto(usuario.getPassword()));
            return _usuarioRepository.save(usuario);
        } catch (AtributoInvalidoException e) {
            throw new UsuarioInvalidoException(e);
        }
    }

    @Override
    public boolean updateUsuario(Usuario usuario) throws UsuarioInvalidoException {
        try {
            ValidarAtributoUtil.validarStringNuloVacio("NOMBRE", usuario.getNombre());
            ValidarAtributoUtil.validarStringNuloVacio("PASSWORD", usuario.getPassword());
            ValidarAtributoUtil.validarStringNuloVacio("ROL", usuario.getRol().toString());
            _usuarioRepository.save(usuario);
            return true;
        } catch (AtributoInvalidoException e) {
            throw new UsuarioInvalidoException(e);
        }
    }

    @Override
    public DataToken getToken(String username, String password) throws UsuarioInvalidoException {
        Usuario usuario = _usuarioRepository.findByNombreAndEstado(username, true);
        if (usuario != null) {
            if (usuario.getEstado()) {
                if (usuario.getPassword().equals(ShaUtils.codificarTexto(password))) {
                    JWTUser jwtUser = new JWTUser();
                    jwtUser.setUserId(usuario.getUsuarioId().toString());
                    jwtUser.setUsername(usuario.getNombre());
                    jwtUser.setRol(usuario.getRol().toString());
                    DataToken data = new DataToken();
                    data.setToken(new JWTTokenUtil().generarToken(jwtUser, validez));
                    return data;
                } else {
                    throw new UsuarioInvalidoException("Contraseña incorrecta");
                }
            } else {
                throw new UsuarioInvalidoException("Usuario no activo");
            }
        } else {
            throw new UsuarioInvalidoException("No existe el usuario");
        }
    }
}
