
public class Ciphers {
  public static void main(String[] args) {
    //args[0] == type of cipher. args[1] is text to be encrypted args[2] is key/keword, if there
    //are 5 args then args[3] is keyword to go along with # key. the last arg is whether to encrypt or decrypt
    String typeCipher = args[0];
    String text = args[1]
    Cipher cipherObject;
    if (args.length() == 5){ //means there will be both key and keyword specified
      switch (typeCipher) {
        case "BookCipher": cipherObject = new BookCipher(args[2], args[3]);
                           break;//int then filename
        default : System.out.println("Sorry, you're requested cipher does not require a # and keyword");
                  break;
      }
    }//do switch statment for rest of things
    if (args.length() == 4){
      switch (typeCipher) {
        case "BookCipher" : System.out.println("You must specify a # and filename");
                            break;
        case "VigenereCipher" : cipherObject = new VigenereCipher(args[2]);
                                break;
        case "AutokeyCipher" : cipherObject = new AutokeyCipher(args[2]);
                               break;
      }
    }
    //3rd parameter should be the key or keyword
    //check to see if it's a # (this is from stackoverflow :/)
    /**
    if (isNumeric(args[2])){
      cipherObject.setKey(Integer.parseInt(args[2])); //this is either for encryption or decryption?
    }else{ //ig if encryption is chosen we can have this be literally the amount we are shifting by,
      cipherObject.setKeyWord(args[2]); //or have some other meaning. for decryption, it is the # that the text was shifted by... i now understand that either way it's the same...
    }
    **/
    //maybe there needs to be a keyword and and a key. if args length = 5, that means
    //there is a keyword specified after the # (# shld come first)

    }
  }
  //from stackoverflow. uses regex to check if arg is a #
  private static boolean isNumeric(String str){
    return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
  }

}
