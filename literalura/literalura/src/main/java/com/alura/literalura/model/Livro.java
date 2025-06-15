package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

// Anotação para indicar que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "livros")
public class Livro {
    // Chave primária auto-gerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Coluna que deve ter valores únicos (ID original do Gutendex)
    @Column(unique = true)
    private Long gutendexId;
    private String titulo;

    // Relacionamento Muitos-para-Um com a entidade Autor
    // CascadeType.ALL: Operações (persist, remove, merge) propagam para o Autor associado
    // FetchType.EAGER: Carrega o Autor junto com o Livro (para simplicidade neste projeto)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor; // Um livro tem um autor

    private String idioma;
    private Long numeroDownloads;

    // Construtor padrão necessário para JPA
    public Livro() {}

    // Construtor que recebe um DTO DadosLivro para inicialização
    public Livro(com.alura.literalura.dto.DadosLivro dadosLivro) {
        this.gutendexId = dadosLivro.id();
        this.titulo = dadosLivro.titulo();
        // O autor será setado posteriormente pelo serviço para evitar duplicações
        this.idioma = dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty() ? dadosLivro.idiomas().get(0) : "N/A";
        this.numeroDownloads = dadosLivro.numeroDownloads() != null ? dadosLivro.numeroDownloads() : 0L;
    }

    // Getters e Setters (necessários para JPA e para acesso aos atributos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGutendexId() {
        return gutendexId;
    }

    public void setGutendexId(Long gutendexId) {
        this.gutendexId = gutendexId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Long getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Long numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    // Método toString para representação textual do objeto
    @Override
    public String toString() {
        String nomeAutor = (autor != null) ? autor.getNome() : "Desconhecido";
        return String.format(
                "----- LIVRO -----%n" +
                        "Título: %s%n" +
                        "Autor: %s%n" +
                        "Idioma: %s%n" +
                        "Número de Downloads: %d%n" +
                        "-----------------%n",
                titulo, nomeAutor, idioma, numeroDownloads
        );
    }
}