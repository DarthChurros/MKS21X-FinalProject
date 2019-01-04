public class Ciphers {
  public static void main(String[] args) {
    String typeCipher = args[0];
    typeCipher cipherObject = new typeCipher();
    String text = args[1];
    //3rd parameter should be the key or keyword
    //check to see if it's a # (this is from stackoverflow :/)
    if (isNumeric(args[2])){
      cipherObject.setKey(Integer.parseInt(args[2])); //this is either for encryption or decryption?
    }else{ //ig if encryption is chosen we can have this be literally the amount we are shifting by,
      cipherObject.setKeyWord(args[2]); //or have some other meaning. for decryption, it is the # that the text was shifted by... i now understand that either way it's the same...
    }
    //maybe there needs to be a keyword and and a key. if args length = 5, that means
    //there is a keyword specified after the # (# shld come first)
    if (args.length == 5){
      cipherObject.setKeyWord(args[3]);
      if (args[4].equals("encrypt")){
        System.out.println("Your encrypted plaintext: " + cipherObject.encrypt(text));
      }else if (args[4].equals("decrypt")){
        System.out.println("Your decrypted text: " + cipherObject.decrypt(text));
      }else{
        System.out.println("Please specify whether you would like to encrypt or decrypt your text.");
      }
    }else{
      if (args[3].equals("encrypt")){
        System.out.println("Your encrypted plaintext: " + cipherObject.encrypt(text));
      }else if (args[3].equals("decrypt")){
        System.out.println("Your decrypted text: " + cipherObject.decrypt(text));
      }else{
        System.out.println("Please specify whether you would like to encrypt or decrypt your text.");
      }
    }
  }
  //from stackoverflow. uses regex to check if arg is a #
  private static boolean isNumeric(String str){
    return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
  }

}
