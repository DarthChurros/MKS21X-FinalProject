import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public abstract class Cipher {
  private int key;
  private String keyword;
  private String name;

  public Cipher(int keyNum, String keyStr) {
    key = keyNum;
    keyword = processText(keyStr);
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

  public static String keyless(String ciphertext) {
    return ciphertext;
  }

  public static boolean isWord(String text) {
    try {
      Scanner wordReader = new Scanner(new File("words1000.txt"));
      words = new ArrayList<String>();
      while(wordReader.hasNext()) {
        if (words.next().equals(text)) {
          return true;
        }
      }
      return false;
    } catch (FileNotFoundException e) {
      return false;
    }
  }
}
