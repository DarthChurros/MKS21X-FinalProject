import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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

  protected static boolean isWord(String word) throws FileNotFoundException{
    File f = new File("words1000.txt");
    Scanner wordList = new Scanner(f);
    String current;
    while (wordList.hasNextLine()){
      current = wordList.nextLine();
      if (current.equals(word)){
        wordList.close();
        return true;
      }
    }
    wordList.close();
    return false;
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
}
