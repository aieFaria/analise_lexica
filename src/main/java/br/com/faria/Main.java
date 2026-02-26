package br.com.faria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.faria.enums.Constantes;
import br.com.faria.enums.Tipagem;

public class Main {
    public static void main(String[] args) {
        
        // Inicializando programa, criando texto completo do código
        LeitorTxt leitura = new LeitorTxt();
        String codigo = leitura.bufferTxt(Constantes.PROGRAMA_DIRETORIO);
        EscritaTxt.log("Registros de erro da execução: ["+ LocalDateTime.now() + "]");

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
                Pattern simb = Pattern.compile("(\\$|#|@|\\?|\\!|~)");
                Matcher matcherSIMB = simb.matcher(blocosUnicos[i>=1 ? i-1 : 0]);

                if ( !matcherSIMB.find() ) {
                    
                    Pattern number = Pattern.compile("\\b\\d+(?:\\.\\d+)?\\b");
                    Matcher matcherNUMBER = number.matcher(blocosUnicos[i]);
                    if( matcherNUMBER.find() ) {
                        tokenAux.setLexema(blocosUnicos[i]);
                        tokenAux.setTipo(Tipagem.NUMBER);
                    } else {
                        tokenAux = Token.criarToken(blocosUnicos[i], 1);
                    }

                } else {
                    tokenAux = Token.criarToken(blocosUnicos[i], 1);
                }

                
            }

            //tokenAux = Token.criarToken(blocosUnicos[i]);
            listaTokensUnicos.add(tokenAux);

        }

        // String[] blocosUnicos = leitura.removerDuplicados(listaTokensUnicos.toArray(new String[0]));
        // List<Token> listaAux = new ArrayList<>();

        Token.cadastrarTokens(listaTokensUnicos);

        Token t = new Token();
        EscritaTxt es = new EscritaTxt();
        ; // Lista de tokens atualizados com seus respectivos IDs, foi gerado simultâneamente a tabela de simbolos

        es.geraCodigoTokenizado(blocosCodigo, t.geraTokensFromJson());
        //System.out.println("Olá mundo!");
        
    }
}
