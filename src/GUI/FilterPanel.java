package GUI;


import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;

public class FilterPanel extends JPanel{

	//Vetor de string contendo as possibilidades de seleção de filtos no JComboBox
    private String[] possibilities = {"Tudo","Nome","Tipo","Escritores","Ano","Idioma","Keywords","Livrarias"};
    //Criando uma referencia a caixasde checagem para filtrar por tipo
    protected JCheckBox impresso;
    protected JCheckBox eletronico;
    protected JCheckBox audioBook;
    //Criando uma caixa de seleção com as possibilidades de filtragem.
    protected JComboBox<String> select = new JComboBox<String>(possibilities);
    //Referenciando labels para mostrar mensagens.
    private JLabel msg;
    private JLabel msg2;

    //Instanciando um objeto que representa uma cor no sistema rgb.
    private Color bg = new Color(171, 147, 112);

    public FilterPanel(){
    	//Utlizando o construtor da classe pai (JPanel) para configurar o layout FlowLayout(linha horizontal com espaçamento 5,10
        super(new FlowLayout(FlowLayout.CENTER,5,10));

        //Inicializando objetos
        impresso = new JCheckBox("Impresso");
        eletronico = new JCheckBox("Eletronico");
        audioBook = new JCheckBox("Audio Book");
        
        //inicializand labels e configurando tamanho e fonte.
        msg = new JLabel("Filtrar por tipo:");
        msg.setFont(new Font("Serif", Font.BOLD, 15));
        msg2 = new JLabel("Pesquisar por:");
        msg2.setFont(new Font("Serif", Font.BOLD, 15));
        
        //Adicionando cor d efundo as caixas de checagem.
        impresso.setBackground(bg);
        eletronico.setBackground(bg);
        audioBook.setBackground(bg);

        //Configurando o tamanho da caixa de seleção
        select.setPreferredSize(new Dimension(100,30));
        
        //Adicionando os componentes ao FilterPanel
        this.add(msg);
        this.add(impresso);
        this.add(eletronico);
        this.add(audioBook);
        this.add(Box.createHorizontalGlue());
        this.add(msg2);
        this.add(select);

    }

   
}