package _9_contest.history.week334;

import java.math.BigInteger;
import java.util.Arrays;

// 6368. Find the Divisibility Array of a String
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(Arrays.toString(solution.divisibilityArray("998244353", 3)));
    // System.out.println(Arrays.toString(solution.divisibilityArray("1010", 10)));
  }
  
  
  // import java.math.BigInteger;
  
  class Solution {
    /*
     * 暴力优化仍旧TLE
     *  [0:i-1]所代表的数能被 m 整除，则[0:i]所代表的数能否被 m 整除 取决于 nums[i] 能否被 m 整除
     */
    public int[] divisibilityArrayTLE(String word, int m) {
      int n = word.length();
      int[] ans = new int[n];
      int digit = word.charAt(0) - '0';
      ans[0] = digit % m == 0 ? 1 : 0;
      BigInteger sum = BigInteger.valueOf(digit);
      
      for (int i = 1; i < n; i++) {
        digit = word.charAt(i) - '0';
        sum = sum.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        if (ans[i - 1] == 1) {
          ans[i] = digit % m == 0 ? 1 : 0;
        } else {
          ans[i] = sum.mod(BigInteger.valueOf(m)).equals(BigInteger.ZERO) ? 1 : 0;
        }
      }
      
      return ans;
    }
    
    // ☆☆☆☆☆ 取余递推
    public int[] divisibilityArray(String word, int m) {
      int n = word.length();
      int[] ans = new int[n];
      for (int i = 0, remainder = 0; i < n; i++) {
        remainder = (int) ((10L * remainder + word.charAt(i) - '0') % m);
        ans[i] = remainder == 0 ? 1 : 0;
      }
      return ans;
    }
  }
}
