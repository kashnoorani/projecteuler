package projecteuler;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

import static test.Test.measure;
import static java.lang.Math.*;

/**
 * Source: http://projecteuler.net/problem=2
 * Each new term in the Fibonacci sequence is generated by adding the previous two
 * terms. By starting with 1 and 2, the first 10 terms will be:
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * By considering the terms in the Fibonacci sequence whose values do not exceed
 * four million, find the sum of the even-valued terms.
 */
public class SumOfEvenFibonaccis
{
  @Test
  public void test()
  {
    Assert.assertEquals( sumOfEvenFibonaccis1( 100 ), 44 );
    Assert.assertEquals( sumOfEvenFibonaccis1( 4000000 ), 4613732 );
    measure( o -> sumOfEvenFibonaccis1( 4000000 ) );

    Assert.assertEquals( sumOfEvenFibonaccis2( 100 ), 44 );
    Assert.assertEquals( sumOfEvenFibonaccis2( 4000000 ), 4613732 );
    measure( o -> sumOfEvenFibonaccis2( 4000000 ) );
  }

  private long sumOfEvenFibonaccis1( long upperLimit )
  {
    return generateFibonnacisTilLimit( upperLimit, fibonacci -> fibonacci % 2 == 0 ).sum();
  }

  private LongStream generateFibonnacisTilLimit( long upperLimit, LongPredicate predicate )
  {
    long fibonacci_prev = 0, fibonacci_prev_prev = 1;

    LongStream.Builder builder = LongStream.builder();
    long nextFibonacci = fibonacci_prev + fibonacci_prev_prev;
    while( nextFibonacci <= upperLimit )
    {
      if( predicate.test( nextFibonacci ) )
      {
        builder.add( nextFibonacci );
      }
      nextFibonacci = fibonacci_prev + fibonacci_prev_prev;
      fibonacci_prev_prev = fibonacci_prev;
      fibonacci_prev = nextFibonacci;
    }
    return builder.build();
  }

  private long sumOfEvenFibonaccis2( long upperLimit )
  {
    // The ratio between consecutive even terms approaches phi ^ 3 because each 3rd term is even
    long nextFibonacci = 2, sum = 0;
    while( nextFibonacci <= upperLimit )
    {
      //    phi i.e. Golden Ratio = 1 + sqrt( 5 ) / 2
      sum += nextFibonacci;
      nextFibonacci = round( nextFibonacci * pow( ( 1 + sqrt( 5 ) ) / 2, 3 ) );
    }
    return sum;
  }

}

