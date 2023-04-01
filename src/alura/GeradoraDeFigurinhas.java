package alura;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

		// leitura da imagem
		// InputStream inputStream = new FileInputStream(new
		// File("entrada/filme-maior.jpg"));
		// InputStream inputStream = new
		// URL("https://assets.mycast.io/characters/trevor-spacey-7144920-nordomal.jpg?1655853528%22).openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// cria nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		// criando imagem do joinha
		BufferedImage imagemJoinha = ImageIO.read(new File("./joinha/joinha.png"));	

		// copiar a imagem original para nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// configurar a fonte
		var fonte = new Font("Comic Sans MS", Font.BOLD, 64);
		graphics.setColor(Color.magenta);
		graphics.setFont(fonte);
			
		String textoDaImagem = "topzera";
		
		// centralizando o texto da imagem
		var center = largura/2 - (textoDaImagem.length() * 20);  
        graphics.drawString(textoDaImagem, center , novaAltura - 100);
        
        // inserindo borda na imagem
        graphics.setColor(Color.WHITE);
        graphics.drawRect(0,0, novaImagem.getWidth() - 1,novaImagem.getHeight() - 1);
        
		// inserindo o joinha na imagem
		int alturaJoinha = altura - 5;
		graphics.drawImage(imagemJoinha, 0, alturaJoinha, null);
		
		// escrever a imagem nova em um arquivo
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
		
	}	
}
