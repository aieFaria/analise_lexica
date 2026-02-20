package br.com.faria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestLeitorTxt {
    
    @Nested
    public class FuncaoDeLeituraBasica {
        
        @Test
        @DisplayName("Leitura de arquivo de texto basica")
        public void leTextoComSucesso() {

            LeitorTxt lt = new LeitorTxt();
            String resultado = lt.bufferTxt("untracked/exemplo.txt");

            assertEquals("oi eu não sei asa", resultado);
        }

        @Test
        @DisplayName("Abrindo arquivo inexistente no diretório do projeto")
        public void erroDeArquivoNaoEncontrado() {
            LeitorTxt lt = new LeitorTxt();
            String resultado = lt.bufferTxt("untracked/ejksdh.txt");

            assertEquals("untracked/ejksdh.txt (Arquivo ou diretório inexistente)",
                         resultado);
        }
        
    }

    @Nested
    public class FuncaoDeSeparacao {
        
        @Test
        @DisplayName("Convertendo String em Array com sucesso")
        public void conversaoDeTextoEmArray() {

            LeitorTxt lt = new LeitorTxt();
            String[] resultado = lt.separateText("ola   \nmundo querido falso");

            // Comparação para verificar se os arrays possuem os mesmos elementos
            // e também mesma ordem dos elementos
            assertArrayEquals(new String[]{"ola", "mundo", "querido", "falso"},
            resultado);
            

        }
        
    }

}
