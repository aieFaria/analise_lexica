package br.com.faria;

import java.io.FileReader;
import java.io.FileWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
//import com.opencsv.CSVWriter;

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
     * 
     * @param tokens  Trata-se dos mesmos tokens gerados para 
     * 
     * IMPORTANTE: CRIAR TABELA SE SIMBOLOS SIMUNTANEAMENTE COM ESSA GERAÇÃO DE TOKENS APARITIR DO JSON
     *             SENDO ASSIM ESTA FUNÇÃO SOMENTE DEVE SER CHAMADA DEPOIS DA FUNÇÃO {@link cadastrarTokens}
     * 
     * 
     * @author Gabriel Faria
     */
    @SuppressWarnings("unchecked")
    public List<Token> geraTokensFromJson() {
        List<Token> tokens =  new ArrayList<>();

        
        JSONParser parser = new JSONParser();

        try {

            int contador = 0;
            Token t = new Token();

            FileReader reader = new FileReader(Constantes.TOKENS_DIRETORIO);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            FileWriter writeFile = new FileWriter(Constantes.TABELA_SIMBOLOS_DIRETORIO); // Usar para escrever o .csv
            // CSVWriter writer = new CSVWriter(writeFile); // Tentar como opicional

            writeFile.write("id;lexema;token\n");
            

            JSONArray tokensPadrao = (JSONArray) jsonObject.get("padrao");


            for (Object objRegra: tokensPadrao) {

                if (objRegra != null) {
                    
                    JSONObject regra = (JSONObject) objRegra;
                    String tipo = (String) regra.get("tipo");
                    List<String> lexema = (List<String>) regra.get("lexema");

                    for(String lexemaDividido : lexema) {
                        contador++;
                        t = new Token();

                        writeFile.write(String.format("%d;%s;<%s>\n",contador, lexemaDividido, tipo));
                        // Gerar o objeto da classe Token dentro desse for
                        // System.out.println(tipo);  //Tipo
                        // System.out.println(lexemaDividido);  //lexema
                        // System.out.println(contador);  //idLexema
                        // System.out.println(true);

                        
                        t.setIdLexema(""+contador);
                        t.setLexema(lexemaDividido);
                        t.setTipo(convertTipagem(tipo));
                        t.setClasse(true);
                        tokens.add(t);
                        
                    }

                    //System.out.println(lexema);

                } else {
                    writeFile.close();
                    // Erro lançado quando faça leitura de objeto nulo
                    throw new IndexOutOfBoundsException("Leitura de um objeto vazio"); 
                }
                
            }
            
            

            JSONArray tokensOutros = (JSONArray) jsonObject.get("outros");
            //System.out.println(tokensOutros.size());
            //System.out.println(tokensOutros);

            for (Object objRegra : tokensOutros) {

                if (objRegra != null) {

                    JSONObject regra = (JSONObject) objRegra;
                    String tipo = (String) regra.get("tipo");
                    List<String> lexema = (List<String>) regra.get("lexema");

                    for(String lexemaDividido : lexema) {
                        contador++;
                        t = new Token();

                        writeFile.write(String.format("%d;%s;<%s>\n",contador, lexemaDividido, tipo));
                        // Gerar o objeto da classe Token dentro desse for
                        // System.out.println(tipo);  //Tipo
                        // System.out.println(lexemaDividido);  //lexema
                        // System.out.println(contador);  //idLexema
                        // System.out.println(false);

                        t.setIdLexema(""+contador);
                        t.setLexema(lexemaDividido);
                        t.setTipo(convertTipagem(tipo));
                        t.setClasse(false);
                        tokens.add(t);
                    }

                } else {
                    writeFile.close();
                    // Erro lançado quando faça leitura de objeto nulo
                    throw new IndexOutOfBoundsException("Leitura de um objeto vazio");
                }

            }
            //writeFile.write("Dados");
            writeFile.close();

            // t.setLexema("LEDGER");
            // t.setIdLexema("0");
            // t.setTipo(Tipagem.KW_BP);
            // t.setClasse(true);
            // System.out.println(t);
            // System.out.println(tokens.indexOf(t));
            // System.out.println(tokens.get(0).equals(t));

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            
            //writeFile.close();
        }

        return tokens;  //Lista de tokens atualizada com os IDs

    }


    /**
     * Método para inserir novos tokens ao arquivo tokens.json na categoria "outros"
     * mantendo os tokens da categoria "padrao" inalterados. Se previna pois este método
     * sobrescreve todos os elementos da categoria "outros", ou seja, os elementos seram apagados
     * e os novos seram colocados no lugar.
     * 
     * Nota: È impossivel adicionar Tokens referente a estrutura principal, como KW_BP e DELIMITER,
     *       na categoria "outros". A menos que seja feito manualmente.
     * 
     * Concatena tokens de mesmo tipo antes de fazer a escrita no arquivo .json
     * 
     * @param listaDeTokens  Passar a lista de tokens gerados para que seja possivel salvá-los
     *                       no arquivo .json que salva os tokens.
     * 
     * IMPORTANTE: É NECESSARIO PASSAR LISTA DO TOKEN UNICOS SEM QUE HAJA DUPLICATAS
     */
    public static void cadastrarTokens(List<Token> listaDeTokens) {

        JSONParser parser = new JSONParser();
        FileWriter writeFile = null;

        Map<String, String> dfaMap = new LinkedHashMap<>();

        StringBuilder saidaIdent = new StringBuilder();
        StringBuilder saidaNumbers = new StringBuilder();
        StringBuilder saidaText = new StringBuilder();

        for(Token token : listaDeTokens) {

            switch (token.getTipo()) {
                case IDENTIFICADOR:
                    if(!saidaIdent.isEmpty())
                        saidaIdent.append(",");

                    saidaIdent.append(String.format("\"%s\"", token.getLexema()));
                    
                    break;

                case NUMBER:
                    if(!saidaNumbers.isEmpty())
                        saidaNumbers.append(",");

                    saidaNumbers.append(String.format("\"%s\"", token.getLexema()));
                    
                    break;

                case TEXTO_LITERAL:
                    if(!saidaText.isEmpty())
                        saidaText.append(",");

                    saidaText.append(String.format("\"%s\"", token.getLexema()));
                    
                    break;
            
                default:
                    break;
            }

        }

        dfaMap.put("IDENTIFICADOR", saidaIdent.toString());
        dfaMap.put("TEXTO_LITERAL", saidaText.toString());
        dfaMap.put("NUMBER", saidaNumbers.toString());
        
        StringBuilder saidaJson = new StringBuilder();
        
        for (Map.Entry<String, String> entry : dfaMap.entrySet()) {
            if (!saidaJson.isEmpty())
                saidaJson.append(",\n");
            else
                saidaJson.append("[\n");
            
            saidaJson.append(String.format("        {\"tipo\":\"%s\",\"lexema\":[%s]}", entry.getKey(), entry.getValue()));
        }
        saidaJson.append("\n    ]");

        try {
            FileReader reader = new FileReader(Constantes.TOKENS_DIRETORIO);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            StringBuilder sb = new StringBuilder();

            // Copiando tokens padrão para mante-lo no .json gerado com os novos tokens
            JSONArray tokensPadrao = (JSONArray) jsonObject.get("padrao");

            // Criando token.json corretamente formatado
            sb.append(String.format("{\n  \"padrao\":%s,\n   \"outros\":%s\n}", tokensPadrao.toString().replace("},", "},\n").replace("{\"t", "\r        {\"t").replace("\n", ""), saidaJson));

            writeFile = new FileWriter(Constantes.TOKENS_DIRETORIO);
            //System.out.println(String.format("{\n\"padrao\":%s,\n   \"outros\":%s\n}", tokensPadrao, saidaJson));
            //writeFile.write(JSONValue.toJSONString(String.format("{\n\"padrao\":%s,\n   \"outros\":%s\n}", tokensPadrao, saidaJson)));
            writeFile.write(JSONValue.toJSONString(sb));
            // FileReader reader = new FileReader(Constantes.TOKENS_DIRETORIO);
            // Object obj = parser.parse(reader);
            // JSONObject jsonObject = (JSONObject) obj;

            // JSONArray tokensPadrao = (JSONArray) jsonObject.get("padrao");
            writeFile.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        

    }

    // public static void main(String[] args) {
    //     Token t1 = new Token(); t1.setLexema("LEDGERoo"); t1.setTipo(Tipagem.KW_BP);
    //     Token t2 = new Token(); t2.setLexema("'"); t2.setTipo(Tipagem.NUMBER);
    //     Token token = new Token();

    //     //token.cadastrarTokens(List.of(t1, t2));
    //     token.geraTokensFromJson();
    // }

    /**
     * Método de verificação de um item qualquer cujo objetivo é descobrir se é
     * possivel gerar um token referente a ele. 
     * 
     * 
     * Onde devemos posicionar essa verificação, em qual classe faremos melhor proveito de
     * sua utilizade?
     * 
     * 
     * @param param  Parâmetro de entrada, aquele que se deseja verificar se é possivel gerar
     *               um token para ele
     * @param caso   Parametro para definir o caso de criação do token, sendo 1 para normal e 2
     *               para casos especiais tipo texto delimitado por "" ou ''. O que define se é caso 1 ou 2
     *               será a verificação se o texto esta delimitado.
     * @return       Retorna o tipo o qual o parametro se encaixa caso encontre token válido
     *               do contrário retorna null.
     * 
     * IMPORTANTE: No caso de ser falso deve haver alguma forma de registrar o erro para armazenar
     *             em formato de 'log'
     */
    public static Token criarToken(String param) {

        return criarToken(param, 1);

    }

    // Método de sobrecarga para controle dos casos
    public static Token criarToken(String param, int caso) {

        List<Token> listaPossiveisTokens = new ArrayList<>(); // Lista dos possiveis token gerados pelo parametro

        switch (caso) {
            case 1:
                
                Token t = new Token();
                
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

                // Regex para encontrar estrutura condicionais
                Pattern kw_COND = Pattern.compile("(\\bIF\\b|(?<!\\S)\\:\\:(?!\\S))");
                Matcher matcherKW_COND = kw_COND.matcher(param);
                if( matcherKW_COND.find() ) {
                    t.setLexema(param);
                    t.setTipo(Tipagem.KW_COND);
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

                // Regex para encontrar delimitadores
                // Mesma coisa do regex imeditamente acima
                Pattern delimiter = Pattern.compile("(\"|'|\\{|\\})");
                Matcher matcherDELIMITER = delimiter.matcher(param);
                if( matcherDELIMITER.find() ) {
                    t.setLexema(param);
                    t.setTipo(Tipagem.DELIMITER);
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
                Pattern kw_OPA = Pattern.compile("(?<!\\S)(\\+\\+|--|\\*\\*|//|%%|\\(|\\))(?!\\S)");
                Matcher matcherKW_OPA = kw_OPA.matcher(param);
                if( matcherKW_OPA.find() ) {
                    t.setLexema(param);
                    t.setTipo(Tipagem.KW_OPA);
                    listaPossiveisTokens.add(t);
                }

                // Regex para encontrar numeros, seja inteiros quanto decimais 
                // Pattern number = Pattern.compile("(?<![$#@?!~)(])\\b\\d+(?:\\.\\d+)?\\b");
                // Matcher matcherNUMBER = number.matcher(param);
                // if( matcherNUMBER.find() ) {
                //     t.setLexema(param);
                //     t.setTipo(Tipagem.NUMBER);
                //     listaPossiveisTokens.add(t);
                // }

                // Regex para encontrar declaração de variaveis lembrando que esse é o mais baixo nível
                Pattern identificador = Pattern.compile("\\S+");
                Matcher matcherIDENTIFICADOR = identificador.matcher(param);
                if( matcherIDENTIFICADOR.find() && listaPossiveisTokens.size() < 1 ) {
                    t.setLexema(param);
                    t.setTipo(Tipagem.IDENTIFICADOR);
                    listaPossiveisTokens.add(t);
                }                

                break;
        
            case 2:

                Token t2 = new Token();

                // Regex para encontrar qualquer tipo de texto literal, seja String ou Chave Pix ou vazio
                Pattern texto_LITERAL = Pattern.compile("[\\s\\S]*");
                Matcher matcherTEXTO_LITERAL = texto_LITERAL.matcher(param);
                if( matcherTEXTO_LITERAL.find() ) {
                    t2.setLexema(param);
                    t2.setTipo(Tipagem.TEXTO_LITERAL);
                    listaPossiveisTokens.add(t2);
                }
                
                break;

        }

        if(listaPossiveisTokens.size() < 1) {
            // Capturar esse erro indica que não houveram matches possíveis então deve ser colocado no log
            throw new InvalidParameterException();
        }

        return listaPossiveisTokens.get(0); // Significa priorizar o primeiro token encontrado nas REGEX
        
    }

    public Tipagem convertTipagem(String str) {

        switch (str) {
            case "IDENTIFICADOR":
                return Tipagem.IDENTIFICADOR;
                
            case "KW_OPA":
                return Tipagem.KW_OPA;
                
            case "KW_BP":
                return Tipagem.KW_BP;
                
            case "KW_EC":
                return Tipagem.KW_EC;
                
            case "NUMBER":
                return Tipagem.NUMBER;
                
            case "TIPO":
                return Tipagem.TIPO;
                
            case "KW_D":
                return Tipagem.KW_D;
                
            case "KW_OA":
                return Tipagem.KW_OA;
                
            case "KW_OR":
                return Tipagem.KW_OR;
                
            case "KW_OL":
                return Tipagem.KW_OL;
                
            case "DELIMITER":
                return Tipagem.DELIMITER;
                
            case "KW_COND":
                return Tipagem.KW_COND;
                
            case "TEXTO_LITERAL":
                return Tipagem.TEXTO_LITERAL;
        
            default:
                return null;
                
        }

        
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

        if(this.tipo == tokenVerificacao.getTipo() && this.lexema.equals( tokenVerificacao.getLexema() ) ) {
            return true;
        } else {
            // Para perceber erros de mesmo ID para diferentes lexemas
            if(this.idLexema != null || tokenVerificacao.getIdLexema() != null)
                if(this.idLexema.equals(tokenVerificacao.getIdLexema()) )
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


    public boolean isClasse() {
        return classe;
    }


    public void setClasse(boolean classe) {
        this.classe = classe;
    }

    
}
