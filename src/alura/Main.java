package alura;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String YELLOW = "\033[0;33m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static String estrela = "🌟";

	public static void main(String[] args) throws Exception {
		
		// pegando a chave da API no arquivo de configurações
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./properties/conf.properties");
		prop.load(file);
		
		 // fazer uma conexão HTTP e buscar os filmes mais popular
		String url = prop.getProperty("chave.acesso.api");
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		// extrair só os dados que interessam (titulo,poster e classificacao)
		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);

		// exibir e manipular os dados
		for (Map<String, String> filme : listaDeFilmes) {
			
			String urlImagem = filme.get("image");
			String titulo = filme.get("title");
			
			InputStream inputStream = new URL(urlImagem).openStream();
			String nomeDoArquivo = titulo + ".png";
			
			var geradora = new GeradoraDeFigurinhas();
			geradora.cria(inputStream, nomeDoArquivo);
		
			System.out.println(titulo);		
		}

	}

}
