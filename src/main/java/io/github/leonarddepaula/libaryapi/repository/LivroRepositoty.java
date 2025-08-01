package io.github.leonarddepaula.libaryapi.repository;

import io.github.leonarddepaula.libaryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepositoty extends JpaRepository<Livro, UUID> {
}
