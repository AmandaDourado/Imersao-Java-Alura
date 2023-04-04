package alura;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String YELLOW = "\033[0;33m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static String estrela = "🌟";

	public static void main(String[] args) throws Exception {
	
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("./properties/conf.properties");
		prop.load(file);
		String urlPropertyIMDB = prop.getProperty("chave.acesso.api.imdb");
		String urlPropertyNasa = prop.getProperty("chave.acesso.api.nasa");	
		String urlPropertyLinguagens = prop.getProperty("chave.acesso.api.linguagens");	
		
		var http = new ClienteHttp();
		//String json = http.buscaDados(urlPropertyNasa);
		String json = http.buscaDados(urlPropertyLinguagens);
		
		// exibir e manipular os dados
		ExtratorDeConteudo extrator = new ExtratorConteudoIMDB();
		var geradora = new GeradoraDeFigurinhas();
		List <Conteudo> conteudos = extrator.extraiConteudos(json);
		
		for (int i = 0; i < 3; i++) {
			
			Conteudo conteudo = conteudos.get(i);
			
			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeDoArquivo =  "saida/" + conteudo.getTitulo() + ".png";

			geradora.cria(inputStream, nomeDoArquivo);
		
			System.out.println(conteudo.getTitulo());		
			
		}

	}

}