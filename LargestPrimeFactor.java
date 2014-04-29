package projecteuler;

import org.testng.annotations.Test;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static org.testng.Assert.assertEquals;
import static test.Test.measure;

/**
 * Source: http://projecteuler.net/problem=3
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */
public class LargestPrimeFactor
{
  @Test
  public void test()
  {
    assertEquals( largestPrimeFactor1( 13195 ), 29 );
    measure( o -> largestPrimeFactor1( 13195 ) );
    assertEquals( largestPrimeFactor1( 600851475143L ), 6857 );
    measure( o -> largestPrimeFactor1( 600851475143L ) );

    assertEquals( largestPrimeFactor2( 13195 ), 29 );
    measure( o -> largestPrimeFactor2( 13195 ) );
    assertEquals( largestPrimeFactor2( 600851475143L ), 6857 );
    measure( o -> largestPrimeFactor2( 600851475143L ) );
  }

  private long largestPrimeFactor1( long n )
  {
    // find list of divisors, sort by largeest and then check for prime using the
    // sieve algo - see if there is any remainder when dividing by any number up
    // to sqrt( n ). Eliminate obvious divisors such as even numbers and multiples
    // of previously tried divisors
    long initial = round( sqrt( n ) );
    for( long divisor = initial % 2 == 0 ? initial + 1 : initial; divisor >= 2; divisor -= 2 )
    {
      if( n % divisor == 0 && isPrime( divisor ) )
      {
        return divisor;
      }
    }
    return 0;
  }

  private boolean isPrime( long n )
  {
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

  private long largestPrimeFactor2( long n )
  {
    long divisor = 1;
    while( n > 1 )
    {
      divisor += 2;
      if( n % divisor == 0 )
      {
        n = n / divisor;
      }
    }
    return divisor;
  }

}

