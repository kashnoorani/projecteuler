package projecteuler;

import org.testng.annotations.Test;

import static com.kash.codingpuzzles.test.Test.measureAndAssertEquals;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Source: http://projecteuler.net/problem=9
 * A Pythagorean triplet is a set of three natural numbers,
 * a < b < c, for which, a^2 + b^2 = c^2. For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class SpecialPythagoreanTriplet
{
  @Test
  public void test()
  {
    measureAndAssertEquals( o -> findSpecialPythagoreanTriplet1( 1000 ), 31875000L );
    measureAndAssertEquals( o -> findSpecialPythagoreanTriplet2( 1000 ), 31875000L );
    measureAndAssertEquals( o -> findSpecialPythagoreanTriplet3( 1000 ), 31875000L );
  }

  private long findSpecialPythagoreanTriplet1( int sum )
  {
    // brute force it
    for( int a = 1; a <= sum - 2; a++ )
    {
      for( int b = a + 1; b <= sum - 2; b++ )
      {
        for( int c = b + 1; c <= sum - 2; c++ )
        {
          if( a * a + b * b == c * c && a + b + c == sum )
          {
            return a * b * c;
          }
        }
      }
    }
    return -1;
  }

  private long findSpecialPythagoreanTriplet2( int sum )
  {
    // be smarter about it
    // a + b + c = sum -> a^2 + ab + ac + ab + b^2 + bc + ac + bc + c^2 = sum
    // a^2 + b^2 + c^2 + 2( ab + ac + bc ) = sum^2 -> 2c^2 + 2( ab + ac + bc ) = sum^2
    // c^2 + ab + c( a + b ) = sum^2 / 2 -> c^2 + ab + c( sum - c ) = sum^2 / 2
    // ab + c * sum = sum^2 / 2 -> ab + sum * sqrt( a^2 + b^2 ) = sum^2 / 2
    for( int a = 1; a <= sum - 2; a++ )
    {
      for( int b = a + 1; b <= sum - 2; b++ )
      {
        if( a * b + sum * sqrt( a * a + b * b ) == sum * sum / 2 )
        {
          return a * b * ( sum - a - b );
        }
      }
    }
    return -1;
  }

  private long findSpecialPythagoreanTriplet3( int sum )
  {
    // be even more smarter about it
    // using Euclid's formula -> a = m^2 - n^2, b = 2mn, c = m^2 + n^2 where m > n
    // Euclid doesn't always work but its quick so worth trying before falling back to longer calculation
    // a + b + c = sum -> m^2 - n^2 + 2mn + m^2 + n^2 = sum -> 2m^2 + 2mn = sum
    // m^2 + mn = sum / 2 -> n = ( sum / 2 - m^2 )/m -> n = ( sum / 2m ) - m
    for( int m = 1; m <= sum; m++ )
    {
      int n = sum / ( 2 * m ) - m;
      int a = ( int ) ( pow( m, 2 ) - pow( n, 2 ) );
      int b = 2 * m * n;
      int c = ( int ) ( pow( m, 2 ) + pow( n, 2 ) );
      if( a > 0 && b > 0 && c > 0 & a + b + c == sum )
      {
        return a * b * c;
      }
    }
    return -1;
  }
}