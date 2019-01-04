public class Ciphers {
  public static void main(String[] args) {
    String typeCipher = args[0];
    typeCipher cipherObject = new typeCipher();
    String text = args[1];
    if (args[2].equals("encrypt")){
      System.out.println("Your encrypted plaintext: " + cipherObject.encrypt(text));
    }else if (args[2].equals("decrypt")){
      System.out.println("Your decrypted text: " + cipherObject.decrypt(text));
    }else{
      System.out.println("Please specify whether you would like to encrypt or decrypt your text.");
    }
  }
}
