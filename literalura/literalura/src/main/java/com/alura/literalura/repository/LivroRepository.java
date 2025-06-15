package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Anotação para indicar que esta interface é um componente de repositório Spring
@Repository
// Estende JpaRepository para fornecer operações CRUD prontas para a entidade Livro,
// onde Livro é o tipo da entidade e Long é o tipo da chave primária (ID)
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Método de consulta personalizado: buscar livro pelo título, ignorando maiúsculas/minúsculas
    Optional<Livro> findByTituloContainsIgnoreCase(String titulo);

    // Método de consulta personalizado: buscar livro pelo ID do Gutendex (ID original da API)
    Optional<Livro> findByGutendexId(Long gutendexId);

    // Método de consulta personalizado: listar livros por idioma
    List<Livro> findByIdioma(String idioma);
}