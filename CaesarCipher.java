import java.lang.Math;
import java.lang.IllegalStateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class CaesarCipher extends SubCipher{
  /*
  public static void main(String[] args){
    CaesarCipher sc = new CaesarCipher(Integer.parseInt(args[0]));
    System.out.println("Encrypted message: " + sc.encrypt(args[1]));
    System.out.println("Decrypted message: " + sc.decrypt(sc.encrypt(args[1])));
    System.out.println("Testing if key works twice in a row: ");
    CaesarCipher sc1 = new CaesarCipher(Integer.parseInt(args[0]));
    System.out.println("Encrypted message: " + sc1.encrypt(args[1]));
    System.out.println("Decrypted message: " + sc1.decrypt(sc1.encrypt(args[1])));
    System.out.println("Testing if key works twice in a row: ");
  }
  */
  public CaesarCipher(int k){
    super(k); //k is the shift amount
  }

  public static String keylessDecryption(String realCT) throws FileNotFoundException { //bc isWord() throws this
    //int countOfWords = 0;
    int keY; //will be the int of the highest shift key
    int maxCountOfWords = 0;
    int a = (realCT.length()-1)/4 + 1; //how many 'words' can be made if a space is made every 4 chars
    String[] ctWords = new String[a];
    for (int shift = 0; shift<26; shift++){
      int countOfWords = 0;
      CaesarCipher toTest = new CaesarCipher(shift);
      String pt = toTest.decrypt(realCT);
      int index = 0;
      for (int i = 0; i<pt.length(); i+=4){ //make the String[] of 'words' in the pt
        if (i > pt.length()-5){
          ctWords[index] = pt.substring(i, pt.length());
        }
        ctWords[index] = pt.substring(i, i+4);
        index++;
      }
      for (String word : ctWords){ //check each 'word' in ctWords and see if they're actually words
        if (isWord(word)){
          countOfWords++;
        }
        if (countOfWords>maxCountOfWords){
          maxCountOfWords = countOfWords;
          keY = shift;
        }
      }
    }
    CaesarCipher toReturn = new CaesarCipher(keY);
    return toReturn.decrypt(realCT);
    /*
    realCT = processText(realCT);
    int countOfWords = 0;
    int maxCountOfWords = 0;
    int keY;
    int a = (realCT.length()-1)/4 + 1; //how many 'words' can be made if a space is made every 4 chars
    String[] ctWords = new String[a]; //if it divides evenly into 4 there is no need. but if it doesn't add 1, so j do realCT.length()-1
    for (int shift = 0; shift<26; shift++){
      countOfWords = 0;
      String ct;
      for (int l = 0; l<realCT.length(); l++){ //encrypt based on given key
        char encChar = (char) 65 + (ct.charAt(l) - 65 + shift)%26;
        ct = ct.substring(0, l) + encChar + ct.substring(l+1, ct.length());
      }
      for (int i = 0; i<=a; i++){ //adds arbitrary spaces. not arbitrary its every 4, but i just choose every 4 on a whim.
        if (i==a){ //this is for our analyzation
          ctWords[i] += ct.substring(i, ct.length());
        }else{
          ctWords[i] = ct.substring(i, i+5);
        }
      }
      for (String s : ctWords){
        if (isWord(s)){ //isWord in cipher.java. Scans a file of 10,000 most common words and sees if s is .equal to one of them
          countOfWords++;
        }
        if (countOfWords>maxCountOfWords){//if with this key there are more actualy words than there had been before, update this key as the one to decrypt w
          maxCountOfWords = countOfWords;
          keY = shift;
        }
      }
    }
    //once we figure out what is most likely to be the right shift key,
    CaesarCipher toReturn = new CaesarCipher(keY);
    return toReturn.decrypt(ct); //a quicker way to do this is to run a loop 26 times, make a new object every time,
    //decrypt w index of for look in that object and then do the spaces and checks
    //fakeSpace.trim();
    */

  }
  public char[][] genGrid(){
    if (getGrid() == null){
      char[][] grid = new char[1][26];
      for (int i = 0; i<26; i++){
        grid[0][i] = (char) ('A' + (i+getKey())%26); //can i use get grid here
      }
      return grid;
    }else{
      throw new IllegalStateException("You can't call fillGrid twice!");
    }
  }
  public String toString() {
    return "Caesar cipher with shift key: "+getKey();
  }
}
