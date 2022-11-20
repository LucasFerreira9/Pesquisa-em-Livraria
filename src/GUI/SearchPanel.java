package GUI;

import Books.Livro;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Search.SearchAll;
import Search.SearchValue;
import Search.Comparator;
import java.util.LinkedList;


public class SearchPanel extends JPanel {

	//referencia para a barra de pesquisa
    private JTextField sbar;
    //referencia para o botão de pesquisa
    private JButton sbutton;
    //referencia para um icone de lupa para ser inserido ao botão
    private Icon imgButton;
    //insyanciando uma caixa de diálogo para indicar um erro
    private JOptionPane error = new JOptionPane();
    //Referencia para o painel de filtragem, resultados e o frame principal.
    private FilterPanel filterP;
    private ResultsPanel resultP;
    private MainFrame frame;
    
    
    public SearchPanel(){
    	//Configurando o layout de searchPanel como uma linha horizontal.
    	super(new FlowLayout(FlowLayout.CENTER,5,5));
    	//instanciando uma campo de inserção texto onde será digitada a palavra de pesquisa
        sbar = new JTextField(40);
        //Configurando tamanho e fonte do texto inserido na barra de pesquisa
        sbar.setFont(new Font("Serif", Font.PLAIN, 22));

        //Carregando a imagem de lupa dos arquivos do projeto.
        imgButton = new ImageIcon(this.getClass().getResource("search.png"));
        //Adicionando a imagem ao botão
        sbutton = new JButton(imgButton);  
        
        //Adicionando um tratador de eventos para o botão de pesquisar
        sbutton.addActionListener(new Handler());

        //Adicionando a barra de pesquisa e o botão ao SearchPanel.
        this.add(sbar);
        this.add(sbutton);
        this.setVisible(true);
    }
    
    //Cria no panel uma referencia a outros panels importantes de serem acessados neste escopo
    public void setReferenceToFilterPanel (FilterPanel f)
    {
    	filterP = f;
    }
    public void setReferenceToResultPanel(ResultsPanel r) {
    	resultP = r;
    }
    public void setParentFrame(MainFrame m) {
    	frame = m;
    }

    //Classe ouvinte de eventos, implementa a interface ACtionListener, usada para detectar clicks dentre outras coisas
    public class Handler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent click){
        	//Se a barra de pesquisa está vazia, mostra uma caixa de diálogo indicando erro
            if(sbar.getText().equals("")){
                error.setMessage("Digite uma entrada valida!");
                error.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog msg = error.createDialog(null, "Error");
                msg.setVisible(true);
            }
            else{
            	//Cria uma coleção para armazenar quais livros serão pesquisados com base no tipo selecionado
            	LinkedList<Livro> toSearch = new LinkedList<Livro>();
           
            	
            	//Verifica qual tipo está selecionado na caixa de checagem para adicionar todos os livros deste tipo na coleção a ser pesquisada
            	if(filterP.impresso.isSelected())
            		toSearch.addAll(SearchValue.getImpressos(frame.acervo));
            	if(filterP.eletronico.isSelected())	
            		toSearch.addAll(SearchValue.getEletronicos(frame.acervo));
            	if(filterP.audioBook.isSelected())	
            		toSearch.addAll(SearchValue.getAudioBooks(frame.acervo));
            	
            	//Se a coleção é fazia, então nenhuma caixa está selecionada. neste caso, faz-se a pesquisa acima de todos os livros do acervo
            	if(toSearch.isEmpty())
            		toSearch.addAll(frame.acervo);
            	
            	//Retorna a posição do texto selecionado dentro da JComboBox(caixa de seleção dentro do FilterPanel)
            	int option = filterP.select.getSelectedIndex();
            	
            	//Para cada opção selecionada, é invocado o método searh cujo que utiliza um comparator específico a coleção a ser pesquisada
            	//O resultado do método é adicionado na coleção resultados que se encontra no ResultsPanel
            	
            	switch(option) {
            		case 0:{
            			//caso esteja selecionado a opção "tudo", invoca-se o método searchAll.
            			resultP.setResults(SearchAll.searchAll(toSearch,sbar.getText()));
            			break;
            		}
            		case 1:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.NOME,sbar.getText()), toSearch));
            			break;
            		}
            		case 2:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.TIPO,sbar.getText()), toSearch));
            			break;
            		}
            		case 3:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.ESCRITORES,sbar.getText()), toSearch));
            			break;
            		}
            		case 4:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.ANO,sbar.getText()), toSearch));
            			break;
            		}
            		case 5:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.IDIOMA,sbar.getText()), toSearch));
            			break;
            		}
            		case 6:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.KEYWORDS,sbar.getText()), toSearch));
            			break;
            		}
            		case 7:{
            			resultP.setResults(SearchValue.search(new Comparator(Comparator.Tipo.LIVRARIAS,sbar.getText()), toSearch));
            			break;
            		}
            	}
            	
            	resultP.searched = sbar.getText();
            	resultP.showResults();
            	
            }
        }
    }
    
}