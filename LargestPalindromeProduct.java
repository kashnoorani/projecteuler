package projecteuler;

import org.testng.annotations.Test;

import java.util.function.Predicate;

import static com.kash.codingpuzzles.test.Test.measure;
import static java.lang.String.valueOf;
import static org.testng.Assert.assertEquals;

/**
 * Source: http://projecteuler.net/problem=4
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindromeProduct
{
  @Test
  public void test()
  {
    assertEquals( isPalindrome1( 9009 ), true );
    assertEquals( isPalindrome1( 909 ), true );
    assertEquals( isPalindrome1( 109 ), false );
    assertEquals( isPalindrome1( 8888 ), true );
    assertEquals( isPalindrome1( 2022 ), false );
    assertEquals( isPalindrome2( 9009 ), true );
    assertEquals( isPalindrome2( 909 ), true );
    assertEquals( isPalindrome2( 109 ), false );
    assertEquals( isPalindrome2( 8888 ), true );
    assertEquals( isPalindrome2( 2022 ), false );

    assertEquals( largestPalindrome1( this::isPalindrome1 ), 906609 );
    measure( o -> largestPalindrome1( this::isPalindrome1 ) );
    measure( o -> largestPalindrome1( this::isPalindrome2 ) );
    measure( o -> largestPalindrome2() );
  }

  private int largestPalindrome1( Predicate<Integer> comparator )
  {
    int largestPalindrome = 0;
    // brute force
    for( int i = 999; i >= 100; i-- )
    {
      for( int j = 999; j >= 100; j-- )
      {
        int product = i * j;
        if( comparator.test( product ) && product > largestPalindrome )
        {
          largestPalindrome = product;
        }
      }
    }
    return largestPalindrome;
  }

  private boolean isPalindrome1( int n )
  {
    String number = valueOf( n );
    int numLength = number.length();
    for( int i = 0; i < numLength / 2; i++ )
    {
      if( number.charAt( i ) != number.charAt( numLength - 1 - i ) )
      {
        return false;
      }
    }
    return true;
  }

  private boolean isPalindrome2( int n )
  {
    String number = valueOf( n );
    return number.equals( new StringBuilder( number ).reverse().toString() );
  }

  private int largestPalindrome2()
  {
    // The palindrome can be written as: abccba
    // Which then simpifies to: 100000 a + 10000 b + 1000 c + 100 c + 10 b + a
    // And then: 100001 a + 10010 b + 1100 c
    // Factoring out 11, you get: 11 ( 9091 a + 910 b + 100 c)
    // So the palindrome must be divisible by 11. Seeing as 11 is prime, at least one of the numbers must be 
    // divisible by 11. So brute force in Python, only with less numbers to be checked:
    int max = 0, i = 999, j;
    while( i > 100 )
    {
      j = 990;
      while( j > 100 )
      {
        int product = i * j;
        if( product > max )
        {
          String productString = String.valueOf( product );
          if( productString.equals( new StringBuilder( productString ).reverse().toString() ) )
          {
            max = product;
          }
        }
        j -= 11;
        i -= 1;
      }
    }
    return max;
  }
}

