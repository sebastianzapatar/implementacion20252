package com.sindocker.sindocker.mediano.controllers;

import com.sindocker.sindocker.mediano.dto.MedianoDTO;
import com.sindocker.sindocker.mediano.models.Mediano;
import com.sindocker.sindocker.photo.models.Photo;
import com.sindocker.sindocker.mediano.services.IServiceMediano;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medianos")
@RequiredArgsConstructor
public class MedianoController {
    private final IServiceMediano mediano;

    @GetMapping("/")
    public List<MedianoDTO> getMedianos() {
        return mediano.getMedianos();

    }

    @PostMapping("/")
    public ResponseEntity<?> addMediano(@RequestBody @Valid MedianoDTO medianoDTO) {
        System.out.println(medianoDTO);
        mediano.addMediano(medianoDTO);
        return ResponseEntity.ok().build();
    }

    /// tarea
    // Usar streams
    // Filtrar los medianos que empiezan con a
    @GetMapping("/filtrado/{nombre}")
    public Mediano filtrado(@PathVariable String nombre) {
        ///
        return mediano.getByName(nombre).orElse(null);
    }

    @GetMapping("/fotos/{idMediano}")
    public List<Photo> fotos(@PathVariable String idMediano) {
        return this.mediano.getPhotosMediano(idMediano);
    }
    // mostrar el mediano con el nombre más largo

}
