package io.github.leonarddepaula.libaryapi.repository;

import io.github.leonarddepaula.libaryapi.model.Autor;
import io.github.leonarddepaula.libaryapi.model.GeneroLivro;
import io.github.leonarddepaula.libaryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepositoty repositoty;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84111");
        livro.setPreco(BigDecimal.valueOf(110));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("WWW");
        livro.setDataPublicacao(
                LocalDate.of(2023, 1, 26)
        );
        Autor autor = autorRepository
                .findById(UUID.fromString("eb52580b-bcbb-45b3-a5ba-636a3883cf50"))
                .orElse(null);
        livro.setAutor(autor);

        repositoty.save(livro);
    }

    @Test
    public void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("30883-14874");
        livro.setPreco(BigDecimal.valueOf(170));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Dando Mais");
        livro.setDataPublicacao(
                LocalDate.of(2023, 1, 26)
        );

        Autor autor = new Autor();
        autor.setNome("Carol de paula");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1989, 7, 12));

//        autorRepository.save(autor);
        livro.setAutor(autor);


        repositoty.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("1c3d799a-f6ef-4234-9b7d-02a228556c4a");
        var livroParaAtualizar = repositoty.findById(id)
                .orElse(null);

        //livroParaAtualizar.setTitulo("Guitar Players");
        livroParaAtualizar.setGenero(GeneroLivro.BIOGRAFIA);
        repositoty.save(livroParaAtualizar);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("952c3980-1f4b-40cd-b1c9-21483be770f6");
        Livro livro = repositoty.findById(id).orElse(null);
        System.out.println("Livro: " + livro.getTitulo() + "\n" + "Autor: " + livro.getAutor().getNome());
//        System.out.println("Livro: " + livro.getTitulo());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repositoty.findByTitulo("Guerra Dos UFOs");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbn() {
        List<Livro> lista = repositoty.findByIsbn("30883-14874");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(100.00);
        var tituloPesquisa = "Guerra Dos UFOs";
        List<Livro> lista = repositoty.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorTituloOrIsbnTest() {
        var isbn = "";
        var titulo = "Guerra Dos UFOs";
        List<Livro> lista = repositoty.findByTituloOrIsbn(titulo, isbn);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQuerryJPQL() {
        var resultado = repositoty.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros() {
        var resultado = repositoty.ListarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

}