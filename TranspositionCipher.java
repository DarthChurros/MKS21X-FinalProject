abstract class TranspositionCipher extends Cipher{
  private char[][] keyGrid;
  public TranspositionCipher(int k, String text){
    super(k, "");
    keyGrid = genGrid(text);
    //keyGrid = genGrid(getKey());
  }
  protected abstract char[][] genGrid(String plaintext);
  //protected abstract char encryptChar(char plain); don't need this because, you just read off the grid

  protected char[][] getGrid(){
      return keyGrid;
  }
}
