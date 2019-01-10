public class VigenereCipher extends PolySubCipher {

  public VigenereCipher(String keyStr) {
    super(0, keyStr);
  }

  protected void genGrid() {
    if (getGrid() != null) {
      throw new IllegalStateException("Grid already initialized");
    }
    char[][] grid = new char[getKeyword().length()][26];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < 26; j++) {
        grid[i][j] = (char)((j + getKeyword().charAt(i) - 'a') % 26 + 'A');
      }
    }
  }
}
