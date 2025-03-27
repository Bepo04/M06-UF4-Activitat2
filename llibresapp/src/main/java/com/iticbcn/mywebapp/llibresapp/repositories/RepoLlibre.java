package com.iticbcn.mywebapp.llibresapp.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import com.iticbcn.mywebapp.llibresapp.model.Llibre;


public interface RepoLlibre extends CrudRepository<Llibre, Long> {
    
    @Override
    @NonNull
    Set<Llibre> findAll();

    Llibre findByTitol(String titol) throws Exception;

    Set<Llibre> findByTitolAndEditorial(String titol, String editorial) throws Exception;

    Llibre save(Llibre llibre);

}