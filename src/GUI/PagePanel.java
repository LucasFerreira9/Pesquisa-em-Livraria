package GUI;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;

import Books.*;

//Extende de JPanel, representa uma das páginas que será mostrada ao se expadir os resultados
public class PagePanel extends JPanel{
	
	//contem 4 labels referentes a informações dos 4 livros mostrados por página
	private int numBooks=0;
	private JLabel[] books = new JLabel[4];
	
	public PagePanel() {
		//Configurado um layout de grade para mostrar os livros
		super(new GridLayout(2,2,2,2));
		//Adicionando cor de fundo
		this.setBackground(new Color(171, 147, 112));
		this.setVisible(true);
	}
	
	//Adiciona um livro a página, na posição seguida do próximo
	public void addBook(Livro livro){
		
		//Veirica o tipo de classe derivada instanciada, para adiaionar ao label suas informações.
		if(livro instanceof Impresso) {
			books[numBooks] = new JLabel("<html><p>"
					+ " Nome: "+livro.getNome()+"<br>"
					+ " Tipo: "+livro.getTipo()+"<br>"
					+ " Escritores: "+livro.getCollection(livro.getEscritores())+"<br>"
					+ " Ano: "+livro.getAno()+"<br>"
					+ "Idioma: "+livro.getIdioma()+"<br>"
					+ "Keywords: "+livro.getCollection(livro.getKeywords())+"<br>"
					+ "Capitulos: "+livro.getCollection(livro.getCapitulos())+"<br>"
					+ "Livrarias: "+livro.getCollection(((Impresso)livro).getLivrarias())+"<br>"
					+ "Coluanas: "+((Impresso)livro).getColunas()+"<br></p></html>"
					);
		}
		else if(livro instanceof Eletronico) {
			books[numBooks] = new JLabel("<html><p>"
					+ " Nome: "+livro.getNome()+"<br>"
					+ " Tipo: "+livro.getTipo()+"<br>"
					+ " Escritores: "+livro.getCollection(livro.getEscritores())+"<br>"
					+ " Ano: "+livro.getAno()+"<br>"
					+ "Idioma: "+livro.getIdioma()+"<br>"
					+ "Keywords: "+livro.getCollection(livro.getKeywords())+"<br>"
					+ "Capitulos: "+livro.getCollection(livro.getCapitulos())+"<br>"
					+ "URL: "+((Eletronico)livro).getUrl()+"<br>"
					+ "Formato de Arquivo: "+((Eletronico)livro).getFormatoArquivo()+"<br></p></html>"
					);
		}
		else {
			books[numBooks] = new JLabel("<html><p>"
					+ " Nome: "+livro.getNome()+"<br>"
					+ " Tipo: "+livro.getTipo()+"<br>"
					+ " Escritores: "+livro.getCollection(livro.getEscritores())+"<br>"
					+ " Ano: "+livro.getAno()+"<br>"
					+ "Idioma: "+livro.getIdioma()+"<br>"
					+ "Keywords: "+livro.getCollection(livro.getKeywords())+"<br>"
					+ "Capitulos: "+livro.getCollection(livro.getCapitulos())+"<br>"
					+ "Duracao: "+((AudioBook)livro).getDuracao()+"<br>"
					+ "Formato de Audio: "+((AudioBook)livro).getFormatoAudio()+"<br></p></html>");
					
		}
		books[numBooks].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.add(books[numBooks]);
		numBooks++;
						
	}
	
	public int getNumBooks() {
		return numBooks;
	}
	
}
