package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Anotação para ignorar propriedades JSON que não temos mapeadas neste record
@JsonIgnoreProperties(ignoreUnknown = true)
// Record para representar os dados de um autor conforme vêm da API
public record DadosAutor(
        @JsonAlias("name") String nome, // Mapeia 'name' do JSON para 'nome' no Java
        @JsonAlias("birth_year") Integer anoNascimento, // Mapeia 'birth_year'
        @JsonAlias("death_year") Integer anoFalecimento // Mapeia 'death_year'
) {}