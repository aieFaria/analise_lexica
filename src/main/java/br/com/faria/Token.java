package br.com.faria;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.faria.enums.Tipagem;
import br.com.faria.enums.Constantes;

public class Token extends Object {

    // Declaração dos atributos do Token
    private Tipagem tipo;
    private String lexema;
    private String idLexema;

    /**
     * Método para geração de objetos do tipo Token para utiliza-los no código
     * 
     * Precisamos definir -> 
     * #1 A função terá algum tipo de retorno? Se sim qual? 
     * R.:
     * #2 As regras para manipular os tokens que seram gerados depois. Devemos 
     * interferir nos tokens 'padrao' ou apenas gerar novos em 'outros'?
     * R.:
     * 
     * 
     * @author Gabriel Faria
     */
    @SuppressWarnings("unchecked")
    public void geraTokensFromJson() {

        JSONParser parser = new JSONParser();

        try {

            FileReader reader = new FileReader(Constantes.TOKENS_DIRETORIO);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray tokensPadrao = (JSONArray) jsonObject.get("padrao");
            //System.out.println(tokensPadrao.get(1));

            for (Object objRegra: tokensPadrao) {

                if (objRegra != null) {
                    
                    JSONObject regra = (JSONObject) objRegra;
                    String tipo = (String) regra.get("tipo");
                    List<String> lexema = (List<String>) regra.get("lexema");

                    for(String lexemaDividido : lexema) {

                        // Gerar o objeto da classe Token dentro desse for
                        System.out.println(tipo);
                        System.out.println(lexemaDividido);
                    }

                    //System.out.println(lexema);

                } else {
                    // Erro lançado quando faça leitura de objeto nulo
                    throw new IndexOutOfBoundsException("Leitura de um objeto vazio"); 
                }
                
            }
            System.out.println();

            JSONArray tokensOutros = (JSONArray) jsonObject.get("outros");
            System.out.println(tokensOutros.size());
            //System.out.println(tokensOutros);

            for (Object objRegra : tokensOutros) {

                if (objRegra != null) {

                    JSONObject regra = (JSONObject) objRegra;
                    String tipo = (String) regra.get("tipo");
                    List<String> lexema = (List<String>) regra.get("lexema");

                    for(String lexemaDividido : lexema) {

                        // Gerar o objeto da classe Token dentro desse for
                        System.out.println(tipo);
                        System.out.println(lexemaDividido);
                    }

                } else {
                    // Erro lançado quando faça leitura de objeto nulo
                    throw new IndexOutOfBoundsException("Leitura de um objeto vazio");
                }

            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Criar verificação de um item qualquer cujo objetivo é descobrir se é
     * possivel gerar um token referente a ele. 
     * 
     * Onde ele buscará a referencia para verificar? Uma possibilidade é do arquivo
     * {@hiperlink #tokens.json}, Outra seria definir aqui mesmo as regras através do enum 
     * {@hiperlink #Tipagem.java}.
     * 
     * Onde devemos posicionar essa verificação, em qual classe faremos melhor proveito de
     * sua utilizade?
     * 
     * 
     * @param param  Parâmetro de entrada, aquele que se deseja verificar se é possivel gerar
     *               um token para ele
     * @return       Retornando true caso seja possivel gerar o token e false caso contrário
     * 
     * IMPORTANTE: No caso de ser falso deve haver alguma forma de registrar o erro para armazenar
     *             em formato de 'log'
     */
    public boolean tokenizavel(String param) {

        Tipagem tipoDoParametro;

        if (true) {
            tipoDoParametro = Tipagem.DELIMITER;
        } else {

        }

        return true;
    }

    /**
     * Método de sobre-escrita para imprimir Tokens de acordo
     * com as regras:
     *  - Caso exista um ID para o lexema imprima ele
     *  - Caso não exista ID para o lexema imprima o próprio lexema
     */
    @Override
    public String toString() {

        if (idLexema != null) {
            return String.format("<%s, %s>", tipo, idLexema);
        } else {
            return String.format("<%s, %s>", tipo, lexema);
        }

    }

    // public static void main(String[] args) {

    //     Token t = new Token();
    //     t.setLexema("Lexema");
    //     t.setTipo(Tipagem.IDENTIFICADOR);
    //     t.setIdLexema("1");

    //     System.out.println(t);
    // }

    public Tipagem getTipo() {
        return tipo;
    }

    public void setTipo(Tipagem tipo) {
        this.tipo = tipo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getIdLexema() {
        return idLexema;
    }

    public void setIdLexema(String idLexema) {
        this.idLexema = idLexema;
    }
}
