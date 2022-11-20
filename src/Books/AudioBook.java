package Books;
import java.util.ArrayList;

public class AudioBook extends Livro {
    private double duracao;
    private String formatoAudio;

    public AudioBook(String nome,ArrayList<String> escritores,int ano,String idioma,
    ArrayList<String> keywords,ArrayList<String> capitulos,double duracao,String formatoAudio){
        super(nome,escritores,ano,idioma,keywords,capitulos);
        this.duracao = duracao;
        this.formatoAudio = formatoAudio;
        
        super.setTipo("AudioBook");
    }

    public double getDuracao(){return duracao;}
    public String getFormatoAudio(){return formatoAudio;}
    
    public void setDuracao(double duracao){this.duracao = duracao;}
    public void setFormatoArquivo(String formatoAudio){this.formatoAudio = formatoAudio;}

    @Override 
    public String toString(){
        return (super.toString() + "\n" + duracao + "\n" + formatoAudio);
    }
}
