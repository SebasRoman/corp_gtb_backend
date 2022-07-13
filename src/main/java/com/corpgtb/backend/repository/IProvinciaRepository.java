package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Provincia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProvinciaRepository")
public interface IProvinciaRepository extends CrudRepository<Provincia, Integer> {

    Provincia getByProvinciaId(long provinciaId);
    List<Provincia> findByEstado(boolean estado);

}
