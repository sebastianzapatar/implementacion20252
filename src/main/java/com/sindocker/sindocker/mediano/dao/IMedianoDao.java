package com.sindocker.sindocker.mediano.dao;

import com.sindocker.sindocker.mediano.models.Mediano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMedianoDao
        extends JpaRepository<Mediano,String> {
    //SELECT * FROM MEDIANO WHERE NOMBRE=NOMBRE
    Optional<Mediano> findMedianoByName(String nombre);
}
