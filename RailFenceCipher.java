import java.util.Arrays; //2*key - 3
public class RailFenceCipher extends TranspositionCipher{
  private int distance;
  public RailFenceCipher(int k, String text){
    super(k, text);
    distance = 2*getKey() - 2;
    type = "Rail-fence cipher";
  }
  //first should be encrypte and decrypt, in both the plaintext or cipher text are specified
  //and then the grid can be made
/*
  public static void main(String[] args){
    RailFenceCipher a = new RailFenceCipher(3, "The United Kingdom");
    //a.encrypt("THEUNITEDKINGDOM");
    String enc = a.encrypt("The United Kingdom");
    System.out.println(a.getKey() + ", " + enc);
    String dec = a.decrypt(enc);
    System.out.println(a.getKey() + ", " + dec);
    //a1
    RailFenceCipher a1 = new RailFenceCipher(4, "THEUNITEDKINGDOM");
    enc = a1.encrypt("THEUNITEDKINGDOM");
    System.out.println(a1.getKey() + ", " + enc);
    dec = a1.decrypt(enc);
    System.out.println(a1.getKey() + ", " + dec);
    //a2
    RailFenceCipher a2 = new RailFenceCipher(5, "THEUNITEDKINGDOM");
    enc = a2.encrypt("THEUNITEDKINGDOM");
    System.out.println(a2.getKey() + ", " + enc);
    dec = a2.decrypt(enc);
    System.out.println(a2.getKey() + ", " + dec);
    //a3
    RailFenceCipher a3 = new RailFenceCipher(6, "THEUNITEDKINGDOM");
    enc = a3.encrypt("THEUNITEDKINGDOM");
    System.out.println(a3.getKey() + ", " + enc);
    dec = a3.decrypt(enc);
    System.out.println(a3.getKey() + ", " + dec);
    //a4
    RailFenceCipher a4 = new RailFenceCipher(7, "THEUNITEDKINGDOM");
    enc = a4.encrypt("THEUNITEDKINGDOM");
    System.out.println(a4.getKey() + ", " + enc);
    dec = a4.decrypt(enc);
    System.out.println(a4.getKey() + ", " + dec);
  // the above proves that getGrid needs to return just a copy bc grid is immutable
  }
  */
  private char[][] makeGrid(char[][] orig, String plaintext){
    boolean passedAry = false;
    char[][] tempGrid = getCopyOfGrid();
    int ary = -1;
    plaintext = plaintext.toUpperCase();
    plaintext = plaintext.replace(" ", "");
    //System.out.println(plaintext.length());
    for(int i = 0; i<plaintext.length(); i++){
      if (!passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary++;
        tempGrid[ary][i] = plaintext.charAt(i);
      //System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if (passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary--;
        tempGrid[ary][i] = plaintext.charAt(i);
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if ((i%distance == 0 && i != 0 )|| ary == getKey()-1){ //i%distance == 0 should happen when
        passedAry = !passedAry;
      }
    }
    return tempGrid;
  }

  public String encrypt(String plaintext){
    String temp = processText(plaintext);
    char[][] tempGrid = makeGrid(getGrid(), temp);
    String encrypted = "";
    for (int i = 0; i<getKey(); i++){
      for (int k = 0; k<temp.length(); k++){
        if (tempGrid[i][k] != '-'){
          encrypted += tempGrid[i][k];
        }
      }
    }
    //return toStringGrid(tempGrid);
    //return toReturn;
    return encrypted; //+ "\n" + toStringGrid(tempGrid);
  }
  public String decrypt(String ciphertext){
    //make a variable which is startPoint which updates by -1 (in order of index in array) everytime
    //you switch to a new array within the 2d one. For instance if the 'first' index in the last array is
    //2 from the back, then the 'first' index in the second to last array is 3 from the back and so on.
    String temp = processText(ciphertext);
    char[][] tempGrid = makeGrid(getGrid(), temp);
    //char[][] tempGrid = makeGrid(getGrid(), processText(ciphertext));
    int index = temp.length()-1;
    int arrayOn = getKey() - 1;
    while (index>= 0){
      for(int k = temp.length()-1; k>=0; k--){
        if (tempGrid[arrayOn][k] != '-'){
          tempGrid[arrayOn][k] = temp.charAt(index);
          index--;
        }
      }
      arrayOn--;
    }
  //  System.out.println(toStringGrid(tempGrid));
    String decrypted = "";
    boolean passedAry = false;
    int ary = -1;
    for(int i = 0; i<temp.length(); i++){
      if (!passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary++;
        decrypted += tempGrid[ary][i]; // = plaintext.charAt(i);
      //System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if (passedAry){
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
        ary--;
        decrypted += tempGrid[ary][i]; // = plaintext.charAt(i);
        //System.out.println("" + ary + ", " + plaintext.charAt(i));
      }
      if ((i%distance == 0 && i != 0 )|| ary == getKey()-1){ //i%distance == 0 should happen when
        passedAry = !passedAry;
      }
    }
    return decrypted.toLowerCase(); //+ "\n" + toStringGrid(tempGrid);

    /*
    char[][] tempGrid = getGrid();
    int index = ciphertext.length()-1;
    //int indAry = getKey() - 1;
    //have a for loop for how manyarys there are. and then try to keep adding every index
    //from the ciphertext, once it fails bc the difference for that ary makes the position to add to
    //out-of-bounds, then add 1 to the variable controlling the for loop that is counting the arrays
    //still need to find a way to calculate where the char begins on first (last) array
    //HOW TO DO THIS: JUST REMAKE THE GRID USING THE CIPHER TEXT AND LIKE TRACE OVER IT! MAKING grid
    //only depends on length of text. reamking it will tell you where the last letter of the last arry is
    //loctaed
    for (int k = getKey()-1; k>=0; k--){
      boolean canAdd = true;
      while [canAdd]{
        try{

        }catch(IndexOutOfBoundsException e){
          canAdd = false;
        }
      }
    }
    */
  //  return "";
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
