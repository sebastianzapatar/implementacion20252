package com.sindocker.sindocker.services;

import com.sindocker.sindocker.dao.IMedianoDao;
import com.sindocker.sindocker.dao.IPhotosDao;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicePhoto implements IServicePhoto{
    @Autowired
    private IPhotosDao photosDao;
    @Autowired
    private IMedianoDao medianoDao;

    @Override
    public List<Photo> getPhotos() {
        return photosDao.findAll();
    }

    @Override
    public void addPhoto(Photo photo) {
        //Mediano mediano=medianoDao.findById();
        photosDao.save(photo);
    }
}
