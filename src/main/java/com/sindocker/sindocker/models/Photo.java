package com.sindocker.sindocker.models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="fotos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String path;

    String description;

    private LocalDate created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mediano_id", nullable = false)
    private Mediano mediano;
}
