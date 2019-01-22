import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
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

  public static boolean isWord(String text) {
    try {
      Scanner wordReader = new Scanner(new File("words1000.txt"));
      while(wordReader.hasNext()) {
        if (wordReader.next().toLowerCase().equals(text.toLowerCase())) {
          return true;
        }
      }
      return false;
    } catch (FileNotFoundException e) {
      return false;
    }
  }

  public static boolean isText(String text) {
    try {
      long start = System.nanoTime();
      ArrayList<String> words = new ArrayList<String>(); //words created by the splitting of the text
      words.add(text); //words should always end in the remaining text
      for (int i = 0; i <= words.get(words.size()-1).length(); i++) { //going through each letter in the last element
        if (isWord(words.get(words.size()-1).substring(0,i))) { //if the first part of the last element is a word
          //System.out.println(words.get(words.size()-1).substring(0,i)+" is a word");
          words.add(words.size()-1,words.get(words.size()-1).substring(0,i)); //add that word to the list
          words.set(words.size()-1,words.get(words.size()-1).substring(i,words.get(words.size()-1).length())); //remove that portion from the remaining text
          i = 0;
        } else if (i == Math.min(13,words.get(words.size()-1).length())) { //if the fragment isn't a word and we are at the last letter
          int last = words.get(words.size()-2).length();
          //System.out.println("reached end, reverting i to "+last);
          words.set(words.size()-1,words.get(words.size()-2)+words.get(words.size()-1));
          words.remove(words.size()-2);
          i = last;
        }
        //System.out.println(words +" at i="+i);
        if (System.nanoTime() - start > 20000000000L) {
          return false;
        }
      }
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }

  public static String keyless(String ct) throws FileNotFoundException{
    //have to import dictionary or dictionary file
    //but there aren't any spaces in the ciphertext!
    //add our own? every 4 letters add a space, maybe that has the highest chance it perhaps cutting the sentence sometimes
    //where it needs to be cut?
    //then, this is going to be inherited by caesar so start with caesar. Just go through the decryption method 25 times (check current text first)
    //and each time split it up every 4 characters, and run each word thru a method called "isWord", and have a counter counting
    //how many of the created words are actually words. The solution w the most actual words can be returned, with the spaces removed
    //i think this is a good rudimentary way to approach keylessDecryption
    return "KEYLESS DECRYPTION NOT IMPLEMENTED FOR THIS CIPHER";
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
