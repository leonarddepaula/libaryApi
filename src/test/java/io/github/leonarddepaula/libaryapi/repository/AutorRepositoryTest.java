package io.github.leonarddepaula.libaryapi.repository;

import io.github.leonarddepaula.libaryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Caroline de paula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1989, 6, 12));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("eb52580b-bcbb-45b3-a5ba-636a3883cf50");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }
    }
}
