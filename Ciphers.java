
public class Ciphers {
  public static void main(String[] args) {
    //args[0] == type of cipher. args[1] is text to be encrypted args[2] is key/keword, if there
    //are 5 args then args[3] is keyword to go along with # key. the last arg is whether to encrypt or decrypt
    String typeCipher = args[0];
    String text = args[1]
    Cipher cipherObject;
    if (args.length() == 5){ //means there will be both key and keyword specified
      switch (typeCipher) {
        case "BookCipher": // cipherObject = new BookCipher(args[2], args[3]);
                           break;//int then filename
        default : System.out.println("Sorry, you're requested cipher does not require a # and keyword");
                           break;
      }
    }//do switch statment for rest of things
    if (args.length() == 4){
      switch (typeCipher) {
        case "BookCipher" : System.out.println("You must specify a # and filename");
                                break;
        case "VigenereCipher" : // cipherObject = new VigenereCipher(args[2]); //args[2] must be a string
                                break;
        case "AutokeyCipher" : // cipherObject = new AutokeyCipher(args[2]);  // ^
                                break;
        case "NihilistCipher" : // cipherObject = new NihilistCipher(args[2]); // ^
                                break;
        case "PlayfairCipher" : // cipherObject = new PlayfairCipher(args[2]);
                                break;
        case "CaesarCipher" : if (isNumeric(args[2])){ //in actual classes, there will be exception handling
                                  // cipherObject = new CaesarCipher(Integer.parseInt(args[2]));
                              }else{
                                  System.out.println("CaesarCipher requires a numeric input");
                              } // this has a #
                              break;
        case "VICCipher" : if (isNumeric(args[2])){
                                  // cipherObject = new VICCipher(Integer.parseInt(args[2]));
                              }else{
                                  System.out.println("VICCipher requires a numeric input");
                              } // ^
                              break;
        default : System.out.println("Sorry, invalid cipher requested");
      }
    }
    if (args[args.length].equals("decrypt")){
      System.out.println("Decrypted text: " + cipherObject.decrypt(text));
    }else if (args[args.length].equals("encrypt")){
      System.out.println("Encrypted text: " + cipherObject.encrypt(text));
    }else{
      System.out.println("Please rerun program and specify whether to decrypt or encrypt");
    }
  }
  //from stackoverflow. uses regex to check if arg is a #
  private static boolean isNumeric(String str){
    return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
  }

}
