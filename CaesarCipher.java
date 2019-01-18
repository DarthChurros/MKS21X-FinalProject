import java.lang.Math;
import java.lang.IllegalStateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
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

  public static void main(String[] args){
    try{
      System.out.println("Testing");
      CaesarCipher test = new CaesarCipher(5);
      String a = test.encrypt("Unfortunately, tremendous transportation usually sucks");
      System.out.println("This is what \"Unfortunately, tremendous transportation usually sucks\" looks like w a shift of 5: " + a);
      System.out.println("This is what ^^ text looks like decrypted: " + test.decrypt(a));
      System.out.println("This is what the decryption of the cihpertext looks like when the key is not given: " + keylessDecrypt(a));
    }catch(FileNotFoundException e){
      System.out.println("words1000.txt doesn't exist!");
    }
  }

  //pre-condition: ct is already processed. a big part of this method is dealing w the fact that there are no spaces
  public static String keylessDecrypt(String ct) throws FileNotFoundException{ //bc isWord() throws this
    ArrayList<Integer> numWords = new ArrayList<Integer>(26); //the highest val in this list, its index is the right shift key
    for (int i = 0; i<26; i++){ //for every possible shift key:
      System.out.println("\n\n CURRENT KEY " + i);
      CaesarCipher testing = new CaesarCipher(i); //instantiate a new caesarcipher object
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
              System.out.println("How many words we've made so far:" + inS.size());
              System.out.println("Where the first letters of these words are:" + inS);
              String testingWord = test.substring(inS.get(inS.size()-1), inS.get((inS.size()-1))+k);
              System.out.println("CHECKING IF THIS WORD IS WORD: " + testingWord);
              if ((testingWord.length() > 1 || testingWord.equals("A") || testingWord.equals("I"))&& isWord(testingWord)){ //have to check length bc in words1000.txt
                System.out.println("Word works!");
                //currentCount++; //if you made a word, add to the current count
                inS.add(inS.get((inS.size()-1))+k); //add this new start character to scan so next time u go thru this u will scna the next word
                k = 14; //get out of this for loop so you can try again
                System.out.println("Has this updated? " + inS);
                //System.out.println(testingWord + "(CHECK)");
              }else if(k==13){//try to re-adjust
                if (inS.size() == 1){
                  k = 14;
                }else{
                  System.out.println("we are resizing the previous word and checking");
                  if (inS.size() > maxCount){
                    System.out.println("\nReplaced maxCount (" + maxCount + ") with inS.size() (" + inS.size() + ")");
                    maxCount = inS.size(); //store this before you go back on ur words
                    System.out.println("maxCount is now: " + maxCount + "\n");
                  }//PROBLEM BELOW --> INDEX OUT OF BOUNDS
                  for (int f = 1; f<15; f++){ //go back and try to set a new word. If there is no new word by modifying the word before the one you just tested,
                    //System.out.println("");
                    System.out.println("Current size: " + inS.size());
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
    System.out.println("numWords looks like: " + numWords);
    }
    System.out.println("max number of words:" + Collections.max(numWords));
    int key_ = numWords.indexOf(Collections.max(numWords)); //return the pt decrypted w the highest key in numWords
    System.out.println("key" + key_);
    CaesarCipher toReturn = new CaesarCipher(key_);
    return toReturn.decrypt(ct);
    //return "ugh";


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
