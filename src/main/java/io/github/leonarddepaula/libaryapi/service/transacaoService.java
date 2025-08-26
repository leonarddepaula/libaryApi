package io.github.leonarddepaula.libaryapi.service;

import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.model.GeneroLivro;
import io.github.leonarddepaula.libaryapi.model.Livro;
import io.github.leonarddepaula.libaryapi.repository.AutorRepository;
import io.github.leonarddepaula.libaryapi.repository.LivroRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class transacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepositoty livroRepositoty;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepositoty
                .findById(UUID.fromString("7e85305a-1e2d-4a2a-a6b8-8f43eaa4b3c4"))
                .orElse(null);

        livro.setTitulo("Estudar E Passar");

    }

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("Ana Luísa de paula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1989, 7, 12));

        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setIsbn("30873-14874");
        livro.setPreco(BigDecimal.valueOf(174));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Dando Mais 2");
        livro.setDataPublicacao(
                LocalDate.of(2023, 1, 26)
        );


        livro.setAutor(autor);


        livroRepositoty.save(livro);

        if (autor.getNome().equals("Caroline de paula")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
