import java.util.Random;
public class quick{
  public static void main(String[] args){
    Random robj = new Random();
    int a = robj.nextInt()%26;
    int b = robj.nextInt()%26;
    System.out.println(a);
    System.out.println(b);
    System.out.println(58898%26);
  }
}
