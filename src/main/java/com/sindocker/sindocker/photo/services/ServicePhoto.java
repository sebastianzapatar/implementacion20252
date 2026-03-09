package com.sindocker.sindocker.photo.services;

import com.sindocker.sindocker.mediano.dao.IMedianoDao;
import com.sindocker.sindocker.photo.dao.IPhotosDao;
import com.sindocker.sindocker.photo.dto.PhotoDTO;
import com.sindocker.sindocker.mediano.models.Mediano;
import com.sindocker.sindocker.photo.models.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicePhoto implements IServicePhoto {
    private final IPhotosDao photosDao;
    private final IMedianoDao medianoDao;

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
