import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Demo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Cipher util;
    System.out.println("Welcome to the Wii Fit Cipher Service! To begin, please select a cipher: \n");
    System.out.println("\t[1] Substitution Cipher");
    System.out.println("\t[2] Caesar Cipher");
    System.out.println("\t[3] Vigenere Cipher");
    System.out.println("\t[4] Book Cipher");
    System.out.println("\t[5] RailFenceCipher Cipher\n");

    switch (in.next()) {
      case "1":
        try{
          System.out.println("\nSelect a randomization key (int) for this substitution cipher!\n");
          util = new SubCipher(Integer.parseInt(in.next()));
          break;
        }catch(NumberFormatException e){
          System.out.println("input a int please! QUITTING");
          System.exit(1);
        }
      case "2":
        try{
          System.out.println("\nSelect a shift key (int) for this Caesar cipher!\n");
          util = new CaesarCipher(Integer.parseInt(in.next()));
          break;
        }catch(NumberFormatException e){
          System.out.println("input a int please! QUITTING");
          System.exit(1);
        }
      case "3":
        System.out.println("\nSelect a (String) key for this Vigenere cipher!\n");
        util = new VigenereCipher(in.next());
        break;
      case "4":
        try{
          System.out.println("\nSelect a randomization key (int) for the book Cipher! It will use the enclosed BookCipherText.txt file!\n");
          util = new BookCipher(Integer.parseInt(in.next()), "BookCipherText.txt");
          break;
        }catch(FileNotFoundException e){
          System.out.println("Your machine doesn't have BookCipherText.txt! QUITTING");
          System.exit(1);
        }catch(NumberFormatException e){
          System.out.println("input a int please! QUITTING");
          System.exit(1);
        }
      case "5":
        System.out.println("\nSelect the height of your fence  (int) and specify the text to encrypt for the RailFenceCipher!\n");
        String a = in.next();
        in.nextLine();
        String b = in.nextLine();
        util = new RailFenceCipher(Integer.parseInt(a), b);
        break;
      default:
        util = new SubCipher(0);
        quit();
    }
    System.out.println("\nSelect a function: \n");
    System.out.println("\t[1] Encryption");
    System.out.println("\t[2] Decryption\n");

    switch(in.next()) {
      case "1":
        in.nextLine();
        System.out.println("Enter your plaintext: ");
        System.out.println("\nCiphertext: " + util.encrypt(in.nextLine()));
        break;
      case "2":
        in.nextLine();
        System.out.println("Enter your ciphertext: ");
        System.out.println("\nPlaintext: " + util.decrypt(in.nextLine()));
        break;
      default:
        quit();
    }
  }

  public static void quit() {
    System.out.println("Invalid selection! QUITTING");
    System.exit(1);
  }
}
