package com.corpgtb.backend.repository;

import com.corpgtb.backend.model.Ciudad;
import com.corpgtb.backend.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CiudadRepository")
public interface ICiudadRepository extends JpaRepository<Ciudad, Integer> {

    Ciudad getByCiudadId(long ciudadId);
    List<Ciudad> getCiudadByProvinciaId(Provincia provinciaId);

}
