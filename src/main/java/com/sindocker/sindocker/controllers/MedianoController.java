package com.sindocker.sindocker.controllers;

import com.sindocker.sindocker.models.Mediano;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medianos")
public class MedianoController {
    private List<String> medianos=new ArrayList<>();
    @GetMapping("/")
    public List<String> getMedianos(){
        return this.medianos;
    }
    @PostMapping("/")
    public void addMediano(@RequestBody String mediano){
        this.medianos.add(mediano);
    }
    /// tarea
    //Usar streams
    //Filtrar los medianos que empiezan con a
    @GetMapping("/filtrado/{letra}")
    public List<String> filtrado(@PathVariable String letra){
        return this.medianos.stream().filter(mediano ->
                mediano.contains(letra)).
                collect(Collectors.toList());
    }
    //mostrar el mediano con el nombre más largo

}
