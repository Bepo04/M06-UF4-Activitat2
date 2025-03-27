package com.iticbcn.mywebapp.llibresapp.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iticbcn.mywebapp.llibresapp.model.Llibre;
import com.iticbcn.mywebapp.llibresapp.repositories.RepoLlibre;

@Service
public class BookServiceImpl implements BookService {

    private final RepoLlibre repoLlibre;

    @Autowired
    public BookServiceImpl(RepoLlibre repoLlibre) {
        this.repoLlibre = repoLlibre;
    }

    public Set<Llibre> findAll() {
        return repoLlibre.findAll();
    }

    public Llibre findByTitol(String titol) throws Exception {
        return repoLlibre.findByTitol(titol);
    }

    public Set<Llibre> findByTitolAndEditorial(String titol, String editorial) throws Exception {
        return repoLlibre.findByTitolAndEditorial(titol, editorial);
    }

    public boolean validateISBNFormat(String isbn) {
        return isbn.matches("^(?=(?:\\D\\d){10}(?:(?:\\D\\d){3})?$)[\\d-]+$");
    }

    public Optional<Llibre> findByIdLlibre(int idLlibre) throws Exception {
        return repoLlibre.findById((long)idLlibre);
    }

    public Llibre save(Llibre llibre) {
        return repoLlibre.save(llibre);
    }
}
