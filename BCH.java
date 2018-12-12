import java.util.Scanner;


public class BCH {

    static int[] sqrt = {0, 1, -1, 5, 2, 4, -1, -1, -1, 3, -1};
    static int[] inverse = {0, 1, 6, 4, 3, 9, 2, 8, 7, 5, 10};
    
    public static int mod11(int num1)
    {
        int mod11 = num1 % 11;
        if (mod11 < 0)
        {
            mod11 += 11;
        }
        return mod11;
    }
    
    public static int inverse(int num1, int[] array)
    {
        int inv = array[mod11(num1)];
        return inv;
    }
    
    public static int sqrt(int num1, int[] array)
    {
        int sqrt = array[mod11(num1)];
        return sqrt;
    }
    
    
    public static void main(String[] args) {
        //create empty array of size 10
        int[] d = new int[10];
    
        Scanner inp = new Scanner(System.in);
        String s = inp.nextLine();
        String input = "";
        
        for (int i = 0; i < s.length(); i++)
        {
            //for each character in String, convert to integer then add to array
            d[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
            input += d[i];
        }
        
        int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
        
        for (int i = 0; i < d.length; i++)
        {
            //every calculation done in this code is always to mod11
            s1 += mod11(d[i]);
            s2 += mod11(i * d[i]);
            s3 += mod11((i * i) * d[i]);
            s4 += mod11((i * i * i ) * d[i]);
        }
        
        //Calculate s1, s2, s3 & s4
        s1 = mod11(s1);
        s2 = mod11(s2);
        s3 = mod11(s3);
        s4 = mod11(s4);
        
        //Calculate p q & r
        int p = mod11((s2 * s2) - (s1 * s3));
        int q = mod11((s1 * s4) - (s2 * s3));
        int r = mod11((s3 * s3) - (s2 * s4));        
        
        //Calculate quadratic 
        int quad = mod11(sqrt(q*q-4*p*r, sqrt));
        
        int i = 0, j = 0, b = 0, a = 0;
        
        //if quadratic is valid, use it to find i, j, b, & a
        if (quad > -1)
        {
            i = mod11(-q + quad);
            j = mod11(-q - quad); //correct
            //run inverse function
            b = mod11((i*s1-s2)* inverse(i-j, inverse));
            a = mod11(s1 - b);
        }
        
        //find error position by mulitplying s2 by the inverse of s1
        int position = mod11(s2 * inverse(s1, inverse));
        
        //if s1, s2, s3, & s4 all 0, no error
        if (s1 == 0 && s2 == 0 && s3 == 0 && s4 == 0)
        {
            System.out.println("No error");
            String output = "";
            //add String to output and exit
            for (int k = 0; k < d.length; k++) 
            {
                output += d[k];
            }
            return;
        }
        
        //if p, q and r all 0, single error
        if (p == 0 && q == 0 && r == 0)
        {
            //crete correct value by subtracting s1 from value in d at error position
            int c = mod11(d[position] - s1);
            //set c as new value at error position
            d[position] = c;
            System.out.println("Single error ");
            String output = "";
            //set output as corrected number
            for (int k = 0; k < d.length; k++) 
            {
                output += d[k];
            }

            System.out.println("Input = " + input + " Output = " + output);
            return;
        }
        
        else
        {
            //if b is less than 0, more than two errors, else two errors
            if (b < 0)
            {
                System.out.println("More than two errors");
            }
            
            else
            {
                System.out.println("Two errors");
                //set c as first new value by subtracting value at first error position, i by a
                int c = d[i] - a;
                d[i] = mod11(c);
                //set e as second new value by subtracting value at second error position, i by b
                int e = d[j] - b;
                d[j] = mod11(e);  
                //set output as corrected number
                String output = "";
                for (int k = 0; k < d.length; k++) 
                {
                    output += d[k];
                }
                //print input and output
                System.out.println("Input = " + input + " Output = " + output);
                return;
            }
        }
    }
    
}
