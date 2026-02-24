package br.com.faria;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.faria.enums.Constantes;
import br.com.faria.enums.Tipagem;

public class Token extends Object {

    // Declaração dos atributos do Token
    private Tipagem tipo;
    private String lexema;
    private String idLexema;
    private boolean classe;  // Verdadeiro -> Token padrão, Falso -> Token outros

    /**
     * Método para geração de objetos do tipo Token para utiliza-los no código
     * 
     * Precisamos definir -> 
     * #1 A função terá algum tipo de retorno? Se sim qual? 
     * R.: Retornar List<Token>
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
                        System.out.println(true);
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
                        System.out.println(false);
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
     * Método para inserir novos tokens ao arquivo tokens.json na categoria "outros"
     * deve ser pensado para quando for chamado dentro o laço de repetição que fará leitura 
     * o array de String que compõe todos os pedaços de código gerado pela função LeitorTxt.separateTxt()
     * 
     * @param parametros  Pensando em parametros: a Tipagem e Lexema. Ou então definir Tipagem nessa própria
     *                    função de cadastramento do token.
     */
    public void cadastrarTokens() {

        // switch (criarToken("idLexema")) {
        //     case DELIMITER:
                
        //         break;
        //     case IDENTIFICADOR:
                
        //         break;
        
        //     default:
        //         break;
        // }

        // Map<Tipagem, List<String>> mapaTokens = new HashMap<>(); // Opcional

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
     * @return       Retorna o tipo o qual o parametro se encaixa caso encontre token válido
     *               do contrário retorna null.
     * 
     * IMPORTANTE: No caso de ser falso deve haver alguma forma de registrar o erro para armazenar
     *             em formato de 'log'
     */
    public static Token criarToken(String param) {

        Token t = new Token();
        List<Token> listaPossiveisTokens = new ArrayList<>(); // Lista dos possiveis token gerados pelo parametro

        // Regex para encontrar estruturas do bloco principal
        Pattern kw_BP = Pattern.compile("(\\bLEDGER\\b|\\bCLOSE\\b)");
        Matcher matcherKW_BP = kw_BP.matcher(param);
        if( matcherKW_BP.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_BP);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar estrutura de saída
        Pattern kw_EC = Pattern.compile("(?<!\\S)\\$>(?!\\S)");
        Matcher matcherKW_EC = kw_EC.matcher(param);
        if( matcherKW_EC.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_EC);
            listaPossiveisTokens.add(t);
        }
        
        // Regex para encontrar operadores relacionais
        Pattern kw_OR = Pattern.compile("(?<!\\S)(==|>>|>=|<=|!=|<<)(?!\\S)");
        Matcher matcherKW_OR = kw_OR.matcher(param);
        if( matcherKW_OR.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_OR);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar tipos de variaveis
        // Realocar para fora desse bloco, implementar no for que percorre a List<String> que
        // contém o código dividido pegando primeiro elemento para fazer verificação
        // Passou nos testes, LEMBRETE: Retirar testes quando remover esse bloco daqui
        Pattern tipo = Pattern.compile("(?<!\\S)(#|\\$|!|@|\\?|~)(?!\\S)");
        Matcher matcherTIPO = tipo.matcher(param);
        if( matcherTIPO.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.TIPO);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar declaração de variaveis
        Pattern kw_D = Pattern.compile("\\b(LET)\\b");
        Matcher matcherKW_D = kw_D.matcher(param);
        if( matcherKW_D.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_D);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar operadores lógicos
        Pattern kw_OL = Pattern.compile("(?<!\\S)(&&|\\|\\||!!)(?!\\S)");
        Matcher matcherKW_OL = kw_OL.matcher(param);
        if( matcherKW_OL.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_OL);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar operadores relacionais
        Pattern kw_OA = Pattern.compile("(?<!\\S)(<-)(?!\\S)");
        Matcher matcherKW_OA = kw_OA.matcher(param);
        if( matcherKW_OA.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_OA);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar operadores aritméticos 
        Pattern kw_OPA = Pattern.compile("(?<!\\S)(\\+\\+|--|\\*\\*|//|%%|(|))(?!\\S)");
        Matcher matcherKW_OPA = kw_OPA.matcher(param);
        if( matcherKW_OPA.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.KW_OPA);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar numeros, seja inteiros quanto decimais 
        Pattern number = Pattern.compile("\\b\\d+.?\\d+\\b");
        Matcher matcherNUMBER = number.matcher(param);
        if( matcherNUMBER.find() ) {
            t.setLexema(param);
            t.setTipo(Tipagem.NUMBER);
            listaPossiveisTokens.add(t);
        }

        // Regex para encontrar declaração de variaveis lembrando que esse é o mais baixo nível
        Pattern identificador = Pattern.compile("\\S+");
        Matcher matcherIDENTIFICADOR = identificador.matcher(param);
        if( matcherIDENTIFICADOR.find() && listaPossiveisTokens.size() < 1 ) {
            t.setLexema(param);
            t.setTipo(Tipagem.IDENTIFICADOR);
            listaPossiveisTokens.add(t);
        }


        return listaPossiveisTokens.get(0);
    }

    /**
     * Método de sobre-escrita para imprimir Tokens de acordo
     * com as regras:
     *  - Caso exista um ID para o lexema imprima ele
     *  - Caso não exista ID para o lexema imprima o próprio lexema
     *  - Caso seja um token padrão imprima apenas o tipo
     */
    @Override
    public String toString() {

        if (idLexema != null) {
            return this.classe ? String.format("<%s>", this.tipo) : 
                   String.format("<%s, %s>", this.tipo, this.idLexema);
        } else {
            return this.classe ? String.format("<%s>", this.tipo) : 
                   String.format("<%s, %s>", this.tipo, this.lexema);
        }

    }


    public boolean equals(Token tokenVerificacao) {

        if(this.tipo.equals( tokenVerificacao.getTipo() ) && this.lexema == tokenVerificacao.getLexema()) {
            return true;
        } else {
            // Para perceber erros de mesmo ID para diferentes lexemas
            if(this.idLexema == tokenVerificacao.getIdLexema() && this.idLexema != null && tokenVerificacao.getIdLexema() != null)
                return true;  
            
            return false;
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
