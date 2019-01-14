public class AutokeyCipher extends VigenereCipher {
  int c;
  public AutokeyCipher(String keyStr) {
    super(keyStr);
    c = 0;
  }

  public String encrypt(String plaintext) {
    c = 0;
    plaintext = getKeyword() + processText(plaintext);
    String ciphertext = "";
    for (int i = 0; i < plaintext.length()-getKey(); i++) {
      char cipher = (char)((Character.toUpperCase(plaintext.charAt(i + getKey())) % 26
                        + Character.toUpperCase(plaintext.charAt(i))) % 26 + 'A');
      iter();
      ciphertext += cipher;
    }
    return ciphertext;
  }

  public String decrypt(String ciphertext) {
    return "unfinished feature";
  }
}
