package Books;
import java.util.ArrayList;

public class Impresso extends Livro{
    private ArrayList<String> livrarias = new ArrayList<String>();
    int colunas;

    public Impresso(String nome,ArrayList<String> escritores,int ano,String idioma,
    ArrayList<String> keywords,ArrayList<String> capitulos,ArrayList<String> livrarias,int colunas){
        super(nome,escritores,ano,idioma,keywords,capitulos);
        this.livrarias.addAll(livrarias);
        this.colunas = colunas;
        
        super.setTipo("Impresso");
    }

    public ArrayList<String> getLivrarias(){return livrarias;}
    public int getColunas(){return colunas;}

    public void setLivrarias(ArrayList<String> livrarias){this.livrarias.addAll(livrarias);}
    public void setColunas (int colunas){this.colunas = colunas;}

    public String toString(){
        return (super.toString() + "\n" + getCollection(livrarias) + "colunas" + "\n");
    }
}
