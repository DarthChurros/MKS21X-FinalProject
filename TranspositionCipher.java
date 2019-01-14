import java.util.Arrays;
abstract class TranspositionCipher extends Cipher{
  private char[][] keyGrid;
  public TranspositionCipher(int k, String text){
    super(k, "");
    keyGrid = genGrid(processText(text));
    //keyGrid = genGrid(getKey());
  }
  protected abstract char[][] genGrid(String plaintext);
  //protected abstract char encryptChar(char plain); don't need this because, you just read off the grid

  protected char[][] getGrid(){
      return keyGrid;
  }

  protected char[][] getCopyOfGrid(){
    char[][] toReturn = new char[getKey()][keyGrid[0].length];
    for (int i = 0; i<getKey(); i++){
      toReturn[i] = Arrays.copyOf(getGrid()[i], keyGrid[0].length);
    }
    return toReturn;
  }
}
