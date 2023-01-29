package org.example.leetcode.problems._9_contest.week330;

import java.math.BigInteger;

//
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution.monkeyMove(500000003));
    // System.out.println(solution.monkeyMove(1000000000));
  }
  
  class Solution {
    public int monkeyMove(int n) {
      // return (pow(2, n) - 2 + 1000000007) % 1000000007;
      return (BigInteger.TWO.modPow(BigInteger.valueOf(n), BigInteger.valueOf(1000000007)).intValue() + 1000000005) % 1000000007;
    }
    
    public static int pow(long base, int exponent) {
      long result = 1;
      while (exponent > 0) {
        if ((exponent & 1) == 1) {
          result = (result * base) % 1000000007;
        }
        base = (base * base) % 1000000007;
        exponent >>= 1;
      }
      return (int) result;
    }
    
  }
}
