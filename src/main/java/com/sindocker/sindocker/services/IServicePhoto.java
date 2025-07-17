package com.sindocker.sindocker.services;

import com.sindocker.sindocker.models.Photo;

import java.util.List;

public interface IServicePhoto {
    public List<Photo> getPhotos();
    public void addPhoto(Photo photo);
}
