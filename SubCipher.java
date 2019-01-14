import java.util.Random;
import java.lang.Math;
import java.lang.IllegalStateException;

public class SubCipher extends PolySubCipher{
  /**
  public static void main(String[] args){
    SubCipher sc = new SubCipher(Integer.parseInt(args[0]));
    System.out.println("Encrypted message: " + sc.encrypt(args[1]));
    System.out.println("Decrypted message: " + sc.decrypt(sc.encrypt(args[1])));
    System.out.println("Testing if key works twice in a row: ");
    SubCipher sc1 = new SubCipher(Integer.parseInt(args[0]));
    System.out.println("Encrypted message: " + sc1.encrypt(args[1]));
    System.out.println("Decrypted message: " + sc1.decrypt(sc1.encrypt(args[1])));
    System.out.println("Testing if key works twice in a row: ");
  }
  **/
  private Random rng;
  public SubCipher(int k){
    //keyGrid must be filled from A to Z. so there is length 26 and only 1 row
    //for keyGrid[0], = 65 + randomObject.nextInt() % 26. For keyGrid[1], as long as keyGrid
    //doesn't already have this letter, it's ok
    //Random randomObject = new Random(); //THESE LINES
    //key = randomObject.nextInt(); //ARE THE USER'S PROBLEM. USER HAS TO PROVIDE A KEY FOR THE RANDOM OBJECT
    super(k, ""); //k is key for random object
    type = "Substitution cipher";
    }

    //to decrypt, you can't use this! you have to use constructor that specifies
  //shift amount. bc it has key variable to re-modify values
  //this is actually for caesar!
  public String encrypt(String plaintext){
    String pt = processText(plaintext);
    //System.out.println("pt: " + pt);
    //convert charAts to ascii #s, -65 so to get range from 0 to 25. these indexes are
    //what the new letters will be, as per the keyGrid
    String toReturn = "";
    for (int i = 0; i<pt.length(); i++){
      toReturn+=encryptChar(pt.charAt(i));
    }
    return toReturn;
  }

  public String decrypt(String ciphertext){ //this was is runtime n2, but works for random key
    String ct = processText(ciphertext); //maybe process this in public static void main(String[] args) {
    String toReturn = "";
    for (int i = 0; i<ct.length(); i++){
      char toAdd = 'A';
      for (int j = 0; j<26; j++){
        if (getGrid()[0][j] == ct.charAt(i)){
          toAdd += j; //remember, j is position of original letter in alphabet
        }
      }
      toReturn += (char) toAdd;
    }
    return toReturn;
  }

  protected char encryptChar(char c){
    int index = (int) c - 65;
    return getGrid()[0][index];
  }

  public char[][] genGrid(){
    if (getGrid() != null){
      throw new IllegalStateException("Grid already initialized!");
    }
    rng = new Random(getKey());
    char[][] grid = new char[1][26]; //getGrid returns reference to array itself, so you have access to modifying it
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for (int i = 0; i<26; i++){
      int randNum = Math.abs(rng.nextInt());
      //set random index of letters to the keygrid, then remove that letter from array and do from rand#%26 to rand#%25...
      grid[0][i] = letters.charAt(randNum%(26-i));
      letters = letters.substring(0, randNum%(26-i)) + letters.substring(randNum%(26-i)+1, letters.length());
      //remove is n2... find better solution
      //solution I think was to use strings
    }
    return grid;
  }

}
