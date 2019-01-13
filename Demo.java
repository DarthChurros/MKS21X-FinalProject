import java.util.Scanner;
import java.util.Arrays;

public class Demo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Cipher util;
    System.out.println("Welcome to the Wii Fit Cipher Service! To begin, please select a cipher: \n");
    System.out.println("\t[1] Substitution Cipher");
    System.out.println("\t[2] Caesar Cipher");
    System.out.println("\t[3] Vigenere Cipher\n");

    switch (in.next()) {
      case "1":
        System.out.println("\nSelect a randomization key for this substitution cipher!\n");
        util = new SubCipher(Integer.parseInt(in.next()));
        break;
      case "2":
        System.out.println("\nSelect a shift key for this Caesar cipher!\n");
        util = new CaesarCipher(Integer.parseInt(in.next()));
        break;
      case "3":
        System.out.println("\nSelect a key for this Vigenere cipher!\n");
        util = new VigenereCipher(in.next());
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
