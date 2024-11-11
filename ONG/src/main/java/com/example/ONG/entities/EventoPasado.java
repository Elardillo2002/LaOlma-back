package com.example.ONG.entities;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EventoPasado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column (nullable=false)
    private String titulo;
    @Column(nullable= false, columnDefinition="LONGTEXT")
    private String descripcion;
    @Column(nullable=false)
    private String participantes;
    @Column(nullable=false)
    private String lugar;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Lob
    @Column(nullable=false, columnDefinition="LONGBLOB")
    private byte[] imagen;

}
