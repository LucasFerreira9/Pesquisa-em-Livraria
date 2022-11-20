package GUI;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;
import java.util.LinkedList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Books.*;

//Frame dedicado a mostrar resultados detalhados da busca. Possui uma coleção de panels especializados em mostrar, cada um deles, 4 livros.
public class ResultsFrame extends JFrame {
	
	private ArrayList<PagePanel> pages = new ArrayList<PagePanel>();
	protected String searched;
	private CardLayout layout = new CardLayout();
	private JPanel content = new JPanel(layout);
	private JButton prev = new JButton("Anterior");
	private JButton prox = new JButton("Proximo");
	private JPanel leftButton = new JPanel(new GridLayout(3,1));
	private JPanel rightButton = new JPanel(new GridLayout(3,1));
	private JPanel header = new JPanel(new GridLayout(1,2));
	private JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
	private JButton relatorioButton = new JButton("Gerar relatório");
	private JButton save = new JButton("Save");
	private JLabel message = new JLabel("Resultados");
	private JFileChooser fileChooser = new JFileChooser();
	private ResultsPanel parentPanel;
	
	protected LinkedList<Livro> resultP;
	
	private Color headColor = new Color(208,176,132);
	private Color bodyColor = new Color(171, 147, 112);
	private Color cornersColor = new Color(150,114,89);
	
	private int numPages = 0;
	
