package io.github.leonarddepaula.libaryapi.service;

import io.github.leonarddepaula.libaryapi.exceptions.OperacaoNaoPermitida;
import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.repository.AutorRepository;
import io.github.leonarddepaula.libaryapi.repository.LivroRepositoty;
import io.github.leonarddepaula.libaryapi.validador.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // essa anotation cria um contrutor em tempo de compilaçao.
public class AutorService {


    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepositoty livroRepository;


    public Autor salvar(Autor autor) {

        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para Atulizar é nescessário que já exista um autor");
        }

        validator.validar(autor);
        repository.save(autor);
    }


    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {

        if (possuiLivro(autor)) {
            throw new OperacaoNaoPermitida("Autor possui Livros Cadastrados ** EXCLUSSÃO NÃO PERMITIDA");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisar(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return repository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }
}
