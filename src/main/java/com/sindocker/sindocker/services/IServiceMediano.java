package com.sindocker.sindocker.services;

import com.sindocker.sindocker.dto.MedianoDTO;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.models.Photo;

import java.util.List;
import java.util.Optional;

public interface IServiceMediano {
    public List<MedianoDTO> getMedianos();
    public Mediano addMediano(MedianoDTO m);
    public Optional<Mediano> removeMediano(String id);
    public Optional<Mediano> getMedianoById(String id);
    public Optional<Mediano> getByName(String nombre);
    public List<Photo> getPhotosMediano(String id);
}
