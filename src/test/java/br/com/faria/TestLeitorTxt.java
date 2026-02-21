package br.com.faria;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            String resultado = lt.bufferTxt("untracked/LEDAGER_programa.pix");

            assertEquals("oi eu não sei asa", resultado);
        }

        @Test
        @DisplayName("Abrindo arquivo inexistente no diretório do projeto")
        public void erroDeArquivoNaoEncontrado() {
            LeitorTxt lt = new LeitorTxt();
            String resultado = lt.bufferTxt("untracked/ejksdh.txt");

            switch (System.getProperty("os.name")) {
                case "Windows":
                    
                    // Test para Windowns
                    assertEquals("untracked\\ejksdh.txt (O sistema não pode encontrar o arquivo especificado)",
                                resultado);

                    break;
                case "Linux":
                    
                    // Test para Linux
                    assertEquals("untracked/ejksdh.txt (Arquivo ou diretório inexistente)",
                                 resultado);

                    break;
            
                default:
                    break;
            }

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

        @Test
        @DisplayName("Capturando erro de execução devido String muito grande")
        public void stringGigantesca() {

            // OutOfMemoryError exception = assertThrows(
            //     OutOfMemoryError.class,
            //     () -> LeitorTxt.separateText("LeitorTxt.geraErro()"));

            // assertEquals("Não é possível somar valores NULL.", exception.getMessage());

        }
        
    }

}
