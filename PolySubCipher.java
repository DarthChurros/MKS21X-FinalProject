public abstract class PolySubCipher extends Cipher{
  private char[][] keyGrid;
  private int counter;

  public PolySubCipher(int keyNum, String keyStr) {
    super(keyNum, keyStr);
    counter = 0;
  }

  protected abstract void fillGrid();

  protected abstract char encryptChar(char plain);

  protected char[][] getGrid() {
    return keyGrid;
  }

  protected int counter() {
    return counter;
  }
}
