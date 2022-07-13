package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UsuarioRepository")
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario getByUsuarioId(long usuarioId);
    List<Usuario> findByEstado(boolean estado);
    public Usuario findByNombreAndEstado(String nombre, boolean estado);

}
