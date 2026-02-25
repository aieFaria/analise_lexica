package br.com.faria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeitorTxt {

    /**
     * Função que realizará leitura do arquivo .txt e converterá em String manipulável
     * 
     * @param nomeArquivo  Nome de referência do arquivo que será lido
     * @return             Arquivo de texto convertido para String
     */
    public String bufferTxt(String nomeArquivo) {

        StringBuilder sb = new StringBuilder();
        
        try {

            FileReader arquivoReader = new FileReader(nomeArquivo);
            BufferedReader bReader  = new BufferedReader(arquivoReader);
            String texto;

            // Faz a leitura linha por linha enquanto houver conteúdo
            // presente no arquivo .txt
            while ( (texto = bReader.readLine()) != null ) {
                sb.append(texto);
                sb.append("\n"); // Adicionar caso seja necessário considerar a quedra de linha
            }

            bReader.close();

        } catch (FileNotFoundException notFound) {

            // Imprimindo messagem de erro utilizando outro método
            // System.out.println( notFound.getMessage() );
            sb.append( notFound.getMessage() );

        } catch (IOException e) {

            // Imprimindo messagem de erro para análise
            e.printStackTrace();

        }
        finally {
            // Bloco que sempre é executado independentemente de erros capturados no try-catch
            System.out.println("Saiu do try");
        }

        return sb.toString().trim();
    }

    /**
     * Função que separa todo texto de acordo com os espaçamentos presentes entre 
     * os textos. De modo a criar objetos que podem ser tratados de diferentes modos
     * 
     * 
     * @param textoCompleto  Texto completo que se deseja separar em Array de String
     * @return               Retorna um array de String que pode ser iteravel
     */
    public String[] separateText(String textoCompleto) {

        // "\s" conjunto composto por caracteres: space, tab e newline
        String[] resultadoConvertido;

        // Lista encadeada que mantém elementos por ordem de adição
        LinkedList<String> lista = new LinkedList<>();

        // Expressão regular para encontrar grupos quaisquer de Caracteres
        String delimitacoes = "\"[^\"]*\"|'[^']*'|\\S+";
        
        Pattern pattern = Pattern.compile(delimitacoes);
        Matcher matcher = pattern.matcher(textoCompleto);
        
        while (matcher.find()) {
            lista.add(matcher.group());
        }

        System.out.println( lista.contains("delimitacoes") );
        lista.remove(0); // é possivel remover elemento com base em index

        resultadoConvertido = lista.toArray(new String[0]);

        return resultadoConvertido;
        //return new String[]{}; // Retorno genérico para o tipo estabelecido
    }

    /**
     * Método para geração de array com elementos exclusivos com base nas regras estabelecidas 
     * para uso na chamada da função Token.cadastrarToken( @param array ) e dessa forma 
     * gerar IDs únicos para cada token
     * 
     * @param arrayString  Parametro provindo do método {@link separateText}
     * 
     * @return             Retorna um String[], array de String, que não é por obrigação ordenado
     *                     pois sua função é entregar um array sem elementos duplicados.
     */
    public String[] removerDuplicados(String[] arrayString) {

        List<String> listaRetorno = new ArrayList<>(); 

        for(int i=0; i<arrayString.length ; i++) {

            // arrayString[i].length();
            // arrayString[i].charAt(0); //Pegando primeiro caractere
            // arrayString[i].charAt(arrayString[j].length()-1); //Pegando ultimo caractere

            // Condicional para garantir que cada texto literal seja único, String ou Chave Pix
            if(arrayString[i].charAt(0) == '\'' && arrayString[i].charAt(arrayString[i].length()-1) == '\'' ||
                arrayString[i].charAt(0) == '\"' && arrayString[i].charAt(arrayString[i].length()-1) == '\"') {

                listaRetorno.add(arrayString[i]);
                continue;
            }
            
            // Caso elemento já esteja na lista pule para próxima iteração
            // do contrário adicione na lista
            if (listaRetorno.contains(arrayString[i])) {
                continue;
            } else {
                listaRetorno.add(arrayString[i]);
            }
            

        }

        

        return listaRetorno.toArray(new String[0]);

    }

    
}
