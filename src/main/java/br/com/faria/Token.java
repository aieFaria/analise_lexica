package br.com.faria;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.faria.enums.Tipagem;

public class Token extends Object {

    // Declaração dos atributos do Token
    private Tipagem tipo;
    private String lexema;
    private String idLexema;

    // Outra possibilidade de geração de tokens é a criação de um .json que contém todos 
    // os tokens
    public void leituraTokens() {

        

    }

    public static void main(String[] args) throws Exception {

        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        List<Object> retorno = new ArrayList<>();

        Object objetoJSON = parser.parse(new FileReader("untracked/tokens.json"));
        
        



        // JSONTokener tokener = new JSONTokener(file);
        
        // System.out.println(obj.toString());

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
