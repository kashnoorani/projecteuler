import time

def main( ) :
  max = maxI = maxJ = 0
  i = 999
  j = 990
  while (i > 100) :
    j = 990
    while (j > 100) :
      product = i * j
      if (product > max) :
        productString = str( product )
        if (productString == productString[ : :-1 ]) :
          max = product
          maxI = i
          maxJ = j
      j -= 11
    i -= 1
  return max, maxI, maxJ

start_time = time.time( )
main( )
print 1000 * (time.time( ) - start_time), "ms"