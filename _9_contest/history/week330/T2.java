package _9_contest.history.week330;

import java.math.BigInteger;

// 6338. Count Collisions of Monkeys on a Polygon
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution.monkeyMove9(55));
    // System.out.println(solution.monkeyMove(500000003));
    // System.out.println(solution.monkeyMove(1000000000));
  }
  
  class Solution {
    /*
     * 正难则反，逆向思维：只有全部顺时针和全部逆时针才不会碰撞，因此答案为 2^n - 2
     *
     * 快速幂
     * 注意：
     *  - 快速幂的基数需要定义为 long 型，以避免数值溢出
     *  - 最后 - 2 可能会出现负数，需要先转正，再取模
     */
    public int monkeyMove9(int n) {
      return (pow(2, n) - 2 + 1000000007) % 1000000007;
    }
    
    // ☆☆☆☆☆ 大数 API
    public int monkeyMove(int n) {
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
