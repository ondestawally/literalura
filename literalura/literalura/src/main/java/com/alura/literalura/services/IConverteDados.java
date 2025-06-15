package com.alura.literalura.service;

import java.util.List;

// Interface para definir o contrato de um conversor de dados
public interface IConverteDados {
    // Método genérico para converter uma string JSON para um único objeto de uma determinada classe
    <T> T obterDados(String json, Class<T> classe);

    // Método genérico para converter uma string JSON para uma lista de objetos de uma determinada classe
    <T> List<T> obterLista(String json, Class<T> classe);
}