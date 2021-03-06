import java.util.Random;
import java.lang.Math;
import java.util.Arrays;
public class SubCipher1{
  private char[][] keyGrid;
  private int key;
  public static void main(String[] args){
    String plaintext = "abcdefghijklmnopqrstuvwxyz";
    int key = Integer.parseInt(args[0]);
    SubCipher1 subC = new SubCipher1(key);
    String enc = subC.encrypt(plaintext);
    System.out.println("Encrypted: " + enc);
    System.out.println("Decrypted: " + subC.decrypt(enc));
    System.out.println("Doing it randomly: ");
    SubCipher1 subC1 = new SubCipher1();
    String enc1 = subC1.encrypt(plaintext);
    System.out.println("Encrypted: " + enc1);
    System.out.println("Decrypted: " + subC1.decrypt(enc1));
  }
  public SubCipher1(){
    //keyGrid must be filled from A to Z. so there is length 26 and only 1 row
    //for keyGrid[0], = 65 + randomObject.nextInt() % 26. For keyGrid[1], as long as keyGrid
    //doesn't already have this letter, it's ok
    Random randomObject = new Random();
    key = randomObject.nextInt(); //key from Cipher.java
    Random rng = new Random(key);
    keyGrid = new char[1][26];
    for (int i = 0; i<26; i++){
      int randNum = Math.abs(rng.nextInt());
      keyGrid[0][i] = (char) ('A' + randNum%26);

      for (int j = i; j>=0; j--){
        if (keyGrid[0][j] == keyGrid[0][]){
          randNum++;
          keyGrid[0][i] = (char) ('A' + randNum%26);
        }
      }

    } //to decrypt, you can't use this! you have to use constructor that specifies
  } //shift amount. bc it has key variable to re-modify values
  public SubCipher1(int shiftAmount){
    key = shiftAmount;
    keyGrid = new char[1][26];
    for (int i = 0; i<26; i++){
      keyGrid[0][i] = (char) ('A' + (i+shiftAmount)%26);
    }
  }
  public String encrypt(String plaintext){
    plaintext = plaintext.toUpperCase();
    //convert charAts to ascii #s, -65 so to get range from 0 to 25. these indexes are
    //what the new letters will be, as per the keyGrid
    String toReturn = "";
    for (int i = 0; i<plaintext.length(); i++){
      int index = (int) plaintext.charAt(i);
      index-=65; //not sure if this can be one line
      toReturn+=keyGrid[0][index]; //add to toReturn the value at the index
                                  //that corresponds to the index of the char in plaintext according to the alphabet
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
}
