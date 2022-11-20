package Search;
import Books.*;
import java.util.ArrayList;


//A classe comparator instancia um objeto paa comparar atributos de um livro com determinado valor
public class Comparator{
	//Declarando um tipo enumerado para servir de flag. indica com qual atributo do livro o método estático do objeto irá comparar
    public enum Tipo {NOME,TIPO,ANO,IDIOMA,KEYWORDS,ESCRITORES,
        LIVRARIAS,CAPITULOS};

    Tipo tipo;
    String value;

    //Construtor inicializa o valor a ser comparado e qual atributo do livro será comparado
    public Comparator(Tipo t,String value){
        tipo = t;
        this.value = value.toLowerCase();
    }

    //Método estático que dado um livro, compara seu atributo(especificado no construtor) com value(também especificado no construtor.
    public boolean compare(Livro livro){
        switch(tipo){
            case NOME: return (livro.getNome().toLowerCase().equals(value)); 
            case TIPO: return (livro.getTipo().toLowerCase().equals(value));
            case ANO: return (String.valueOf(livro.getAno()).equals(value));
            case IDIOMA: return (livro.getIdioma().toLowerCase().equals(value));
            case KEYWORDS: {
                ArrayList<String> keys = livro.getKeywords();
                for(int i=0;i<keys.size();i++){
                    if(keys.get(i).toLowerCase().equals(value))
                        return true;
                }
                return false;
            }
            case ESCRITORES:{
                ArrayList<String> esc = livro.getEscritores();
                for(int i=0;i<esc.size();i++){
                    if(esc.get(i).toLowerCase().equals(value))
                        return true;
                }
                return false;
            }
            case LIVRARIAS:{
                if(!(livro instanceof Impresso)) {
                    return false;
                }
                ArrayList<String> liv = ((Impresso)livro).getLivrarias();
                for(int i=0;i<liv.size();i++){
                    if(liv.get(i).toLowerCase().equals(value))
                        return true;
                }
                return false;
            }
            
            default: return false;

        }

    }
}
