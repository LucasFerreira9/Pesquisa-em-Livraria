package GUI;


import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

//Herda de JPanel. A instancia de MessagePanel contem uma mensagem definida em um JLabel.
public class MessagePanel extends JPanel {

    private JLabel label;

    public MessagePanel(){
        super(new FlowLayout(FlowLayout.LEFT,5,5));

        label = new JLabel("O que gostaria de ler hoje?");
        label.setFont(new Font("Serif", Font.PLAIN, 35));
        
        this.add(label);
    }
}