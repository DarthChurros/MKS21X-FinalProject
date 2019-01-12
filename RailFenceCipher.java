import java.util.Arrays; //2*key - 3
public class RailFenceCipher extends TranspositionCipher{
  private int distance;
  public RailFenceCipher(int k, String text){
    super(k, text);
    distance = 2*getKey() - 2;
  }
  //first should be encrypte and decrypt, in both the plaintext or cipher text are specified
  //and then the grid can be made
  public static void main(String[] args){
    RailFenceCipher a = new RailFenceCipher(3, "THEUNITEDKINGDOM");
    //a.encrypt("THEUNITEDKINGDOM");
    System.out.println(a.encrypt("THEUNITEDKINGDOM"));
  }
  public String encrypt(String plaintext){
    boolean passedAry = false;
    //int i = 0;
    char[][] tempGrid = getGrid();
    int ary = -1;
    for(int i = 0; i<plaintext.length(); i++){
      if (!passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary++;
        tempGrid[ary][i] = plaintext.charAt(i);
        System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if (passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary--;
        tempGrid[ary][i] = plaintext.charAt(i);
        System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if ((i%distance == 0 && i != 0 )|| ary == getKey()-1){ //i%distance == 0 should happen when
        passedAry = !passedAry;
      }
    }
    return toStringGrid(tempGrid);
    //return toReturn;
  }
  protected String toStringGrid(char[][] grid){
    String toReturn = "";
    for (int i = 0; i<getKey(); i++){
      toReturn += Arrays.toString(grid[i]) + "\n";
    }
    return toReturn;
  }
  protected char[][] genGrid(String text){
    char[][] toReturn = new char[getKey()][text.length()];
    for (int i = 0; i<getKey(); i++){
      for (int k = 0; k<text.length(); k++){
        toReturn[i][k] = '-';
      }
    }
    return toReturn;
  }
  //  public abstract String decrypt(String ciphertext);
}
