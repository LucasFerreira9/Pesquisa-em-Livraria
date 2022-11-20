package Books;
import java.util.ArrayList;

public class Livro{
	private String tipo;
    private String nome;
    private ArrayList<String> escritores = new ArrayList<String>();
    private int ano;
    private String idioma;
    private ArrayList<String> keywords = new ArrayList<String>();
    private ArrayList<String> capitulos = new ArrayList<String>();

    public Livro(String nome,ArrayList<String> escritores,int ano,String idioma,ArrayList<String> keywords,ArrayList<String> capitulos){
        this.nome = nome;
        this.escritores.addAll(escritores);
        this.ano = ano;
        this.idioma = idioma;
        this.keywords.addAll(keywords);
        this.capitulos.addAll(capitulos);
    }
    public Livro(){}

    public String getTipo(){return tipo;}
    public String getNome(){return nome;}
    public ArrayList<String> getEscritores(){return escritores;}
    public int getAno(){return ano;}
    public String getIdioma(){return idioma;}
    public ArrayList<String> getKeywords(){return keywords;}
    public ArrayList<String> getCapitulos(){return capitulos;}

    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setNome(String nome){this.nome = nome;}
    public void setEscritores(ArrayList<String> escritores){this.escritores.addAll(escritores);}
    public void setAno(int ano){this.ano=ano;}
    public void setIdioma(String idioma){this.idioma = idioma;}
    public void setKeywords(ArrayList<String> keywords){this.keywords.addAll(keywords);}
    public void setCapitulos(ArrayList<String> capitulos){this.capitulos.addAll(capitulos);}
    public String getCollection (ArrayList<String> colecao)
    {
        String r = "";
        for(int p = 0; p<colecao.size();p++)
        {
            r += (colecao.get(p) + "; ");
        }
        return r; 
    }

    @Override 
    public String toString(){
        return (nome+"\n" + getCollection(escritores) + "\n" + ano + "\n" + idioma + "\n" +  
        getCollection(keywords) + "\n" + getCollection(capitulos) +"\n");
    }


}
