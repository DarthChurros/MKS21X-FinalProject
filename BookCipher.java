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
      return -1;
    }else{
      Integer tempIndex = 0;
      for (int i = 0; i<amountIters; i++){
        Integer index = charListCopy.indexOf(c);
        if (index != -1){ //will return -1 if no longer present
          tempIndex = index;
          charListCopy.set(tempIndex, '!');
        }
      }
      return tempIndex;
    }
  }

  public String encrypt(String plaintext){
    String pt = plaintext;
    String puncString = ",.!?()/\";\':-"; //get rid of punctuation
    for (int i = 0; i<pt.length(); i++){
        pt = pt.trim();
        for (int k = 0; k<puncString.length(); k++){
          String punc = puncString.substring(k,k+1);
          pt = pt.replace(punc, ""); //this is a long process, i hope it is ok
        }
        pt = pt.toUpperCase();
    }
    for (int j = 0; j<pt.length(); j++){
      if (pt.substring(j, j+1).equals(" ")){
        pt = pt; //don't change it. i know this is redundant but it makes it more clear
      }else{
        pt = pt.substring(0,j) + encryptChar(pt.charAt(j)) + pt.substring(j+1, pt.length()); //keep reassigning pt to being itself w current int encrypted
      }
    }
    return pt;
  }

  public String decrypt(String ciphertext){
    return "dec";
  }

  public static void main(String[] args){
    try{
      BookCipher nbc = new BookCipher(23, "BookCipherText.txt");
      System.out.println(nbc.encrypt("ABCDEFGHIJKLMNOPQRSTUVWXYZ hi hello"));
    }catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
  /*
  private char encryptChar(char){

  }
  */
}
