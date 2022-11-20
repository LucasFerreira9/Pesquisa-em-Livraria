package GUI;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;

import Books.Livro;


public class ResultsPanel extends JPanel {

    protected JList<String> results;
    DefaultListModel<String> model = new DefaultListModel<>();
    protected LinkedList<Livro> BooksResult;
    protected JButton detailsButton = new JButton("Expandir resultados");
    private ResultsFrame frame;
    protected String searched;
    protected String lastPath = null;
    private ResultsPanel ref = this;
    private JButton limpar = new JButton("Limpar");
    //Panel que contem os botões de expandir resultados e limpar.
    private JPanel buttons = new JPanel(new BorderLayout(0,0));
    
    protected FileWriter fw;
    PrintWriter print; 

    public ResultsPanel(){
    	//Configurando o layout do panel.
        super(new FlowLayout(FlowLayout.LEFT,50,10));
        
        //Instanciando uma JList para armazenar o resultado, cujo construtor recebe uma referencia para um model que controla as mesagens presentes nele
        results = new JList<String>(model);    
        //Configurando tamanho do JList results
        results.setPreferredSize(new Dimension(400,350));
        
        

        //Configurando tamanho dos botões
        detailsButton.setPreferredSize(new Dimension(150,50));
        limpar.setPreferredSize(new Dimension(150,50));
        
        /*
         * 
         * teste de uma borda arredondada.
        detailsButton.setBorder(new Border() {
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				g.drawRoundRect(x, y, width-1, height-1, 20, 20);
			}

			@Override
			public Insets getBorderInsets(Component c) {
				// TODO Auto-generated method stub
				return new Insets(21, 21, 22, 20);
			}

			@Override
			public boolean isBorderOpaque() {
				// TODO Auto-generated method stub
				return true;
			}
        	
        });*/
       
        
        //Adicionando um ouvinte de eventos aos botões.
        HandlerButton handler = new HandlerButton();
        
        detailsButton.addActionListener(handler);
        limpar.addActionListener(handler);
        
        buttons.add(detailsButton,BorderLayout.NORTH);
        buttons.add(limpar,BorderLayout.SOUTH);
        buttons.add(Box.createRigidArea(new Dimension(100,50)));
        buttons.setBackground(new Color(150,114,89));
        
        //Adicionando a cor de fundo do painel.
        this.setBackground(new Color(150,114,89));
        //Adicionando a JList e os JButtons ao painel.
        this.add(results);
        this.add(buttons);
        this.setEmpty();
        this.setVisible(true);
    }

    public void setEmpty(){
        this.model.clear();
    }

    //Adiciona ao ResultsPanel uma referencia para uma coleção de livros retornada como resultado no painel de pesquisa
    public void setResults(LinkedList<Livro> results){
       BooksResult = results;
    }

    //Adiciona a JList o nome de cada um dos livros da coleção de resultados
    public void showResults(){
    	if(BooksResult.isEmpty()) {
    		model.addElement("Nenhum resultado corresponde a pesquisa");
    		return;
    	}
    	
        LinkedList<String> results = new LinkedList<String>();
        ListIterator<Livro> itr = BooksResult.listIterator();
        while(itr.hasNext()){
           results.add(itr.next().getNome());
        }
        model.addAll(results);
    }
  
    
    //Herda da classe ActionListener, define um método que trata o evento de clicar no botão
    public class HandlerButton implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		//Se o botão de limpar foi selecionado
    		if(e.getSource().equals(limpar)){
    			model.clear();
    		}
    		else {
    			if(BooksResult==null) {
        			return;
        		}
        		//Se o resultado é vazio, indica ao usuário que não há resultados para serem expandidos
        		if(BooksResult.isEmpty()) {
        			JOptionPane error = new JOptionPane();
        			error.setMessage("Não há resultados para serem mostrados");
                    error.setMessageType(JOptionPane.ERROR_MESSAGE);
                    JDialog msg = error.createDialog(null, "Error");
                    msg.setVisible(true);
        		}
        		else {
        			//Ao clicar em expandir resultados, cria-se outros frame que contem informações detalhadas dos resultados
        			frame = new ResultsFrame(searched,lastPath);
        			frame.setReferenceToBooksResult(BooksResult);
        			//Adiciona os resultados ao frame de resultados
        			for(Livro livro : BooksResult){
        				frame.addBook(livro);
        			}
        			frame.setReferenceToParentPanel(ref);
        			
        		}
    		}
    		
    	}
    }

}
