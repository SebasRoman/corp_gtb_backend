package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Cliente;
import com.corpgtb.backend.model.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClienteRepository")
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente getByClienteId(long clienteId);
    List<Cliente> findByEstado(boolean estado, Pageable pageable);
    List<Cliente> findByEstado(boolean estado);
    Cliente findByIdentificacion(String identificacion);
    Cliente findByUsuarioId(Usuario usuarioId);
}
