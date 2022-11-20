package GUI;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;


public class ContentPanel extends JPanel {

	//Criando um layout do tipo GridLayout com 4 linhas e uma coluna
    private GridLayout layout = new GridLayout(4,1,0,0);
    //Criando um painel para mostrar uma mensagem.
    private MessagePanel p = new MessagePanel();
    //criando um painel de pesquisa(contem barra de pesquisa e botão pesquisar)
    protected SearchPanel searchP = new SearchPanel();
    //Criando um painel que contem os componentes de filtragem de pesquisa
    protected FilterPanel filterP = new FilterPanel();
    //Criando um label com a mensagem resultados
    private JLabel msg = new JLabel(" Resultados:");
    //Instanciando um Color que representa uma cor, dado por um valor em rgb.
    private Color color = new Color(171, 147, 112);

    public ContentPanel(){

    	//Adicionando o layout ao painel
        this.setLayout(layout);
      //Configurando a cor de fundo do painel.
        this.setBackground(color);
        //Configurando a fonte da mensagem
        msg.setFont(new Font("Serif", Font.LAYOUT_LEFT_TO_RIGHT, 30));


        //Configurado a cor de fundo dos subpaineis.
        p.setBackground(color);
        searchP.setBackground(color);
        filterP.setBackground(color);
        msg.setBackground(color);

        //Adicionando os subpaineis ao painel ContentPanel.
        this.add(p);
        this.add(searchP);
        this.add(filterP);
        this.add(msg);
        this.setVisible(true);    
        
    }
}
