package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Anotação para ignorar propriedades JSON que não temos mapeadas neste record
@JsonIgnoreProperties(ignoreUnknown = true)
// Record para representar a resposta completa da API Gutendex
public record RespostaAPI(
        Long count,
        String next,
        String previous,
        // Usa @JsonAlias para mapear o campo 'results' do JSON para 'resultados' no Java
        @JsonAlias("results") List<DadosLivro> resultados
) {}