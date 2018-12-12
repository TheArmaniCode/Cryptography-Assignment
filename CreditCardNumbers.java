import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Owner
 */
public class creditCardNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create blank integer to become total of all digits
        int mod = 0;
        System.out.println("Please input a number");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        //create empty array
        int[] d = {};
        
        //for each character in String, add to array
        for (int i=0; i<s.length(); i++)
        {
            //multiply each 2nd digit by 2
            if (i%2 == 0)
            {
                d[i] *= 2;
            }
            //if a digit is greater than 10, subtract 9
            if (d[i] < 10)
            {
                d[i] -= 9;
            }
              //add each digit to total
              mod += d[i];
        } 
        
        //modulus 10 the final total
        int luhn = mod % 10;
        
        //if mod 10 is zero, valid credit card number
        if (luhn == 0)
        {
            System.out.println("No error");
        }
        else
        {
            System.out.println("Invalid Number");
        }
        
        for (int i = 0; i < s.length(); i++)
        {
            mod += d[0]*(d[0]+1);
        } 
        
        int isbn = mod % 11;
        
        if (isbn == 0)
        {
            System.out.println("Valid number");
        }
        
        else
        {
            System.out.println("Invalid number");
        }       // TODO add your handling code here:
    }
}
