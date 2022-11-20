package Search;
import java.util.*;

import Books.*;

public class SearchValue{

	//Método estático para pesquisar em uma coleção livros com um valor específico de atributo, cujo tipo é dado por um objeto Comparator
    public static LinkedList<Livro> search(Comparator comp,LinkedList<Livro> collection){
    	//Criando uma coleção vazia para armazenar os resultados
        LinkedList<Livro> results = new LinkedList<Livro>();
        //Criando um iterator para apontar para os livros da coleção
        ListIterator<Livro> itr = collection.listIterator();
        Livro livro;
        //Enquanto houver livro a serem percorridos na coleção
        while(itr.hasNext()){
        	//Le o proximo livro
            livro = itr.next();
            //verifica se ele cumpre o requisito estabelecido pelo compare do Objeto Comparator
            if(comp.compare(livro)){
            	//Adiciona o livro aos resultados
                results.add(livro);
                itr.remove();
            }
        }
        return results;
    } 
    
    //Dado uma coleção, retorna apenas livros impressos
    public static LinkedList<Livro> getImpressos(LinkedList<Livro> collection){
    	LinkedList<Livro> results = new LinkedList<Livro>();
    	Iterator<Livro> itr = collection.iterator();
    	Livro livro;
    	while(itr.hasNext()) {
    		livro = itr.next();
    		if(livro instanceof Impresso)
    			results.add(livro);
    		
    		
    	}
    	return results;
    }
    //Dado uma coleção, retorna uma coleção apenas de Eletronicos
    public static LinkedList<Livro> getEletronicos(LinkedList<Livro> collection){
    	LinkedList<Livro> results = new LinkedList<Livro>();
    	Iterator<Livro> itr = collection.iterator();
    	Livro livro;
    	while(itr.hasNext()) {
    		livro = itr.next();
    		if(livro instanceof Eletronico)
    			results.add(livro);
    		
    		
    	}
    	return results;
    }
    //Dado uma coleção, retorna uma coleção apenas de impressos.
    public static LinkedList<Livro> getAudioBooks(LinkedList<Livro> collection){
    	LinkedList<Livro> results = new LinkedList<Livro>();
    	Iterator<Livro> itr = collection.iterator();
    	Livro livro;
    	while(itr.hasNext()) {
    		livro = itr.next();
    		if(livro instanceof AudioBook)
    			results.add(livro);
    		
    		
    	}
    	return results;
    }
}


