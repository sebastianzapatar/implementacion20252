package com.sindocker.sindocker.services;

import com.sindocker.sindocker.dao.IMedianoDao;
import com.sindocker.sindocker.dao.IPhotosDao;
import com.sindocker.sindocker.dto.PhotoDTO;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public void addPhoto(PhotoDTO photo) {
        Mediano mediano = medianoDao.findById(photo.medianoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mediano no encontrado"));
        Photo nuevaFoto = new Photo();
        nuevaFoto.setPath(photo.url());
        nuevaFoto.setDescription(photo.descripcion());
        nuevaFoto.setMediano(mediano);

        photosDao.save(nuevaFoto);
    }
}
