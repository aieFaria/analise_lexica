package br.com.faria.enums;


// Enumeração para identificação resumida dos possiveis de tipos de Tokens
public enum Tipagem {

    IDENTIFICADOR("IDENTIFICADOR"),   // Identificador, Lexema V
    KW_OPA("KW_OPA"),                 // Operadores Aritiméticos V
    KW_BP("KW_BP"),                   // Operadores Base do Programa V
    KW_EC("KW_EC"),                   // Exit Console V
    NUMBER("NUMBER"),                 // Números V
    TIPO("TIPO"),                     // Tipos de variavel V
    KW_D("KW_D"),                     // Declaração de variavel V
    KW_OA("KW_OA"),                   // Operadores de atribuição  V 
    KW_OR("KW_OR"),                   // Operadores Relacionais V
    KW_OL("KW_OL"),                   // Operadores Lógicos V
    DELIMITER("DELIMITER"),           // Delimitadore de conteúdo String, Chave PIX, Prioridades: Parenteses esquerda e diteira
    TEXTO_LITERAL("TEXTO_LITERAL");   // Texto formado por quaisquer caracteres, String

    private String str;

    Tipagem(String str) {
        this.str = str;
    }

    // Caso necessário é possivel comparar os tipos com Strings
    public String getStr() {
        return str;
    }
}



