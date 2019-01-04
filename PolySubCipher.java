public abstract class PolySubCipher {
  private char[][] keyGrid;
  private int counter;

  protected abstract char encryptChar(char plain);
}
