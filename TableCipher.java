public abstract class TableCipher extends Cipher{
  protected char[][] grid;

  protected abstract void fillGrid(int keyNum, String keyStr);
}
