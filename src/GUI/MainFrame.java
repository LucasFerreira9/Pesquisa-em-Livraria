package GUI;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.util.*;
import Books.Livro;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MainFrame extends JFrame{

	//Referencia para o painel cabeçalho
    private HeaderPanel header;
    //referencia para o painel principal(contem 3 subpaineis)
    private ContentPanel content;
    //Referencia para o acervo de livros
    protected LinkedList<Livro> acervo;
    
    //Referencia para o painel rodapé(mostra resultados)
    protected ResultsPanel results;
    //Referencia para o scrool da Jlist dos resultados
    protected JScrollPane scrool;

    public MainFrame(String title){
        
    	//Título da aplicaçao
        this.setTitle(title);

        //Instanciando os Panels.
        header = new HeaderPanel();
        content = new ContentPanel();
        results = new ResultsPanel();

        //Encerra o programa quando o frame principal é fechada
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Configurando tamanho do frame
        this.setSize(1000, 800);
        //Centraliza O frame
        this.setLocationRelativeTo(null);
        
        //Colocando nos atributos do painel de pesquisa uma referencia para o painel de filtros e o painel de resultados, assim como a referecia ao frame principal
        this.content.searchP.setReferenceToFilterPanel(this.content.filterP);
        this.content.searchP.setReferenceToResultPanel(this.results);
        this.content.searchP.setParentFrame(this);
        
        //Adiciona o scroll a JList de resultados do painel resultados
        scrool = new JScrollPane(this.results.results);
        scrool.setPreferredSize(new Dimension(450,240));
        results.add(scrool,FlowLayout.LEFT);
       
        //Adiciona os JPanels ao frame principal
        this.add(header,BorderLayout.NORTH);
        this.add(content,BorderLayout.CENTER);
        this.add(results,BorderLayout.SOUTH);
    }
    
    //Adiciona uma referencia ao acervo no frame.
    public void setLibrary(LinkedList<Livro> list) {
    	acervo = list;
    }

}
