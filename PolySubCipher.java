public abstract class PolySubCipher extends Cipher{
  private char[][] keyGrid;
  private int counter;

  protected abstract char encryptChar(char plain);
}
