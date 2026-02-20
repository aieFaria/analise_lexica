package br.com.faria.exercicio;

public class VetorUtil implements IVetorUtil {

  @Override
  public void copiarValores(int[] a, int[] b) {
    if (a.length != b.length) {
      throw new RuntimeException("Os arrays possuem tamanhos diferentes.");
    }

    for (int i = 0; i < a.length; i++) {
      b[i] = a[i];
    }
  }

  @Override
  public void copiarValoresInverso(int[] a, int[] b) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'copiarValoresInverso'");
  }

  @Override
  public int[] somarVetores(int[] a, int[] b) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'somarVetores'");
  }

  @Override
  public int[] intercalarElementos(int[] a, int[] b) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'intercalarElementos'");
  }

}
