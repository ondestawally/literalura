package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Anotação para ignorar propriedades JSON que não temos mapeadas neste record
@JsonIgnoreProperties(ignoreUnknown = true)
// Record para representar os dados de um único livro conforme vêm da API
public record DadosLivro(
        Long id, // ID original do livro no Gutendex
        @JsonAlias("title") String titulo, // Mapeia 'title' do JSON para 'titulo' no Java
        @JsonAlias("authors") List<DadosAutor> autores, // Mapeia 'authors' para lista de DadosAutor
        @JsonAlias("languages") List<String> idiomas, // Mapeia 'languages' para lista de Strings
        @JsonAlias("download_count") Long numeroDownloads // Mapeia 'download_count' para 'numeroDownloads'
) {}