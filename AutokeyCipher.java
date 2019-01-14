public class AutokeyCipher extends VigenereCipher {
  public AutokeyCipher(String keyStr) {
    super(keyStr);
    type = "Autokey cipher";
  }

  public String encrypt(String plaintext) {
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
    String plaintext = "";
    plaintext += super.decrypt(ciphertext.substring(0,getKey()));
    ciphertext = ciphertext.substring(getKey(),ciphertext.length());
    System.out.println("ciphertext = " + ciphertext);
    for (int i = 0; i < ciphertext.length(); i++) {
      plaintext += (char)(Math.floorMod(ciphertext.charAt(i) - Character.toUpperCase(plaintext.charAt(i)), 26) + 'A');
    }
    return plaintext.toLowerCase();
  }
}
