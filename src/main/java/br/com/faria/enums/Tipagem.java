package br.com.faria.enums;


// Enumeração para identificação resumida dos possiveis de tipos de Tokens
public enum Tipagem {

    IDENTIFICADOR("IDENTIFICADOR"),   // Identificador, Lexema 
    KW_OPA("KW_OPA"),                 // Operadores Aritiméticos [ID único]
    KW_BP("KW_BP"),                   // Operadores Base do Programa [ID único]
    KW_EC("KW_EC"),                   // Exit Console [ID único]
    NUMBER("NUMBER"),                 // Números 
    TIPO("TIPO"),                     // Tipos de variavel [ID único]
    KW_D("KW_D"),                     // Declaração de variavel [ID único]
    KW_OA("KW_OA"),                   // Operadores de atribuição [ID único]
    KW_OR("KW_OR"),                   // Operadores Relacionais [ID único]
    KW_OL("KW_OL"),                   // Operadores Lógicos [ID único]
    DELIMITER("DELIMITER"),           // Delimitadore de conteúdo String, Chave PIX [ID único]
    KW_COND("KW_COND"),               // Estrutura condicional do código [ID único]
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



