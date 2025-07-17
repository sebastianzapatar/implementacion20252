package com.sindocker.sindocker.services;

import com.sindocker.sindocker.Excepciones.NameException;
import com.sindocker.sindocker.dao.IMedianoDao;
import com.sindocker.sindocker.dao.IPhotosDao;
import com.sindocker.sindocker.dto.MedianoDTO;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMediano implements IServiceMediano {
    @Autowired
    private IMedianoDao medianoDao;
    @Autowired
    private IPhotosDao photosDao;

    @Override
    public List<MedianoDTO> getMedianos(){
        //SELECT * FROM medianos;
        return medianoDao.findAll().stream().map(
                mediano -> {
                    return new MedianoDTO(
                            mediano.getName(),
                            mediano.getHeight(),
                            mediano.getEmail()
                    );
                }
        ).toList();
    }

    @Override
    public Mediano addMediano(MedianoDTO m) {
        Mediano mediano = new Mediano();
        mediano.setHeight(m.altura());
        mediano.setName(m.nombre());
        mediano.setEmail(m.email());
        Mediano busqueda=
                medianoDao.findMedianoByName(mediano.getName())
                        .orElse(null);
        if(busqueda!=null) {
           throw new NameException("El mediano ya existe");
        }
        //INSERT INTO MEDIANOS VALUES (nombre,altura)
        return medianoDao.save(mediano);

    }

    @Override
    public Optional<Mediano> removeMediano(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Mediano> getMedianoById(String id) {
        return medianoDao.findById(id);
    }

    @Override
    public Optional<Mediano> getByName(String nombre) {
        return medianoDao.findMedianoByName(nombre);
    }

    @Override
    public List<Photo> getPhotosMediano(String id) {
        return photosDao.findByMedianoId(id);
    }
}
