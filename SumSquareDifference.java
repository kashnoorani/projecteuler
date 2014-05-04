package projecteuler;

import org.testng.annotations.Test;

import static java.lang.Math.pow;
import static java.util.stream.IntStream.range;
import static test.Test.measureAndAssertEquals;

/**
 * Source: http://projecteuler.net/problem=6
 * The sum of the squares of the first ten natural numbers is,
 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)^2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten
 * natural numbers and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one
 * hundred natural numbers and the square of the sum.
 */
public class SumSquareDifference
{
  @Test
  public void test()
  {
    measureAndAssertEquals( o -> sumsquareDifference1( 10 ), 2640 );
    measureAndAssertEquals( o -> sumsquareDifference1( 100 ), 25164150 );

    measureAndAssertEquals( o -> sumsquareDifference2( 10 ), 2640 );
    measureAndAssertEquals( o -> sumsquareDifference2( 100 ), 25164150 );
  }

  private int sumsquareDifference1( int upto )
  {
    // brute force it
    return ( int ) pow( range( 1, upto + 1 ).sum(), 2 ) - range( 1, upto + 1 ).map( n -> n * n ).sum();
  }

  private int sumsquareDifference2( int n )
  {
    // be smart about it
    return ( int ) ( pow( n * ( n + 1 ) / 2, 2 ) - ( n * ( n + 1 ) * ( 2 * n + 1 ) ) / 6 );
  }
}

