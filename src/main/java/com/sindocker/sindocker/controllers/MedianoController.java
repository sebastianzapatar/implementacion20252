package com.sindocker.sindocker.controllers;

import com.sindocker.sindocker.dto.MedianoDTO;
import com.sindocker.sindocker.models.Mediano;
import com.sindocker.sindocker.models.Photo;
import com.sindocker.sindocker.services.IServiceMediano;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medianos")
public class MedianoController {
    @Autowired
    private IServiceMediano mediano;


    @GetMapping("/")
    public List<MedianoDTO> getMedianos(){
        return mediano.getMedianos();

    }
    @PostMapping("/")
    public ResponseEntity<?> addMediano(@RequestBody @Valid
                                            MedianoDTO medianoDTO){
        System.out.println(medianoDTO);
        mediano.addMediano(medianoDTO);
        return ResponseEntity.ok().build();
    }
    /// tarea
    //Usar streams
    //Filtrar los medianos que empiezan con a
    @GetMapping("/filtrado/{nombre}")
    public Mediano filtrado(@PathVariable String nombre){
        ///
        return mediano.getByName(nombre).orElse(null);
    }
    @GetMapping("/fotos/{idMediano}")
    public List<Photo> fotos(@PathVariable String idMediano){
        return this.mediano.getPhotosMediano(idMediano);
    }
    //mostrar el mediano con el nombre más largo

}
