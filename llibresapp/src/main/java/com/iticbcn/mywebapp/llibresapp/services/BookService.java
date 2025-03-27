package com.iticbcn.mywebapp.llibresapp.services;

import java.util.Optional;
import java.util.Set;

import com.iticbcn.mywebapp.llibresapp.model.Llibre;

public interface BookService {

    Set<Llibre> findAll();

    Llibre findByTitol(String titol) throws Exception;

    Set<Llibre> findByTitolAndEditorial(String titol, String editorial) throws Exception;

    boolean validateISBNFormat(String isbn);

    Optional<Llibre> findByIdLlibre(int idLlibre) throws Exception;
    
    Llibre save(Llibre llibre);
}
