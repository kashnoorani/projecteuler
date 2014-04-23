package projecteuler;

import org.testng.annotations.Test;

import static com.kash.codingpuzzles.test.Test.measure;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.testng.Assert.assertEquals;

/**
 * Source: http://projecteuler.net/problem=7
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
public class GetNthPrime
{
  @Test
  public void test()
  {
    assertEquals( getNthPrime1( 6 ), 13 );
    measure( o -> getNthPrime1( 6 ) );
    assertEquals( getNthPrime1( 10001 ), 104743 );
    measure( o -> getNthPrime1( 10001 ) );
  }

  private long getNthPrime1( int n )
  {
    // brute force it
    int i = 2, count = 0;
    while( true )
    {
      if( isPrime( i ) )
      {
        count++;
        if( count == n )
        {
          return i;
        }
      }
      i++;
    }
  }

  private boolean isPrime( long n )
  {
    if( n == 2 || n == 3 )
    {
      return true;
    }
    else if( n % 2 == 0 )
    {
      return false;
    }
    long initial = round( sqrt( n ) );
    for( long divisor = initial % 2 == 0 ? initial + 1 : initial; divisor >= 2; divisor -= 2 )
    {
      if( n % divisor == 0 )
      {
        return false;
      }
    }
    return true;
  }
}

