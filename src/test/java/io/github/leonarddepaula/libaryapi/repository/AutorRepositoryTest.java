package io.github.leonarddepaula.libaryapi.repository;

import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.model.GeneroLivro;
import io.github.leonarddepaula.libaryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepositoty livroRepositoty;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Sebastian de paula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2026, 9, 12));

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

            autorEncontrado.setDataNascimento(LocalDate.of(1989, 7, 12));

            repository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("eb52580b-bcbb-45b3-a5ba-636a3883cf50");

        repository.deleteById(id);
    }


    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Lorena de Paula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2006, 5, 22));

        Livro livro = new Livro();
        livro.setIsbn("6046-14474");
        livro.setPreco(BigDecimal.valueOf(270));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Redes Não Sociais");
        livro.setDataPublicacao(
                LocalDate.of(2024, 1, 26)
        );
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("6026-15274");
        livro2.setPreco(BigDecimal.valueOf(200));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Escravidão Digital");
        livro2.setDataPublicacao(
                LocalDate.of(2023, 4, 16)
        );
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepositoty.saveAll(autor.getLivros());
    }

    @Test
    public void listaLivrosAutor() {
        var id = UUID.fromString("706f5508-9dbd-48fb-8cbd-bab0457b532e");
        var autor = repository.findById(id).get();
        // buscar os livros do autor
        List<Livro> livrosLista = livroRepositoty.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
