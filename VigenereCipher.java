import java.util.Arrays;
public class VigenereCipher extends PolySubCipher {

  public VigenereCipher(String keyStr) {
    super(keyStr.length(), keyStr);
  }

  protected char[][] genGrid() {
    if (getGrid() != null) {
      throw new IllegalStateException("Grid already initialized");
    }
    char[][] grid = new char[getKey()][26];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < 26; j++) {
        grid[i][j] = (char)((j + Character.toUpperCase(getKeyword().charAt(i)) - 'A') % 26 + 'A');
      }
    }
    return grid;
  }

  protected char encryptChar(char plain) {
    char cipher = getGrid()[counter()][Character.toUpperCase(plain) - 'A'];
    iter();
    return cipher;
  }

  public String encrypt(String plaintext) {
    reset();
    String ciphertext = "";
    for(int i = 0; i < plaintext.length(); i++) {
      ciphertext += encryptChar(plaintext.charAt(i));
    }
    return ciphertext;
  }

  public String decrypt(String ciphertext) {
    reset();
    String plaintext = "";
    for (int j = 0; j < ciphertext.length(); j++) {
      int letter = 0;
      for (int k = 0; k < 26; k++) {
        if (getGrid()[counter()][k] == Character.toUpperCase(ciphertext.charAt(j))) {
          letter = k;
          k = 26;
        }
      }
      plaintext += (char)('a' + letter);
      iter();
    }
    return plaintext;
  }
}
