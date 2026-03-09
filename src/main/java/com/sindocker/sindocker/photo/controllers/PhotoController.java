package com.sindocker.sindocker.photo.controllers;

import com.sindocker.sindocker.photo.dto.PhotoDTO;
import com.sindocker.sindocker.photo.models.Photo;
import com.sindocker.sindocker.photo.services.IServicePhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
@RequiredArgsConstructor
public class PhotoController {
    private final IServicePhoto servicePhoto;

    @GetMapping("/")
    public List<Photo> getPhoto(@PathVariable String idmediano) {
        return servicePhoto.getPhotos();

    }

    @PostMapping("/")
    public void createPhoto(@RequestBody PhotoDTO photo) {
        this.servicePhoto.addPhoto(photo);
    }

}
