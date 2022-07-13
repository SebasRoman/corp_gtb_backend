package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Parroquia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ParroquiaRepository")
public interface IParroquiaRepository extends CrudRepository<Parroquia, Integer> {

    Parroquia getByParroquiaId(long parroquiaId);
    List<Parroquia> findByEstado(boolean estado);
    List<Parroquia> findByCiudadId(Ciudad ciudadId);

}
