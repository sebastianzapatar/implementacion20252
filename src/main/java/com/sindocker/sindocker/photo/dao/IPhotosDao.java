package com.sindocker.sindocker.photo.dao;

import com.sindocker.sindocker.photo.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPhotosDao extends JpaRepository<Photo,Long> {

    List<Photo> findByMedianoId(String medianoId);
}
