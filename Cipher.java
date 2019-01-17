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
    word = word.toUpperCase();
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

  public static String keylessDecrypt(String ct) throws FileNotFoundException{
    //have to import dictionary or dictionary file
    //but there aren't any spaces in the ciphertext!
    //add our own? every 4 letters add a space, maybe that has the highest chance it perhaps cutting the sentence sometimes
    //where it needs to be cut?
    //then, this is going to be inherited by caesar so start with caesar. Just go through the decryption method 25 times (check current text first)
    //and each time split it up every 4 characters, and run each word thru a method called "isWord", and have a counter counting
    //how many of the created words are actually words. The solution w the most actual words can be returned, with the spaces removed
    //i think this is a good rudimentary way to approach keylessDecryption
    return "cipher.java";
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
