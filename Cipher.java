public abstract class Cipher {
  private int key;
  private String keyword;

  public Cipher(int keyNum, String keyStr) {
    key = keyNum;
    keyword = keyStr;
  }

  public abstract String encrypt(String plaintext);

  //public abstract String decrypt(String ciphertext);

  public int getKey() {
    return key;
  }

  public String getKeyword() {
    return keyword;
  }
}
