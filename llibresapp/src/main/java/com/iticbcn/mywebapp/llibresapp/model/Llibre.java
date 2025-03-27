package com.iticbcn.mywebapp.llibresapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "llibre")
public class Llibre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLlibre;

    @Column
    private String titol;

    @Column
    private String autor;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column
    private String editorial;

    @Column
    private LocalDate datapublicacio;

    @Column
    private String tematica;

}