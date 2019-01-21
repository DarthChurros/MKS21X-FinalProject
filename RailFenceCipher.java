import java.util.Arrays; //2*key - 3
import java.lang.Math;
import java.lang.IllegalStateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class RailFenceCipher extends TranspositionCipher{
  private int distance;
  public RailFenceCipher(int k, String text){
    super(k, text);
    distance = 2*getKey() - 2;
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
      if ((i%distance == 0 && i != 0 )|| ary == getKey()-1){ //i%distance == 0 should happen when, first part accounts for when key = 1
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
  public String toString() {
    return "Rail-fence cipher with rails: "+getKey() + " and initial plaintext: "+getKeyword();
  }

  public static String keyless(String ct) throws FileNotFoundException{ //bc isWord() throws this
    ct = processText(ct);
    int possKeys = ct.length();
    ArrayList<Integer> numWords = new ArrayList<Integer>(possKeys); //the highest val in this list, its index is the right shift key
    for (int i = 2; i<possKeys; i++){ //for every possible shift key: -- can't start at 0 bc height 0 not possible
      //System.out.println("\n\n CURRENT KEY " + i);
      RailFenceCipher testing = new RailFenceCipher(i, ct); //instantiate a new caesarcipher object
      String test = testing.decrypt(ct); //decrypt the ct based on the current key
      int maxCount = 0;
      //int currentCount = 0; //InS stands for indexSpances. Also make the currentCount 0 -- this counts how many words
      ArrayList<Integer> inS = new ArrayList<Integer>(); //idk if this would be better suited with a linked list. maybe!
      inS.add(0); //consider the first letter as a word.
      boolean done = false; //WHAT YOU FORGOT IS THAT WORDS1000.TXT IS IN ALL LOWERCASE! IN ISWORD, TURN WORD INTO UPPER CASE
      boolean didOnce = false;
      while(!done){ //this second part of the if statement is probably very faulty for short sentences
        if (inS.size() == 1 && didOnce || inS.get(inS.size()-1)>test.length()-3){ //if you've tried different first words too much or you're at the ~end of the text
          done = true; //if you've gone all the way back to the first 'word' unable to find a better path, you're done
                      //or if the last space is very close to the end
        }else{
          for (int k = 1; k<15; k++){ //max trial word length is 10 letters
            if (inS.get((inS.size()-1))+k < ct.length()){
              //System.out.println("How many words we've made so far:" + inS.size());
              //System.out.println("Where the first letters of these words are:" + inS);
              String testingWord = test.substring(inS.get(inS.size()-1), inS.get((inS.size()-1))+k);
              //System.out.println("CHECKING IF THIS WORD IS WORD: " + testingWord);
              if ((testingWord.length() > 1 || testingWord.equals("A") || testingWord.equals("I"))&& isWord(testingWord)){ //have to check length bc in words1000.txt
                //System.out.println("Word works!");
                //currentCount++; //if you made a word, add to the current count
                inS.add(inS.get((inS.size()-1))+k); //add this new start character to scan so next time u go thru this u will scna the next word
                k = 14; //get out of this for loop so you can try again
                //System.out.println("Has this updated? " + inS);
                //System.out.println(testingWord + "(CHECK)");
              }else if(k==13){//try to re-adjust
                if (inS.size() == 1){
                  k = 14;
                }else{
                  //System.out.println("we are resizing the previous word and checking");
                  if (inS.size() > maxCount){
                    //System.out.println("\nReplaced maxCount (" + maxCount + ") with inS.size() (" + inS.size() + ")");
                    maxCount = inS.size(); //store this before you go back on ur words
                    //System.out.println("maxCount is now: " + maxCount + "\n");
                  }//PROBLEM BELOW --> INDEX OUT OF BOUNDS
                  for (int f = 1; f<15; f++){ //go back and try to set a new word. If there is no new word by modifying the word before the one you just tested,
                    //System.out.println("");
                    //System.out.println("Current size: " + inS.size());
                    String nextTest = test.substring(inS.get(inS.size()-2), //problem here!
                                                     inS.get(inS.size()-1)+f);
                    if (isWord(nextTest)){ //go back and check the one before keep going
                      inS.set(inS.size()-1, inS.get(inS.size()-1) + f); //you don't need to remove any words from currentCount, b/c u still have the same amount j one word is different
                      f = 14;
                    }
                    if (f==13){ //if you didn't find a word, remove the word you just worked on
                      inS.remove(inS.size()-1);
                      //currentCount--; //since you're going back on your list of indexes to start new words, remove one
                      f=1; //restart the loop
                      if(inS.size() == 1){
                        f = 14; //if you went back to the end of string w no words possible, you're done
                        done = true;
                      }
                    }
                  }
                }
              }
              didOnce = true; //this is to see, if you've gone thru the first letter 10 times trying to make a word and it hasn't worked, you've tried already too much -- go on to next key
            }else{
              done = true;
            }
            if (inS.size() > maxCount){
              maxCount = inS.size();
            }
          }
        }
      }
    numWords.add(maxCount); //add the final word count
    //System.out.println("numWords looks like: " + numWords);
    }
    //System.out.println("max number of words:" + Collections.max(numWords));
    int key_ = numWords.indexOf(Collections.max(numWords)) + 2; //return the pt decrypted w the highest key in numWords --> IT'S PLUS 2 BC FOR RAILFENCE WE CAN'T HAVE KEY 0 OR 1 (KEY 1 =KEY LENGTH OF CT);
    //System.out.println("key" + key_);
    RailFenceCipher toReturn = new RailFenceCipher(key_, ct);
    return toReturn.decrypt(ct);
    //return "ugh";
  }
}
