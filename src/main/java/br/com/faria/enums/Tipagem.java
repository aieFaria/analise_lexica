package br.com.faria.enums;


// Enumeração para identificação resumida dos possiveis de tipos de Tokens
public enum Tipagem {

    IDENTIFICADOR("IDENTIFICADOR"),   // Identificador, Lexema
    KW_OPA("KW_OPA"),                 // Operadores Aritiméticos
    KW_BP("KW_BP"),                   // Operadores Base do Programa
    KW_EC("KW_EC"),                   // Exit Console
    NUMBER("NUMBER"),                 // Números
    TIPO("TIPO"),                     // Tipos de variavel
    KW_D("KW_D"),                     // Declaração de variavel
    KW_OA("KW_OA"),                   // Operadores de atribuição
    KW_OR("KW_OR"),                   // Operadores Relacionais
    KW_OL("KW_OL"),                   // Operadores Lógicos
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



