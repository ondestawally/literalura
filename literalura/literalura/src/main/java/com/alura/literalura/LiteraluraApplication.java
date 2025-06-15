package com.alura.literalura;

import com.alura.literalura.dto.DadosAutor;
import com.alura.literalura.dto.DadosLivro;
import com.alura.literalura.dto.RespostaAPI;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

// Anotação principal de uma aplicação Spring Boot
@SpringBootApplication
// Implementa CommandLineRunner para executar lógica após o início da aplicação Spring
public class LiteraluraApplication implements CommandLineRunner {

	// URL base da API Gutendex
	private static final String ENDERECO = "https://gutendex.com/books/";

	// Injeção de dependências (Spring gerencia a criação e fornecimento dessas instâncias)
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AutorRepository autorRepository;

	private ConsumoAPI consumo = new ConsumoAPI(); // Instancia o serviço de consumo da API
	private ConverteDados conversor = new ConverteDados(); // Instancia o serviço de conversão JSON
	private Scanner teclado = new Scanner(System.in); // Scanner para entrada do usuário

	public static void main(String[] args) {
		// Inicia a aplicação Spring Boot
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	// Método que será executado automaticamente após a inicialização do Spring Boot
	@Override
	public void run(String... args) throws Exception {
		mostrarMenu();
	}

	// Método para exibir o menu e gerenciar as interações do usuário
	private void mostrarMenu() {
		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\n----- LITERALURA - CATÁLOGO DE LIVROS -----");
			System.out.println("1. Buscar livro por título");
			System.out.println("2. Listar livros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Listar livros por idioma");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				opcao = teclado.nextInt();
				teclado.nextLine(); // Consumir a nova linha restante

				switch (opcao) {
					case 1:
						buscarLivroPorTitulo();
						break;
					case 2:
						listarLivrosRegistrados();
						break;
					case 3:
						listarAutoresRegistrados();
						break;
					case 4:
						listarAutoresVivosNoAno();
						break;
					case 5:
						listarLivrosPorIdioma();
						break;
					case 0:
						System.out.println("Saindo do Literalura. Até logo!");
						break;
					default:
						System.out.println("Opção inválida. Tente novamente.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				teclado.nextLine(); // Limpar o buffer do scanner para evitar loop infinito
				opcao = -1; // Resetar opção para garantir que o loop continue
			}
		}
		teclado.close(); // Fechar o scanner ao sair
	}

	// --- Implementações das Opções do Menu ---

	// Opção 1: Buscar livro por título na API e salvar
	private void buscarLivroPorTitulo() {
		System.out.print("Digite o título do livro que deseja buscar: ");
		String tituloBusca = teclado.nextLine();
		String json = consumo.obterDados(ENDERECO + "?search=" + tituloBusca.replace(" ", "%20")); // Substitui espaços para URL

		// Converte a resposta JSON para o DTO RespostaAPI
		RespostaAPI respostaAPI = conversor.obterDados(json, RespostaAPI.class);

		if (respostaAPI != null && respostaAPI.resultados() != null && !respostaAPI.resultados().isEmpty()) {
			DadosLivro dadosLivro = respostaAPI.resultados().get(0); // Pega o primeiro resultado

			// Verifica se o livro já existe no banco pelo gutendexId para evitar duplicação
			Optional<Livro> livroExistente = livroRepository.findByGutendexId(dadosLivro.id());

			if (livroExistente.isPresent()) {
				System.out.println("Livro já cadastrado no banco de dados:");
				System.out.println(livroExistente.get());
			} else {
				Livro livro = new Livro(dadosLivro);

				// Processa o(s) autor(es)
				if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
					DadosAutor dadosAutor = dadosLivro.autores().get(0); // Considera o primeiro autor para simplificar
					Optional<Autor> autorExistente = autorRepository.findByNomeContainsIgnoreCase(dadosAutor.nome());

					Autor autor;
					if (autorExistente.isPresent()) {
						autor = autorExistente.get();
						System.out.println("Autor já existente: " + autor.getNome());
					} else {
						autor = new Autor(dadosAutor);
						autorRepository.save(autor); // Salva o novo autor
						System.out.println("Novo autor salvo: " + autor.getNome());
					}
					livro.setAutor(autor); // Associa o autor ao livro
				} else {
					System.out.println("Autor não encontrado na API para este livro.");
					// Pode criar um autor "Desconhecido" ou lidar de outra forma
				}

				livroRepository.save(livro); // Salva o livro no banco de dados
				System.out.println("Livro salvo com sucesso!");
				System.out.println(livro);
			}
		} else {
			System.out.println("Nenhum livro encontrado com o título: " + tituloBusca);
		}
	}

	// Opção 2: Listar todos os livros registrados no banco de dados
	private void listarLivrosRegistrados() {
		List<Livro> livros = livroRepository.findAll();
		if (livros.isEmpty()) {
			System.out.println("Nenhum livro registrado ainda.");
		} else {
			System.out.println("\n----- LIVROS REGISTRADOS -----");
			livros.forEach(System.out::println);
		}
	}

	// Opção 3: Listar todos os autores registrados no banco de dados
	private void listarAutoresRegistrados() {
		List<Autor> autores = autorRepository.findAll();
		if (autores.isEmpty()) {
			System.out.println("Nenhum autor registrado ainda.");
		} else {
			System.out.println("\n----- AUTORES REGISTRADOS -----");
			autores.forEach(System.out::println);
		}
	}

	// Opção 4: Listar autores vivos em determinado ano
	private void listarAutoresVivosNoAno() {
		System.out.print("Digite o ano para buscar autores vivos: ");
		try {
			int ano = teclado.nextInt();
			teclado.nextLine(); // Consumir a nova linha

			List<Autor> autores = autorRepository.buscarAutoresVivosNoAno(ano);
			if (autores.isEmpty()) {
				System.out.println("Nenhum autor vivo encontrado no ano " + ano + ".");
			} else {
				System.out.println("\n----- AUTORES VIVOS EM " + ano + " -----");
				autores.forEach(System.out::println);
			}
		} catch (InputMismatchException e) {
			System.out.println("Entrada inválida. Por favor, digite um ano válido (número inteiro).");
			teclado.nextLine(); // Limpar o buffer
		}
	}

	// Opção 5: Listar livros por idioma
	private void listarLivrosPorIdioma() {
		System.out.println("Idiomas disponíveis:");
		System.out.println("es - Espanhol");
		System.out.println("en - Inglês");
		System.out.println("fr - Francês");
		System.out.println("pt - Português");
		System.out.print("Digite o código do idioma para buscar livros: ");
		String idiomaBusca = teclado.nextLine().toLowerCase(); // Converte para minúsculas

		List<Livro> livros = livroRepository.findByIdioma(idiomaBusca);
		if (livros.isEmpty()) {
			System.out.println("Nenhum livro encontrado no idioma '" + idiomaBusca + "'.");
		} else {
			System.out.println("\n----- LIVROS NO IDIOMA " + idiomaBusca.toUpperCase() + " -----");
			livros.forEach(System.out::println);
		}
	}
}