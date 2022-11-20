package Search;
import java.util.*;
import Books.Livro;


//Classe possui um método estático para pesquisar por livros através de um termo geral(não específico)
public class SearchAll{
	public static LinkedList<Livro> searchAll(LinkedList<Livro> l, String s){
		
	//Verificando se a string corresponde a um valor numérico, o que indica que a pesquisa deve ser por ano
	if(s.matches("-?\\d+(\\.\\d+)?")) {
		return SearchValue.search(new Comparator(Comparator.Tipo.ANO,s),l);
	}
	else {
		//Cria uma coleção de livros resultado para ser retornada.
		LinkedList<Livro> results = new LinkedList<Livro>();
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.NOME,s),l));
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.TIPO,s),l));
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.IDIOMA,s),l));
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.ESCRITORES,s),l));
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.KEYWORDS,s),l));
		results.addAll(SearchValue.search(new Comparator(Comparator.Tipo.LIVRARIAS,s),l));
		
		return results;
	}
	
	}
}
