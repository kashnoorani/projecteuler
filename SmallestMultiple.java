package projecteuler;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static test.Test.measure;


/**
 * Source: http://projecteuler.net/problem=5
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class SmallestMultiple
{
  @Test
  public void test()
  {
    assertEquals( smallestMultiple1( 10 ), 2520 );
    assertEquals( smallestMultiple1( 20 ), 232792560 );
    measure( o -> smallestMultiple1( 20 ) );
    assertEquals( smallestMultiple2( 10 ), 2520 );
    assertEquals( smallestMultiple2( 20 ), 232792560 );
    measure( o -> smallestMultiple2( 20 ) );
  }

  // brute force it
  private long smallestMultiple1( int maxRange )
  {
    int number = maxRange;
    while( true )
    {
      if( isDivisibleByRange( number, maxRange ) )
      {
        return number;
      }
      number++;
    }
  }

  private boolean isDivisibleByRange( int number, int maxRange )
  {
    for( int divisor = 2; divisor <= maxRange; divisor++ )
    {
      if( number % divisor != 0 )
      {
        return false;
      }
    }
    return true;
  }

  // be smart about it by detecting pattern

  /**
   * the pattern is that the smallest multiple is the product of all numbers up to maxRange but only once the numbers
   * are reduced by the whole division of the previous numbers. For example,
   * 2  3   4  5   6   7   8    9   10
   * 2  3   2  5   1   7   2    3    1 <- reduced factors
   * 2  6  12 60  60 420 840 2520 2520
   */
  private long smallestMultiple2( int maxRange )
  {
    List<Long> numbers = new ArrayList<>();
    for( int number = 2; number <= maxRange; number++ )
    {
      numbers.add( reduced( number, numbers ) );
    }
    return numbers.stream().reduce( ( i, j ) -> i * j ).get();
  }

  private long reduced( long number, List<Long> divisors )
  {
    long result = number;
    for( long divisor : divisors )
    {
      result = result % divisor == 0 ? result / divisor : result;
    }
    return result;
  }
}

