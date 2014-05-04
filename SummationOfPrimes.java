package projecteuler;

import org.testng.annotations.Test;

import static java.lang.StrictMath.sqrt;
import static test.Test.measureAndAssertEquals;

/**
 * Source: http://projecteuler.net/problem=10
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
public class SummationOfPrimes
{
  @Test
  public void test()
  {
    measureAndAssertEquals( o -> findSummationOfPrimes1( 10 ), 17L );
    System.out.println( findSummationOfPrimes1( 20000000 ) );
    //    measureAndAssertEquals( o -> findSummationOfPrimes1( 2000000 ), 17 );
  }

  private long findSummationOfPrimes1( int n )
  {
    // brute force it
    long sum = 2;
    for( int i = 3; i < n; i++ )
    {
      if( isPrime( i ) )
      {
        sum += i;
      }
    }
    return sum;
  }

  private boolean isPrime( int num )
  {
    if( num == 2 )
    {
      return true;
    }

    if( num % 2 == 0 )
    {
      return false;
    }

    for( int i = 3; i <= sqrt( num ); i += 2 )
    {
      if( num % i == 0 )
      {
        return false;
      }
    }
    return true;
  }
}