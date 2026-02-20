package br.com.faria;

import br.com.faria.enums.Tipagem;

public class Token extends Object {

    // Declaração dos atributos do Token
    private Tipagem tipo;
    private String lexema;
    private String idLexema;

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
