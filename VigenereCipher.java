import java.util.Arrays;
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
        grid[i][j] = (j + Character.toUpperCase(getKeyword().charAt(i)) - 'A') % 26 + 'A';
      }
    }
  }

  protected char encryptChar(char plain) {
    iter();
    return getGrid()[counter()][Character.toUpperCase(plain) - 'A'];
  }

  public String encrypt(String plaintext) {
    reset();
    String ciphertext = "";
    for(int i = 0; i < plaintext.length(); i++) {
      ciphertext += encryptChar(plaintext.charAt(i));
    }
  }

  public String decrypt(String ciphertext) {
    reset();
    String plaintext = "";
    for (int i = 0; i < ciphertext.length(); i++) {
        plaintext += Arrays.indexOf(getGrid()[counter()], Character.toUpperCase(ciphertext.charAt(i)));
    }
    return plaintext.toUpperCase();
  }
}
