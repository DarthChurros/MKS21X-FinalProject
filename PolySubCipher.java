public abstract class PolySubCipher extends Cipher{
  private char[][] keyGrid;
  private int counter;

  public PolySubCipher(int keyNum, String keyStr) {
    super(keyNum, keyStr);
    counter = 0;
    keyGrid = genGrid();
  }
  /*
  protected static String processText(String text){
    String pt = text;
    //String ct = "";
    String puncString = ",.!?()/\";\':- "; //get rid of punctuation
    System.out.println(pt);
    //pt = pt.trim();
    System.out.println(pt);
    for (int k = 0; k<puncString.length(); k++){
      String punc = puncString.substring(k,k+1);
      pt = pt.replace(punc, ""); //this is a long process, i hope it is ok
    }
    pt = pt.toUpperCase();
    return pt;
  }
  */
  protected abstract char[][] genGrid();

  protected abstract char encryptChar(char plain);

  protected char[][] getGrid() {
    return keyGrid;
  }

  protected int counter() {
    return counter;
  }

  protected void iter() {
    counter = (counter + 1) % keyGrid.length;
  }

  protected void reset() {
    counter = 0;
  }
}
