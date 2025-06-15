# Literalura - Catálogo de Livros

Este projeto Java é um catálogo de livros interativo, que permite aos usuários buscar informações sobre livros através da API Gutendex, persistir esses dados em um banco de dados local e realizar diversas consultas.

## Objetivo do Desafio

Desenvolver um Catálogo de Livros que ofereça interação textual (via console), buscando livros através da API Gutendex e gerenciando os dados em um banco de dados (H2 em memória por padrão).

## Tecnologias Utilizadas

* **Java 17** (LTS - Long Term Support)
* **Spring Boot 3.5.0**
* **Spring Data JPA** (para persistência de dados)
* **Hibernate** (implementação do JPA)
* **H2 Database** (banco de dados em memória, padrão para desenvolvimento)
    * _Alternativamente, configurado para **PostgreSQL** caso o usuário deseje um banco de dados persistente._
* **Maven** (gerenciamento de dependências)
* **Jackson** (para processamento de JSON da API)
* **Lombok** (para reduzir o código boilerplate)
* **API Gutendex:** `https://gutendex.com/`

## Funcionalidades do Catálogo

Ao executar a aplicação, o usuário terá as seguintes opções no menu interativo:

1.  **Buscar livro por título:** Realiza uma busca na API Gutendex pelo título informado e, se encontrado, salva o livro e seu autor no banco de dados. Evita duplicação de livros e autores.
2.  **Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar autores registrados:** Exibe todos os autores que foram salvos no banco de dados.
4.  **Listar autores vivos em determinado ano:** Busca e lista autores que estavam vivos em um ano específico, com base nos seus anos de nascimento e falecimento registrados.
5.  **Listar livros por idioma:** Filtra e lista os livros salvos no banco de dados por um código de idioma (ex: `en`, `es`, `pt`, `fr`).
0.  **Sair:** Encerra a aplicação.

## Como Executar o Projeto

### Pré-requisitos

* **JDK 17** ou superior instalado (configurado nas variáveis de ambiente).
* **Maven** instalado (geralmente já vem com o IntelliJ).
* **IntelliJ IDEA** (recomendado para abrir e executar o projeto).

### Passos para Rodar a Aplicação

1.  **Clone o Repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd literalura
    ```
    *(Substitua `[URL_DO_SEU_REPOSITORIO]` pela URL do seu repositório Git, se estiver usando um.)*

2.  **Abra no IntelliJ IDEA:**
    * Abra o IntelliJ IDEA.
    * Vá em `File -> Open...` e selecione a pasta raiz `literalura` do projeto. O IntelliJ deve reconhecer automaticamente o projeto Maven.

3.  **Verifique as Dependências:**
    * Aguarde o IntelliJ baixar as dependências do Maven (pode demorar um pouco na primeira vez). Se houver algum problema, verifique a janela "Maven" no IntelliJ e clique em "Reimport All Maven Projects".

4.  **Configuração do Banco de Dados (H2 - Padrão):**
    * O projeto vem configurado para usar o **H2 Database em memória**. Nenhuma configuração extra é necessária para começar, os dados serão perdidos a cada reinício da aplicação.
    * O console H2 pode ser acessado em `http://localhost:8080/h2-console` (se você tiver um `spring-boot-starter-web` adicionado ou configurar uma porta). A URL de JDBC padrão é `jdbc:h2:mem:literaluradb`.

5.  **Configuração do Banco de Dados (PostgreSQL - Opcional):**
    * Se você deseja usar PostgreSQL, certifique-se de ter um servidor PostgreSQL rodando e um banco de dados (`literaluradb` ou o nome que preferir) criado.
    * No arquivo `src/main/resources/application.properties`, comente as linhas do H2 e descomente e ajuste as linhas do PostgreSQL com suas credenciais:
        ```properties
        # Configurações do Banco de Dados PostgreSQL
        # spring.datasource.url=jdbc:postgresql://localhost:5432/literaluradb
        # spring.datasource.username=seu_usuario
        # spring.datasource.password=sua_senha
        # spring.datasource.driver-class-name=org.postgresql.Driver
        # spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
        # spring.jpa.hibernate.ddl-auto=update
        # spring.jpa.show-sql=true
        # spring.jpa.properties.hibernate.format_sql=true
        ```
    * No `pom.xml`, comente a dependência do H2 e descomente a do PostgreSQL.

6.  **Execute a Aplicação:**
    * Abra a classe `src/main/java/com/alura/literalura/LiteraluraApplication.java`.
    * Clique no botão verde de "play" (executar) ao lado do método `main`.
    * A aplicação será iniciada no console do IntelliJ, apresentando o menu de opções.

## Autor

Seu Nome Completo