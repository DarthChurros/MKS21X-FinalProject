import java.lang.Math;
import java.lang.IllegalStateException;
public class CaesarCipher extends PolySubCipher{
  public CaesarCipher(int k){
    super(k, ""); //k is the shift amount
    fillGrid();
    for (int i = 0; i<26; i++){
      getGrid()[0][i] = (char) ('A' + (i+getKey())%26); //can i use get grid here
    }
  }
  public String encrypt(String plaintext){
    plaintext = plaintext.toUpperCase();
    //convert charAts to ascii #s, -65 so to get range from 0 to 25. these indexes are
    //what the new letters will be, as per the keyGrid
    String toReturn = "";
    for (int i = 0; i<plaintext.length(); i++){
      toReturn+=encryptChar(plaintext.charAt(i));
    }
    return toReturn;
  }

  public String decrypt(String ciphertext){ //this was is runtime n2, but works for random key
    ciphertext = ciphertext.toUpperCase(); //maybe process this in public static void main(String[] args) {
    String toReturn = "";
    for (int i = 0; i<ciphertext.length(); i++){
      char toAdd = 'A';
      for (int j = 0; j<26; j++){
        if (keyGrid[0][j] == ciphertext.charAt(i)){
          toAdd += j; //remember, j is position of original letter in alphabet
        }
      }
      toReturn += (char) toAdd;
    }
    return toReturn;
  }

  protected char encryptChar(char c){
    int index = (int) c - 65;
    return keyGrid[0][index];
  }
  
  protected void fillGrid() throws IllegalStateException(){
    if (getGrid() == null){
      getGrid() = new char[1][26]; //getGrid returns reference to array itself, so you have access to modifying it
      for (int i = 0; i<26; i++){
        getGrid()[0][i] = (char) ('A' + (i+getKey())%26); //can i use get grid here
      }
    }else{
      throw new IllegalStateException("You can't call fillGrid twice!");
    }
  }
}