	public ResultsFrame(String searched,String lastPath) {
		//Título do frame
		super("Resultados");
		//Tmanho do frame
		this.setSize(900,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.searched = searched;
		
		//configurando a fonte da mensagem
		message.setFont(new Font("Serif", Font.ROMAN_BASELINE, 25));
		
		//adicionando uma meansagem ao subpainel do cabeçalho e os botões de salvar e gerar relatório no outro subpainel do cabeçalho
		headerLeft.add(message);
		headerRight.add(relatorioButton);
		headerRight.add(save);
		//Configurando a cor e fundo 
		headerLeft.setBackground(headColor);
		headerRight.setBackground(headColor);
		
		//Adicionando subpaineis ao painel cabeçalho
		header.add(headerLeft);
		header.add(headerRight);
		
		//Adicionando o painel cabeçalho no topo do frame
		this.add(header,BorderLayout.NORTH);
		
		//Criando um handler tratator de eventos e atribuindo aos botões de avançar na página, retornar na página, salvar e gerar relatório
		HandlerButton handler = new HandlerButton();
		prev.addActionListener(handler);
		prox.addActionListener(handler);
		save.addActionListener(handler);
		relatorioButton.addActionListener(handler);
		
		//Adicionando caixas invisíveis nos paineis laterais afim de centralizar os botões.
		leftButton.add(Box.createHorizontalGlue());
		leftButton.add(prev);
		leftButton.add(Box.createHorizontalGlue());
		leftButton.setBackground(cornersColor);
		
		rightButton.add(Box.createHorizontalGlue());
		rightButton.add(prox);
		rightButton.add(Box.createHorizontalGlue());
		rightButton.setBackground(cornersColor);
		
		//cria uma página 
		createPage();
		content.setBackground(bodyColor);
		
		//Adiciona os paineis laterais nas bordas e o painel principal no centro 
		this.add(leftButton,BorderLayout.WEST);
		this.add(rightButton,BorderLayout.EAST);
		this.add(content,BorderLayout.CENTER);
		
		//Adiciona ao cardlayout o painel principal, que irá se alterar ao clicar nos botões laterais
		layout.show(content, "0");
		
		this.setVisible(true);
		
	}
	
	//Cria uma nova página na coleção de páginas.
	//Adiciona a página ao painel principal que contem um conjunto de outras paginas que podem ser trocadas atraves do cardlayout
	public void createPage(){
		pages.add(new PagePanel());
		numPages++;
		content.add(pages.get(numPages-1),String.valueOf(numPages-1));
	}
	
	//Adiciona um livro a ultima página com espaço da coleção de páginas
	public void addBook(Livro livro){
		if(pages.get(numPages-1).getNumBooks()<4){
			pages.get(numPages-1).addBook(livro);
		}
		else {
			//Se já há quatro livro na página atual, cria-se outra página
			createPage();
			pages.get(numPages-1).addBook(livro);
		}
	}
	
	//Troca de página avnçando
	public void nextPage(){
		layout.next(content);
	}
	//troca de página retornando
	public void previousPage() {
		layout.previous(content);
	}
	//armazena uma referencia a coleção e resultados de livro
	public void setReferenceToBooksResult(LinkedList<Livro> list) {
		resultP = list;
	}
	//Retorna uma string com os dados dos livros de resultado de forma customizada
	public String geraHistorico(){
		String output = "";
		for(Livro l: resultP){
			output += l.toString()+"\n\n";
		}
		return output;
	}
	
	//armazena uma referencia para o painel de resultados
	public void setReferenceToParentPanel(ResultsPanel panel) {
		parentPanel = panel;
	}
	
	//classe para tratar o evento de clicar no botão, implementa a interface ActionListener 
	public class HandlerButton implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Caso um dos dois botões laterais sejam clicados, troca-se a página atual
			if(e.getSource().equals(prev)){
				previousPage();
			}
			else if(e.getSource().equals(prox)) {
				nextPage();
			}
			//Se o botão clicado foi o de gerar um relatório, abre se um browser JFileChooser que possibilita o usuário escolher onde salvar
			else if(e.getSource().equals(relatorioButton)){
				fileChooser.setDialogTitle("Selecione um diretório");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//Se a seleção foi aprovada
				if(fileChooser.showSaveDialog(content)==JFileChooser.APPROVE_OPTION){
					//Retorna o caminho do diretóio selecionado pelo usuário e abre um File neste caminho criando o arquivo relatorio.txt
					//Após, cria-se um FileWriter com o File que foi instanciado e em seguida um printWriter para escrever no arquivo.
					parentPanel.lastPath = new String(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						parentPanel.fw = new FileWriter(new File(parentPanel.lastPath+"/relatorio.txt"));
						parentPanel.print = new PrintWriter(parentPanel.fw);
						parentPanel.print.write("HISTÓRICO: \n\n\n"+"Pesquisa: "+searched+"\n"+ geraHistorico());
						parentPanel.print.close();
						//Mostra uma caixa de diálogo informando que o arquivo foi salvo e sua localização
						JOptionPane messageDialog = new JOptionPane();
		    			messageDialog.setMessage("Relatorio gerado com sucesso em "+parentPanel.lastPath);
		                messageDialog.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		                JDialog msg = messageDialog.createDialog(null, "Tudo ok!");
		                msg.setVisible(true);
						
					}
					//Caso haja algum erro na criação de arquivo, mostra uma mensagem de erro.
					catch(IOException ex) {
						JOptionPane error = new JOptionPane();
		    			error.setMessage("Ocorreu um erro ao criar o arquivo");
		                error.setMessageType(JOptionPane.ERROR_MESSAGE);
		                JDialog msg = error.createDialog(null, "Error");
		                msg.setVisible(true);
					}
					
				}
			}
			else {
				//Se o botão selecionado foi o de salvar, incremente os resultados de pesquisa em um arquivo ja existente
				//cujo camihno foi o ultimo path utilizado para gerar um relatorio
				if(parentPanel.lastPath!=null){
					try {
						
						PrintWriter print = new PrintWriter(new FileOutputStream(new File(parentPanel.lastPath+"/relatorio.txt"),true));
						print.append("Pesquisa: "+searched+"\n"+ geraHistorico());
						print.close();
					}
					//Caso não seja possivel localizar o arquivo, mostra uma mensagem de erro.
					catch(FileNotFoundException ex){
						JOptionPane error = new JOptionPane();
		    			error.setMessage("Não foi possivel encontrar o arquivo "+parentPanel.lastPath+"/relatorio.txt");
		                error.setMessageType(JOptionPane.ERROR_MESSAGE);
		                JDialog msg = error.createDialog(null, "Error");
		                msg.setVisible(true);
					}
					
				}
				else {
					//Se o ultimo diretorio escrito é null, deve-se realizar a primeira escrita, realizando um autoclick no botão de gerar relatório
					relatorioButton.doClick();
				}
				
			}
		}
	}
	
	
	
}
