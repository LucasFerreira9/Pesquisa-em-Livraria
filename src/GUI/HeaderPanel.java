package GUI;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;

public class HeaderPanel extends JPanel{
    private JLabel titulo;
    private JLabel logo;
    private ImageIcon img;

    
    public HeaderPanel(){
    	//Utilizando constutor pai para configurar um flowlayout para o panel.
        super(new FlowLayout(FlowLayout.LEFT,10,5));

        //Carregando uma imagem de logo dos arquivos do projeto.
        img = new ImageIcon(this.getClass().getResource("logo.png"));
        //Redimensionando o tamanho da imagem 100X100
        img = resizeImg(img, 100, 100);
        //Criando um label com a imagem 
        logo = new JLabel(img);
        
        //Instanciando um label com o título da aplicação 
        titulo = new JLabel("LIBRARY SEARCH TOOL");
        //Configurando tamanho e fonte do texto do label
        titulo.setFont(new Font("Serif", Font.PLAIN, 20));
        
        //Adicionando cor de fundo ao HeaderPanel
        this.setBackground(new Color(208,176,132));
        //Adionando logo e título ao HeaderPanel
        this.add(logo);
        this.add(titulo);
        //Adicionando borda ao painel.
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2));
        this.setVisible(true);
    }

    //Dado um ImageIcon e um tamanho, o método redimensiona a imagem reescrevendo a referencia do objeto original
    private ImageIcon resizeImg(ImageIcon img,int w,int h){
        Image i = img.getImage().getScaledInstance(w, h,Image.SCALE_SMOOTH);
        return (new ImageIcon(i));
    }
}
