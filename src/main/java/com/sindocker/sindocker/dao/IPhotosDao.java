package com.sindocker.sindocker.dao;

import com.sindocker.sindocker.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPhotosDao extends JpaRepository<Photo,Long> {

    List<Photo> findByMedianoId(String medianoId);
}
