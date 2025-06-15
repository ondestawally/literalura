package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Anotação para indicar que esta classe é uma entidade JPA
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "autores")
public class Autor {
    // Chave primária auto-gerada
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Coluna que deve ter valores únicos (nome do autor)
    @Column(unique = true)
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    // Relacionamento Um-para-Muitos com a entidade Livro
    // mappedBy indica que o relacionamento é mapeado pela propriedade "autor" na classe Livro
    // CascadeType.ALL: Propaga operações para os livros associados
    // FetchType.EAGER: Carrega os livros junto com o Autor (para simplicidade)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>(); // Um autor pode ter vários livros

    // Construtor padrão necessário para JPA
    public Autor() {}

    // Construtor que recebe um DTO DadosAutor para inicialização
    public Autor(com.alura.literalura.dto.DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    // Getters e Setters (necessários para JPA e para acesso aos atributos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        // Ao setar a lista de livros, garante que a referência bidirecional seja estabelecida
        this.livros = livros;
        for (Livro livro : livros) {
            livro.setAutor(this);
        }
    }

    // Método toString para representação textual do objeto
    @Override
    public String toString() {
        return String.format(
                "Autor: %s%n" +
                        "Ano de Nascimento: %s%n" +
                        "Ano de Falecimento: %s%n",
                nome,
                anoNascimento != null ? anoNascimento.toString() : "N/A",
                anoFalecimento != null ? anoFalecimento.toString() : "N/A"
        );
    }
}