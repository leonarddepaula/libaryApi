package io.github.leonarddepaula.libaryapi.repository;

import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.model.GeneroLivro;
import io.github.leonarddepaula.libaryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositotyTest {

    @Autowired
    LivroRepositoty repositoty;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Guerra Dos UFOs");
        livro.setDataPublicacao(
                LocalDate.of(2022, 1, 26)
        );
        Autor autor = autorRepository
                .findById(UUID.fromString("3604e601-506a-409e-adcd-470971024706"))
                .orElse(null);
        livro.setAutor(autor);

        repositoty.save(livro);
    }

}