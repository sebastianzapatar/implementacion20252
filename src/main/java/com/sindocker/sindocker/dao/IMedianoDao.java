package com.sindocker.sindocker.dao;

import com.sindocker.sindocker.models.Mediano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedianoDao
        extends JpaRepository<Mediano,String> {
}
