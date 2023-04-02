package alura;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record ExtratorConteudoIMDB() implements ExtratorDeConteudo{

	@Override
	public List<Conteudo> extraiConteudos(String json) {
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);
		
		List<Conteudo> conteudos = listaDeAtributos.stream().flatMap(atributo -> 
		Stream.of(new Conteudo(atributo.get("title"),atributo.get("image")))).collect(Collectors.toList());
		
		return conteudos;
	}

}
