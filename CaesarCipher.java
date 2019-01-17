import java.lang.Math;
import java.lang.IllegalStateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
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
  //pre-condition: ct is already processed. a big part of this method is dealing w the fact that there are no spaces
  public static String keylessDecrypt(String ct) throws FileNotFoundException{ //bc isWord() throws this
    ArrayList<Integer> numWords = new ArrayList<Integer>(26);
    for (int i = 0; i<26; i++){
      CaesarCipher testing = new CaesarCipher(i);
      String test = testing.decrypt(ct);
      int currentCount = 0; //InS stands for indexSpances
      ArrayList<Integer> inS = new ArrayList<Integer>; //idk if this would be better suited with a linked list. maybe!
      inS.add(1);
      boolean done = false;
      while(!done){
        if (inS.get(0) >= 10 || .get(inS.size()-1)>test.length()-3){ //if you've tried different first words too much or you're at the ~end of the text
          done = true;
        }else{
          for (int k = 1; k<11; k++){ //max trial word length is 10 letters
            if (isWord(pt.substring(inS.get(inS.size()-1), inS.get((inS.size()-1)+k)))){
              currentCount++;
              inS.add(inS.get((inS.size()-1)+k));
              k = 11;
            }else if(k==10){//try to re-adjust
              for (int f = 1; f<11; f++){
                if (isWord(pt.substring(inS.get(inS.size()-2), inS.get(inS.size()-1)+f))){
                  inS.set(size()-1, inS.get(inS.size()-1) + f);
                  f = 11;
                }
                if (f==10){
                  inS.remove(size()-1);
                  f=1;
                  if(inS.size() == 0){
                    f = 11;
                    done = true;
                  }
                }
              }
            }
          }
        }
      }
    numWords.add(currentCount);
    }
    int key = numWords.indexOf(collections.max(numWords));
    CaesarCipher toReturn = new CaesarCipher(key);
    return toReturn.decrypt(ct);


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
