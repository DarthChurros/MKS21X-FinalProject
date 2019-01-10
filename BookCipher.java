import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class BookCipher extends Cipher{
  private Random randgen;
  ArrayList<Character> charList;
  //VV will throw filenot found
  public BookCipher(int k, String t) throws FileNotFoundException{ //k can be key for rand num. t is name of text file
    super(k, t); //so keyword == filename and k == key
    randgen = new Random(getKey()); //we will use this to encrypt
    File f = new File(getKeyword());
    Scanner in = new Scanner(f);
    charList = new ArrayList<Character>();
    String puncString = ",.!?()/\";\':-"; //get rid of punctuation
    while (in.hasNext()){
        String word = in.next();
        word = word.trim();
        for (int i = 0; i<puncString.length(); i++){
          String punc = puncString.substring(i,i+1);
          word = word.replace(punc, ""); //this is a long process, i hope it is ok
        }
        word = word.toUpperCase();
        for (int i = 0; i<word.length(); i++){
          charList.add(word.charAt(i));
        }
    }
  }

  private Integer encryptChar(char c){
    ArrayList<Character> charListCopy = new ArrayList<Character>(charList);
    int amountIters = randgen.nextInt();
    if (!charListCopy.contains(c)){
      return '!';
    }else{
      Integer tempIndex = 0;
      for (int i = 0; i<amountIters; i++){
        index = charListCopy.indexOf(c);
        if (index != -1){ //will return -1 if no longer present
          tempIndex = index;
          charListCopy.set(tempIndex, '!');
        }
      }
      return tempIndex;
    }
  }

  public String encrypt(String plaintext){
    return "enc";
  };

  public String decrypt(String ciphertext){
    return "dec";
  };

  public static void main(String[] args){
    try{
      BookCipher nbc = new BookCipher(23, "BookCipherText.txt");
      System.out.println(nbc.charList);
    }catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
  /*
  private char encryptChar(char){

  }
  */
}
