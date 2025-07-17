package com.sindocker.sindocker.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.util.Set;

@Entity()
@Table(name="medianos")
@Data//getters y setters
@NoArgsConstructor// constructor vacio
@AllArgsConstructor//constructor completo
public class Mediano {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String id;

    @Column(unique=true)
    private String name;

    private long height;

    private String email;
    @OneToMany(mappedBy = "mediano",cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Photo> photos;

}


