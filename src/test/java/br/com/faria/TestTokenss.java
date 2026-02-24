package br.com.faria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.faria.enums.Tipagem;


public class TestTokenss {

    @Nested
    public class CriacaoTokenss {
    
        @Test
        @DisplayName("Criando token com sucesso")
        public void correctToken() {

            Token t1 = new Token();

            t1.setLexema("LEDGER");
            t1.setTipo(Tipagem.KW_BP);
            assertTrue(t1.equals( Token.criarToken("LEDGER")) );

            t1.setLexema("CLOSE");
            t1.setTipo(Tipagem.KW_BP);
            assertTrue(t1.equals( Token.criarToken("CLOSE")) );

            t1.setLexema("$>");
            t1.setTipo(Tipagem.KW_EC);
            assertTrue(t1.equals( Token.criarToken("$>")) );

            t1.setLexema(">>");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken(">>") ) );

            // Test de operadores relacionais
            t1.setLexema("<<");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken("<<") ) );

            t1.setLexema(">=");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken(">=") ) );

            t1.setLexema("<=");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken("<=") ) );

            t1.setLexema("!=");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken("!=") ) );

            t1.setLexema("==");
            t1.setTipo(Tipagem.KW_OR);
            assertTrue( t1.equals( Token.criarToken("==") ) );

            // Teste de Declaração
            t1.setLexema("LET");
            t1.setTipo(Tipagem.KW_D);
            assertTrue( t1.equals( Token.criarToken("LET") ) );

            // Teste de Identificador
            t1.setLexema("programa");
            t1.setTipo(Tipagem.IDENTIFICADOR);
            assertTrue( t1.equals( Token.criarToken("programa") ) );

            t1.setLexema("<");
            t1.setTipo(Tipagem.IDENTIFICADOR);
            assertTrue( t1.equals( Token.criarToken("<") ) );

            // Teste de operadores lógicos
            t1.setLexema("!!");
            t1.setTipo(Tipagem.KW_OL);
            assertTrue( t1.equals( Token.criarToken("!!") ) );

            t1.setLexema("||");
            t1.setTipo(Tipagem.KW_OL);
            assertTrue( t1.equals( Token.criarToken("||") ) );

            t1.setLexema("&&");
            t1.setTipo(Tipagem.KW_OL);
            assertTrue( t1.equals( Token.criarToken("&&") ) );

            //Teste de operador de atribuição
            t1.setLexema("<-");
            t1.setTipo(Tipagem.KW_OA);
            assertTrue( t1.equals( Token.criarToken("<-") ) );

            //Teste de numeros
            t1.setLexema("40");
            t1.setTipo(Tipagem.NUMBER);
            assertTrue( t1.equals( Token.criarToken("40") ) );
            
            t1.setLexema("40.12");
            t1.setTipo(Tipagem.NUMBER);
            assertTrue( t1.equals( Token.criarToken("40.12") ) );

            // Teste de tipos de variaveis
            t1.setLexema("!");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("!") ) );

            t1.setLexema("@");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("@") ) );

            t1.setLexema("#");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("#") ) );

            t1.setLexema("$");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("$") ) );

            t1.setLexema("?");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("?") ) );

            t1.setLexema("~");
            t1.setTipo(Tipagem.TIPO);
            assertTrue( t1.equals( Token.criarToken("~") ) );
        }
        
    }

    
}
