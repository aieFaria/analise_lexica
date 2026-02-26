package br.com.faria;

import java.io.FileWriter;
import java.util.List;

import br.com.faria.enums.Constantes;
import br.com.faria.enums.Tipagem;

public class EscritaTxt {

    private List<Token> tokensCorretos;


    /**
     * Método para geração do .pixobj a partir do código de entrada e da lista de tokens já atualizada
     * com os IDs gerados na tabela de simbolos
     * 
     * @param codigo  Código dividido em array cujo cada pedaço está desmenbrado para facilitar iterações
     *                sobre todos os itens tokenizáveis
     * @param tokens  Lista de tokens, a referencia da Lista gerada simultâneamente a tabela de simbolos
     *                visto que assim todos Token possuiram ID.
     */
    public void geraCodigoTokenizado(String[] codigo, List<Token> tokens) {

        // Garante que a lista de tokens pode ser usada em outros métodos desta mesma classe
        this.tokensCorretos = tokens; 

        int cont = 0;

        try {

            FileWriter writeFile = new FileWriter(Constantes.PIXOBJ_DIRETORIO);

            for (String stmt : codigo) {
                
                // Escrita base do .pixobj
                // stmt deve ser subistituído pelo seu respectivo token
                writeFile.write(" " +substituirToken(stmt)+ " ");
                //writeFile.write(" " +stmt+ " ");

                // Regra de quebra de linha a cada 7 iterações
                if(cont%7 == 0) {
                    writeFile.write("\n");
                    cont = 0;
                }
                    
                cont++;
            }

            writeFile.close();
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
        
        // Ideia: gerar tokens consumiveis para correta ligação

    }

    /**
     * Método privado, visto que seu uso éxclusivo desta classe.
     * Serve para substituir o código, um texto, em seu respectivo token basando-se no atributo
     * desta classe {@link tokensCorretos}.
     * 
     * Uma das lógicas implementadas foi o consumo de tokens para evitar casos de IDs duplicados
     * para objetos igual em conteúdo porém não estam alocados no mesmo espaço de memória.
     * 
     * @param str
     * @return
     */
    private Token substituirToken(String str) {

        for (Token tk : this.tokensCorretos) {

            if( tk.getLexema().equals(str.replace("\"", "")) ) {

                if(tk.getTipo() == Tipagem.TEXTO_LITERAL ) {
                    //|| tk.getTipo() == Tipagem.NUMBER
                    tokensCorretos.remove(tk);
                }

                return tk;
            }

        }

        return new Token();
    }

}
