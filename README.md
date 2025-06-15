Literalura - Catálogo de Livros
Este projeto Java é um catálogo de livros interativo, que permite aos usuários buscar informações sobre livros através da API Gutendex, persistir esses dados em um banco de dados local e realizar diversas consultas.

Objetivo do Desafio
Desenvolver um Catálogo de Livros que ofereça interação textual (via console), buscando livros através da API Gutendex e gerenciando os dados em um banco de dados (H2 em memória por padrão).

Tecnologias Utilizadas
Java 17 (LTS - Long Term Support)
Spring Boot 3.5.0
Spring Data JPA (para persistência de dados)
Hibernate (implementação do JPA)
H2 Database (banco de dados em memória, padrão para desenvolvimento)
Alternativamente, configurado para PostgreSQL caso o usuário deseje um banco de dados persistente.
Maven (gerenciamento de dependências)
Jackson (para processamento de JSON da API)
Lombok (para reduzir o código boilerplate)
API Gutendex: https://gutendex.com/
Funcionalidades do Catálogo
Ao executar a aplicação, o usuário terá as seguintes opções no menu interativo:

Buscar livro por título: Realiza uma busca na API Gutendex pelo título informado e, se encontrado, salva o livro e seu autor no banco de dados. Evita duplicação de livros e autores.
Listar livros registrados: Exibe todos os livros que foram salvos no banco de dados.
Listar autores registrados: Exibe todos os autores que foram salvos no banco de dados.
Listar autores vivos em determinado ano: Busca e lista autores que estavam vivos em um ano específico, com base nos seus anos de nascimento e falecimento registrados.
Listar livros por idioma: Filtra e lista os livros salvos no banco de dados por um código de idioma (ex: en, es, pt, fr).


