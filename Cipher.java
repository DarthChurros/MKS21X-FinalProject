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
    isText(ciphertext);
    return ciphertext;
  }

  public static boolean isWord(String text) {
    try {
      Scanner wordReader = new Scanner(new File("words1000.txt"));
      while(wordReader.hasNext()) {
        if (wordReader.next().equals(text)) {
          return true;
        }
      }
      return false;
    } catch (FileNotFoundException e) {
      return false;
    }
  }

  public static boolean isText(String text) {
    ArrayList<String> words = new ArrayList<String>();
    words.add(text);
    for (int i = 0; i < text.length(); i++) {
      if (isWord(words.get(words.length()).substring(0,i))) {
        words.add(0,words.get(words.length()).substring(0,i));
      } else if (i == text.length() - 1) {
        i -= words.get(words.length() - 1).length();
        words.remove(words.length()-1);
      }
    }

    return false;
  }
}
