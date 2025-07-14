package com.sindocker.sindocker.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity()
@Table(name="medianos")
@Data//getters y setters
@NoArgsConstructor// constructor vacio
@AllArgsConstructor//constructor completo
public class Mediano {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private double height;

}
