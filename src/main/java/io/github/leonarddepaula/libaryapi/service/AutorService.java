package io.github.leonarddepaula.libaryapi.service;

import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {


    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }
}
