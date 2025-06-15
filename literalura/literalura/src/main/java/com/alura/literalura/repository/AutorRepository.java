package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Anotação para indicar que esta interface é um componente de repositório Spring
@Repository
// Estende JpaRepository para fornecer operações CRUD prontas para a entidade Autor,
// onde Autor é o tipo da entidade e Long é o tipo da chave primária (ID)
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Método de consulta personalizado: buscar autor pelo nome exato
    Optional<Autor> findByNome(String nome);

    // Método de consulta personalizado: buscar autor pelo nome, ignorando maiúsculas/minúsculas
    Optional<Autor> findByNomeContainsIgnoreCase(String nome);

    // Método de consulta personalizado usando JPQL (Java Persistence Query Language)
    // Busca autores que estavam vivos em um determinado ano
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> buscarAutoresVivosNoAno(Integer ano);
}