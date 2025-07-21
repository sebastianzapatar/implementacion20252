package com.sindocker.sindocker.services;

import com.sindocker.sindocker.dto.PhotoDTO;
import com.sindocker.sindocker.models.Photo;

import java.util.List;

public interface IServicePhoto {
    public List<Photo> getPhotos();
    public void addPhoto(PhotoDTO photo);
}
