import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class BruteForce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //class containing hash function
        PasswordDemo myPasswordDemo = new PasswordDemo();
        String s, hs = "";
        System.out.println("Please input a hash variable");
        Scanner input = new Scanner(System.in);
        s = input.nextLine();
        
        //read string
        
        //create array containing every single possible character
        String [] a = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
        "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        //create nested loop to generate each possible combination in five digit password using array
        for(int n5=0; n5<37; n5++) 
        {
            for(int n4=0; n4<37; n4++) 
            {
                for(int n3=0; n3<37; n3++) 
                {
                    for(int n2=0; n2<37; n2++) 
                    {
                        for(int n1=0; n1<37; n1++) 
                        {
                            for(int n0=0; n0<37; n0++) 
                            {
                                //convert each combination to hash
                                String possPass = a[n5]+a[n4]+a[n3]+a[n2]+a[n1]+a[n0];
                                //try block to catch exceptions
                                try { 
                                    hs = myPasswordDemo.SHA1(possPass);
                                    System.out.println(hs);
                                    }                  
                                    catch (NoSuchAlgorithmException e) { 
                                        // TODO Auto-generated catch block 
                                        e.printStackTrace(); 
                                    } 
                                    catch (UnsupportedEncodingException e) { 
                                        // TODO Auto-generated catch block 
                                        e.printStackTrace(); 
                          
                                    }
                                //if generated hash equals entered hash, password found
                                if(hs.equals(s) == true)
                                {
                                    System.out.println("Password Found");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }   
}
