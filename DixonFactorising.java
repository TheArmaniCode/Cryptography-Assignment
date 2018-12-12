import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class DixonFactorising {

    public static int sevenSmooth(BigDecimal x, int[] base, int[] counter) 
    {
        //create value for checking number of times through loop
        int checks = 0;
        //create BigDecimal number for sum
        BigDecimal sum = BigDecimal.valueOf(0);
        //while there is at least one number in the base left to check against current sum
        while(checks < 4)
        {
            for (int i = 0; i < 4; i++)
            {
                while (sum.remainder(BigDecimal.valueOf(base[i])) == BigDecimal.valueOf(0)) 
                {
                    //divide x for each value in base whenever divisible 
                    sum = x.divide(BigDecimal.valueOf(base[i]));
                    counter[i]++;
                    //reset checks to zero in order to check every base value against newly divided x
                    checks = 0;
                }
                //if x not divisible by specific base value anymore, increment checks
                if (sum.remainder(BigDecimal.valueOf(base[i])) != BigDecimal.valueOf(0))
                {
                    checks++;
                }
            }
        }
        //if sum = 1, n smooth, return 1, else return 0
        if (sum == BigDecimal.valueOf(1))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    //multiply function - multiplies each value used in base together
    public static int multiply(int base)
    {
        int sum = 1;
        sum *= base;
        return sum;
    }
    
    //checks for prime numbers
    public static int checkForPrime(BigDecimal n)
    {
        int f = 0;
        BigDecimal x = n.divide(BigDecimal.valueOf(2));
        
        //for i in range of possible factors (2-n/2)
        for (BigDecimal i = BigDecimal.valueOf(2);
            i.compareTo(x) > 0;
            i = i.add(BigDecimal.ONE))
        {
            //if remainder of any number tested is 0, not prime, so break
            if (n.remainder(i) == BigDecimal.valueOf(0))
            {
                f = 1;
                break;
            }
        }            
        
        //return 0 if prime, 1 if not prime
        if (f == 1)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public static void main(String args[]) 
    {
        //scan in number to factorise
        System.out.println("Please input a number");
        Scanner input = new Scanner(System.in);
        //
        BigInteger y = BigInteger.valueOf(0);
        int sum = 0, sevenSmooth = 0;
        int checks, x = 0;
        BigInteger n = input.nextBigInteger();
        int [] base = {2, 3, 5, 7};
        int [] counterFinal = {0, 0, 0, 0};
        int [][] pairsList = {};
        Random r = new Random();
        boolean factorsFound = false;
        //x = 20;
        
        while (factorsFound == false) 
        {
            //generate new random integer in range 0, n
            x = r.nextInt(n.intValue());
            //convert to Big Integer and Big Decimal for use in functions
            y = BigInteger.valueOf(x);
            BigDecimal z = BigDecimal.valueOf(x);
            //reset counter for each new value of x
            int [] counter = {0, 0, 0, 0};
            //System.out.println("x = " + x);
            //call seven smooth function, which will iterate until x is no longer divisible by base
            sevenSmooth = sevenSmooth(z, base, counter); 
            /*for (int i = 0; i < 4; i++)
            {
                System.out.println(counter[i]);
            }*/
            //create variable to count number of even
            int evens = 0;
            for (int i = 0; i < 4; i++) 
            {
                //if one value in counter is even, check if seven smooth
                if (counter[i] % 2 != 0) 
                {
                    System.out.println(sum);
                    if (sevenSmooth == 1)
                    {
                        //if sevenSmooth, add to PairsList
                        counter[counter.length] = x;
                        pairsList[pairsList.length] = counter;
                    }
                }
                //otherwise, if counter is even, increment evens
                else if (counter[i] % 2 == 0)
                {
                    evens++;
                }
                //if all 4 values in counter are even, check if any are zero
                if (evens == 4)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        //if one is not zero, add to final counter and exit loop
                        if (counter[j] != 0)
                        {
                            counterFinal[j] = counter[j];
                            factorsFound = true;
                        }
                    }
                }
            }
        }
        
        BigInteger baseSum = BigInteger.valueOf(1);
        //search pairslist
        for (int i = 0; i < pairsList.length-1; i++)
        {
            int pair = pairsList[i][4];
            int pair2 = pairsList[i+1][4];
            //if sum of values is even, assign to y
            if((pair+pair2) % 2 == 0)
            {
                y = BigInteger.valueOf(pair*pair2);
                for (int j = 0; j < 4; j++) 
                {
                    if (pairsList[i][j] != 0) 
                    {
                        //Multiply each value in base with count > 0 by each other
                        baseSum = baseSum.multiply(BigInteger.valueOf(base[i]));
                    }
                }
                break;
            }
        }
        //add and subtract sum1 from y respectively to find values to gcd
        BigInteger add = y.add(baseSum);
        BigInteger subtract = y.subtract(baseSum);
        //perform gcd on both values then convert to bigDecimal
        BigInteger f1 = add.gcd(n);
        BigInteger f2 = subtract.gcd(n);
        BigDecimal factor1 = new BigDecimal(f1);
        BigDecimal factor2 = new BigDecimal(f2);
        //run both values through checkForPrime
        int checkForPrime1 = checkForPrime(factor1);
        int checkForPrime2 = checkForPrime(factor2);
        //if either value is prime, no prime factors, else, print factor 1 & factor 2
        if (checkForPrime1 == 0) {
            System.out.println("No prime factors");
        } else if (checkForPrime2 == 0) {
            System.out.println("No prime factors");
        } else {
            System.out.println("Factors are " + factor1 + " & " + factor2);
        }
      
    }
}
