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
      ArrayList<String> words = new ArrayList<String>(); //words created by the splitting of the text
      words.add(text); //words should always end in the remaining text
      for (int i = 0; i < words.get(words.size()-1).length(); i++) { //going through each letter in the last element
        if (isWord(words.get(words.size()-1).substring(0,i))) { //if the first part of the last element is a word
          System.out.println(words.get(words.size()-1).substring(0,i)+" is a word");
          words.add(words.size()-1,words.get(words.size()-1).substring(0,i)); //add that word to the list
          words.set(words.size()-1,words.get(words.size()-1).substring(i,words.get(words.size()-1).length())); //remove that portion from the remaining text
          i = 0;
        } else if (i == words.get(words.size()-1).length()-1) { //if the fragment isn't a word and we are at the last letter
          int last = words.get(words.size()-2).length();
          System.out.println("reached end, reverting i to "+last);
          words.set(words.size()-1,words.get(words.size()-2)+words.get(words.size()-1));
          words.remove(words.size()-2);
          i = last;
        }
        System.out.println(words +" at i="+i);
      }
      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }
}
