package _9_contest.week334;

import java.math.BigInteger;
import java.util.Arrays;

//
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(Arrays.toString(solution.divisibilityArray("998244353", 3)));
    // System.out.println(Arrays.toString(solution.divisibilityArray("1010", 10)));
  }
  
  class Solution {
    
    public int[] divisibilityArray(String word, int m) {
      int n = word.length();
      int[] ans = new int[n];
      int digit = word.charAt(0) - '0';
      ans[0] = digit % m == 0 ? 1 : 0;
      BigInteger sum = BigInteger.valueOf(digit);
      for (int i = 1, gcd = 0; i < word.length(); i++) {
        sum = sum.multiply(BigInteger.TEN);
        digit = word.charAt(i) - '0';
        if (ans[i - 1] == 1) {
          ans[i] = digit % m == 0 ? 1 : 0;
          sum = sum.add(BigInteger.valueOf(digit));
        } else {
          sum = sum.add(BigInteger.valueOf(digit));
          ans[i] = sum.mod(BigInteger.valueOf(m)).equals(BigInteger.ZERO) ? 1 : 0;
          // ans[i] = sum.gcd(BigInteger.valueOf(digit)).mod(BigInteger.valueOf(m)).equals(BigInteger.ZERO) ? 1 : 0;
        }
      }
      return ans;
    }
    
    public static int gcd(int a, int b) {
      while (a != 0) {
        int tmp = a;
        a = b % a;
        b = tmp;
      }
      return b;
    }
  }
}
