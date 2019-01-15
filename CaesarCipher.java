import java.lang.Math;
import java.lang.IllegalStateException;
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
