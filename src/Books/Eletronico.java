package Books;
import java.util.ArrayList;

public class Eletronico extends Livro {
    private String url;
    private String formatoArquivo;

    public Eletronico(String nome,ArrayList<String> escritores,int ano,String idioma,ArrayList<String> 
    keywords,ArrayList<String> capitulos,String url,String formatoArquivo){
        super(nome,escritores,ano,idioma,keywords,capitulos);
        this.url = url;
        this.formatoArquivo = formatoArquivo;
        
        super.setTipo("Eletronico");
    }

    public String getUrl(){return url;}
    public String getFormatoArquivo(){return formatoArquivo;}

    public void setUrl(String url){this.url = url;}
    public void setFormatoArquivo(String formatoArquivo){this.formatoArquivo = formatoArquivo;}

    @Override
    public String toString(){
        return (super.toString() + "\n" + url + "\n" + formatoArquivo+ "\n");
    }
}
