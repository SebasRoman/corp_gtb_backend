package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Archivo;
import com.corpgtb.backend.model.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ArchivoRepository")
public interface IArchivoRepository extends JpaRepository<Archivo, Integer> {

    Archivo getByArchivoId(long archivoId);

    List<Archivo> findByClienteId(Cliente clienteId, Pageable pageable);
    List<Archivo> findByClienteId(Cliente clienteId);


}
