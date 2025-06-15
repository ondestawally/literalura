package com.alura.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

// Classe que implementa a interface IConverteDados para converter JSON usando Jackson
public class ConverteDados implements IConverteDados {
    // Instância do ObjectMapper, a classe central do Jackson para mapeamento JSON
    private ObjectMapper mapper = new ObjectMapper();

    // Implementação do método para converter JSON para um único objeto
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            // Usa readValue do ObjectMapper para converter a string JSON para um objeto da classe especificada
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
            // Lança uma exceção em caso de erro na conversão
            throw new RuntimeException("Erro ao converter JSON para " + classe.getSimpleName(), e);
        }
    }

    // Implementação do método para converter JSON para uma lista de objetos
    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        try {
            // Constrói um tipo de coleção para indicar que esperamos uma lista da classe específica
            CollectionType lista = mapper.getTypeFactory()
                    .constructCollectionType(List.class, classe);
            // Usa readValue do ObjectMapper para converter a string JSON para a lista de objetos
            return mapper.readValue(json, lista);
        } catch (Exception e) {
            System.out.println("Erro ao converter JSON para lista: " + e.getMessage());
            // Lança uma exceção em caso de erro na conversão
            throw new RuntimeException("Erro ao converter JSON para lista de " + classe.getSimpleName(), e);
        }
    }
}