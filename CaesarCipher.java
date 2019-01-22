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
/*
  public static void main(String[] args){
    try{
      System.out.println("Testing");
      CaesarCipher test = new CaesarCipher(5);
      String a = test.encrypt("Unfortunately, tremendous transportation usually sucks");
      System.out.println("This is what \"Unfortunately, tremendous transportation usually sucks\" looks like w a shift of 5: " + a);
      System.out.println("This is what ^^ text looks like decrypted: " + test.decrypt(a));
      System.out.println("This is what the decryption of the cihpertext looks like when the key is not given: " + keylessDecrypt(a));
    }catch(FileNotFoundException e){
      System.out.println("words1000.txt doesn't exist!");
    }
  }
*/
  //pre-condition: ct is already processed. a big part of this method is dealing w the fact that there are no spaces
  public static String keyless(String ct) throws FileNotFoundException{ //bc isWord() throws this\
    ct = processText(ct);
    String pt = ct;
    CaesarCipher test = new CaesarCipher(1);
    for (int i = 0; i < 26; i++) {
      pt = test.encrypt(pt);
      if (isText(pt)) {
        return pt;
      }
    }
    return "Keyless decryption unsuccessful";
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
