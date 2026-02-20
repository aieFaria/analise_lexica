package br.com.faria.exercicio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class VetorUtilTest {

  @Nested
  class CopiarValores {
    @Test
    @DisplayName("Copiar valores com sucesso")
    public void copiarValoresComSucesso() {
      int[] a = new int[] { 1, 2, 3 };
      int[] b = new int[3];

      IVetorUtil vetorUtil = new VetorUtil();

      vetorUtil.copiarValores(a, b);

      assertArrayEquals(new int[] { 1, 2, 3 }, b);
    }

    @Test
    @DisplayName("Copiar valores com arrays de tamanhos diferentes")
    public void copiarValoresComArraysDiferentesTamanhos() {
      int[] a = new int[] { 1, 2, 3 };
      int[] b = new int[8];

      IVetorUtil vetorUtil = new VetorUtil();

      assertThrows(RuntimeException.class, () -> {
        vetorUtil.copiarValores(a, b);

      }, "Os arrays possuem tamanhos diferentes.");

    }
  }

  @Nested
  class CopiarValoresInverso {

  }
}
