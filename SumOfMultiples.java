package projecteuler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static com.kash.codingpuzzles.test.Test.measure;

/**
 * Source: http://projecteuler.net/problem=1
 * Problem Definition: If we list all the natural numbers below 10 that are
 * multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class SumOfMultiples
{
  @Test
  public void test()
  {
    Assert.assertEquals( sumOfMultiples1( new int[]{ 3, 5 }, 1000 ), 233168 );
    Assert.assertEquals( sumOfMultiples2( new int[]{ 3, 5 }, 1000 ), 233168 );
    Assert.assertEquals( sumOfMultiples3( new int[]{ 3, 5 }, 1000 ), 233168 );
    Assert.assertEquals( sumOfMultiples3( new int[]{ 2, 3 }, 10 ), 32 );

    measure( o -> sumOfMultiples1( new int[]{ 3, 5 }, 10000000 ) );
    measure( o -> sumOfMultiples2( new int[]{ 3, 5 }, 10000000 ) );
    measure( o -> sumOfMultiples3( new int[]{ 3, 5 }, 10000000 ) );
  }

  private int sumOfMultiples1( int[] divisors, int upperLimit )
  {
    int sum = 0;
    for( int i = 0; i < upperLimit; i++ )
    {
      for( int divisor : divisors )
      {
        if( i % divisor == 0 )
        {
          sum += i;
          break;
        }
      }
    }
    return sum;
  }

  private int sumOfMultiples2( int[] divisors, int upperLimit )
  {
    return IntStream.range( 0, upperLimit ).filter( getPredicate( divisors ) ).sum();
  }

  private IntPredicate getPredicate( int[] divisors )
  {
    IntPredicate predicate = x -> false;
    for( int divisor : divisors )
    {
      predicate = predicate.or( n -> n % divisor == 0 );
    }
    return predicate;
  }

  private int sumOfMultiples3( int[] divisors, int upperLimit )
  {
    // recognize that this problem is resolved by a multiplication and division formula
    int sum = 0;
    for( int divisor : divisors )
    {
      sum += sumDivisibleBy( divisor, upperLimit );
    }
    return sum - sumDivisibleBy( multiply( divisors ), upperLimit );
  }

  private int multiply( int[] divisors )
  {
    //    return Arrays.stream( divisors ).reduce( ( n, m ) -> n * m );
    int m = 1;
    for( int divisor : divisors )
    {
      m *= divisor;
    }
    return m;
  }

  private int sumDivisibleBy( int divisor, int upperLimit )
  {
    int p = ( upperLimit - 1 ) / divisor;
    return divisor * p * ( p + 1 ) / 2;
  }
}
