package br.com.faria;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
                //sb.append("\n"); // Adicionar caso seja necessário considerar a quedra de linha
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

        return sb.toString();
    }

    /**
     * Função que separa todo texto de acordo com os espaçamentos presentes entre 
     * os textos. De modo a criar objetos que podem ser tratados de diferentes modos
     * de acordo com as regras estabelecidas futuramente
     * 
     * 
     * @param textoCompleto  Texto completo que se deseja separar em Array de String
     * @return               Retorna um array de String que pode ser iteravel
     */
    public String[] separateText(String textoCompleto) {

        // "\s" conjunto composto por caracteres: space, tab e newline
        String[] resultadoConvertido = textoCompleto.split("\\s+");

        return resultadoConvertido;
        //return new String[]{}; // Retorno genérico para o tipo estabelecido
    }


    
}
