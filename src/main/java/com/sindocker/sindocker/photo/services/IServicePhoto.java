package com.sindocker.sindocker.photo.services;

import com.sindocker.sindocker.photo.dto.PhotoDTO;
import com.sindocker.sindocker.photo.models.Photo;

import java.util.List;

public interface IServicePhoto {
    public List<Photo> getPhotos();
    public void addPhoto(PhotoDTO photo);
}
