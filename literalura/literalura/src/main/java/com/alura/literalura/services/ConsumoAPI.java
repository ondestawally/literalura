package com.alura.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Classe responsável por consumir APIs HTTP
public class ConsumoAPI {

    // Método para obter dados de uma URL fornecida
    public String obterDados(String endereco) {
        // Cria um novo cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        // Constrói a requisição HTTP GET para o endereço fornecido
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)) // Converte a string de endereço em um objeto URI
                .build(); // Constrói a requisição
        HttpResponse<String> response = null;
        try {
            // Envia a requisição e recebe a resposta, com o corpo da resposta como String
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar dados da API: " + e.getMessage());
            // Lança uma exceção em caso de erro na comunicação
            throw new RuntimeException(e);
        }
        // Obtém o corpo da resposta (que é a string JSON)
        String json = response.body();
        return json; // Retorna a string JSON
    }
}