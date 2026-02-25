package br.com.faria;

import java.util.ArrayList;
import java.util.List;

import br.com.faria.enums.Constantes;

public class Main {
    public static void main(String[] args) {
        
        // Inicializando programa, criando texto completo do código
        LeitorTxt leitura = new LeitorTxt();
        String codigo = leitura.bufferTxt(Constantes.PROGRAMA_DIRETORIO);

        String[] blocosCodigo = leitura.separateText(codigo);
        String[] blocosUnicos = leitura.removerDuplicados(blocosCodigo);

        List<Token> listaTokensUnicos = new ArrayList<>();

        for(int i=0 ; i<blocosUnicos.length ; i++) {

            Token tokenAux = new Token();
            blocosUnicos[i].charAt( blocosUnicos[i].length()-1 );
            if (blocosUnicos[i].charAt(0) == '\'' && blocosUnicos[i].charAt( blocosUnicos[i].length()-1 ) =='\'' ||
                blocosUnicos[i].charAt(0) == '"' && blocosUnicos[i].charAt( blocosUnicos[i].length()-1 ) =='"' ) {

                
                tokenAux = Token.criarToken(blocosUnicos[i].replace("\"", ""), 2);
            } else {
                tokenAux = Token.criarToken(blocosUnicos[i], 1);
            }

            //tokenAux = Token.criarToken(blocosUnicos[i]);
            listaTokensUnicos.add(tokenAux);

        }

        
        Token.cadastrarTokens(listaTokensUnicos);

        Token t = new Token();
        t.geraTokensFromJson();

        System.out.println("Olá mundo!");
        
    }
}
