package Main;
//import Search.Comparator;
//import Search.SearchValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import Books.*;
import GUI.*;

public class Main{
    public static void main(String args[]){

        int tipo;
        String nome;
        ArrayList<String> escritores = new ArrayList<String>();
        String[] esc;
        int ano;
        String idioma;
        ArrayList<String> keywords = new ArrayList<String>();
        String[] keys;
        ArrayList<String> capitulos = new ArrayList<String>();
        String[] caps;
        ArrayList<String> livrarias = new ArrayList<String>();
        String[] liv;
        int colunas;

        String url;
        String formatoArquivo;
        Double duracao;
        String formatoAudio;

        LinkedList<Livro> acervo = new LinkedList<Livro>();
        Scanner scan;
        int cont =  1;
        String path = "src/livros_txt/"+cont + ".txt";
        //Instanciando um objeto File cujo path é a localização dos arquivos do livro
        File file = new File(path);

        
        try{
        	//Enquanto houver arquivos de livro para serem lidos
            while(file.getAbsoluteFile().exists()){
            	//Instanciando um objeto Scanner para ler os dados do arquivo atualmente aberto
                scan = new Scanner(new File(path));
                //Le sequencialmente as informações do livro.
                tipo = scan.nextInt();
                scan.nextLine();
                nome = scan.nextLine();    
                esc = scan.nextLine().split(";");
                ano = scan.nextInt();
                scan.nextLine(); 
                idioma = scan.nextLine();
                keys = scan.nextLine().split(";");
                caps = scan.nextLine().split(";");

                //Atribuindo escritores, keywords e capitulos do livro em coleções.
                for(String s : esc)
                    escritores.add(s);
                for(String s : keys)
                    keywords.add(s);
                for(String s: caps)
                    capitulos.add(s);
               
                //Verifica o tipo de livro para instancia-los na coleção referente ao acervo
                switch(tipo){
                    case 1:{
                        liv = scan.nextLine().split(";");
                        colunas = Integer.parseInt(scan.nextLine());
    
                        for(String s : liv){
                            livrarias.add(s);
                        }
    
                        acervo.add(new Impresso(nome,escritores,ano,idioma,
                        keywords,capitulos,livrarias,colunas));
                        break;
                    }
                    case 2:{
                        url = scan.nextLine();
                        formatoArquivo = scan.nextLine();

                        acervo.add(new Eletronico(nome,escritores,ano,idioma,
                        keywords,capitulos,url,formatoArquivo));
                        break;
                    }
                    case 3:{
                        duracao = Double.parseDouble(scan.nextLine());
                        formatoAudio = scan.nextLine();
                        
                        acervo.add(new AudioBook(nome,escritores,ano,idioma,
                        keywords,capitulos,duracao,formatoAudio));
                        break;
                    }
                    default:
                        System.err.println("Tipo invalido. Livro nao foi lido!");
                }
            /*Atualiza o path contendo o nome do proximo arquivo de livro a ser lido e criando
             um novo File referente a ele. */
            cont++;
            path = "src/livros_txt/"+cont + ".txt";
            scan.close();
            file = new File(path);

            //Limpa as coleções temporárias para que possam armazenar dados de outros livros.
            keywords.clear();
            capitulos.clear();
            escritores.clear();
            livrarias.clear();
            }
    
        }
        catch(NoSuchElementException e){
        	//Caso haja um erro durante a leitura dos dados do livro(formato ou ordem dos dados incorretas)
            System.err.println("O formato do arquivo "+path+" eh inválido!");
        }
        catch(FileNotFoundException e){
        	//Caso o arquivo não exista no diretorio
            System.err.println("Arquivo "+path+" nao encontrado");
        }
        
        //Cria o frame principal, informando seu título
        MainFrame frame = new MainFrame("Trabalho Prático 2 - POO");
        //Passando uma referencia da coleção acervo para o frame.
        frame.setLibrary(acervo);
        //Tornando o frame visível.
        frame.setVisible(true);

    }

}
