package _9_contest.week335;

import java.math.BigInteger;

//
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution);
  }
  
  // import java.math.BigInteger;
  class Solution {
    
    public int findValidSplit(int[] arr) {
      int n = arr.length; // length of array
      if (n < 2) return -1; // no valid split for single element array
      
      int gcd = 1;
      for (int i = 1, a = arr[0]; i < n; i++) {
        int x = gcd(a, arr[i]);
        gcd *= x;
        a /= x;
      }
      
      if (gcd == 1) {
        return 0;
      }
      
      for (int i = 1; i < n - 1; i++) {
        if (arr[i] % gcd == 0) {
          return i;
        }
        int x = gcd(gcd, arr[i]);
        gcd /= x;
      }
      
      return -1;
    }
    
    public int findValidSplitTLE(int[] arr) {
      // function to find smallest valid split index
      int n = arr.length; // length of array
      
      if (n < 2) return -1; // no valid split for single element array
      
      BigInteger leftProduct = BigInteger.valueOf(1); // initialize left subarray product with first element
      BigInteger rightProduct = BigInteger.valueOf(1); // initialize right subarray product
      for (int x : arr) {
        rightProduct = rightProduct.multiply(BigInteger.valueOf(x));
      }
      
      for (int i = 0; i < n - 1; i++) { // loop through possible split points from left to right
        leftProduct = leftProduct.multiply(BigInteger.valueOf(arr[i])); // update left subarray product with next element
        rightProduct = rightProduct.divide(BigInteger.valueOf(arr[i])); // update right subarray product
        
        if (leftProduct.gcd(rightProduct).equals(BigInteger.ONE)) {
          return i; // check if products are coprime and return index if true
        }
      }
      
      return -1; // no valid split found after checking all indices
    }
    
    public static int gcd(int a, int b) { // function to find GCD of two numbers
      if (b == 0) return a; // base case
      return gcd(b, a % b); // recursive case
    }
    
    public static boolean areCoprime(int a, int b) { // function to check if two numbers are coprime
      return gcd(a, b) == 1; // true if GCD is 1, false otherwise
    }
  }
}
