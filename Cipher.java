public abstract class Cipher {
  private int key;
  private String keyword;
  private String name;
  static String type = "Cipher";

  public Cipher(int keyNum, String keyStr) {
    key = keyNum;
    keyword = keyStr;
  }

  public abstract String encrypt(String plaintext);

  public abstract String decrypt(String ciphertext);

  public int getKey() {
    return key;
  }

  public String getKeyword() {
    return keyword;
  }

  protected static String processText(String text){
    //String ct = "";
    String puncString = ",.!?()/\";\':- "; //get rid of punctuation
    //System.out.println(pt);
    //pt = pt.trim();
    //System.out.println(pt);
    for (int k = 0; k<puncString.length(); k++){
      String punc = puncString.substring(k,k+1);
      text = text.replace(punc, ""); //this is a long process, i hope it is ok
    }
    return text.toUpperCase();
  }

  public String toString() {
    return type + " with key: "+getKey()+" and keyword: "+getKeyword();
  }
}
