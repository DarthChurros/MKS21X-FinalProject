public abstract class TableCipher extends Cipher{
  private char[][] grid;

  public TableCipher(int keyNum, String keyStr) {
    super(keyNum, keyStr);
  }

  protected char[][] getGrid() {
    return grid;
  }

  protected abstract void fillGrid(int keyNum, String keyStr);
}
