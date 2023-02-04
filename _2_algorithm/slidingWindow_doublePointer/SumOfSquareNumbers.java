//<p>给定一个非负整数&nbsp;<code>c</code>&nbsp;，你要判断是否存在两个整数 <code>a</code> 和 <code>b</code>，使得&nbsp;<code>a<sup>2</sup> + b<sup>2</sup> = c</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>c = 5
//<strong>输出：</strong>true
//<strong>解释：</strong>1 * 1 + 2 * 2 = 5
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>c = 3
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= c &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 403</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 633.平方数之和
// 开题时间：2022-11-29 11:13:38
public class SumOfSquareNumbers {
  public static void main(String[] args) {
    Solution solution = new SumOfSquareNumbers().new Solution();
    System.out.println(solution.judgeSquareSum(2147483646));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 双指针 + sqrt 函数
    public boolean judgeSquareSum9(int c) {
      for (int l = 0, r = (int) Math.sqrt(c); l <= r; ) {
        if (l * l == c - r * r)
          return true;
        if (l * l < c - r * r)
          l = (int) Math.ceil(Math.sqrt(c - r * r));
        else
          r = (int) Math.floor(Math.sqrt(c - l * l));
      }
      
      return false;
    }
    
    //☆☆☆☆☆ 数学：费曼平方和（一个非负整数 c 如果能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k+3 的质因子所对应的指数均为偶数。）
    public boolean judgeSquareSum8(int c) {
      for (int i = 2; i * i <= c; i++) {
        if (c % i == 0) {
          int exp = 0;
          while (c % i == 0) {
            c /= i;
            exp++;
          }
          if (exp % 2 == 1 && i % 4 == 3)
            return false;
        }
      }
      return c % 4 != 3;
    }
    
    // sqrt 函数
    public boolean judgeSquareSum7(int c) {
      int bound = (int) Math.sqrt(c);
      for (int i = 0; i <= bound; i++) {
        double sqrt = Math.sqrt(c - i * i);
        if (sqrt == (int) sqrt)
          return true;
      }
      return false;
    }
    
    // 双指针
    public boolean judgeSquareSum(int c) {
      int l = 0;
      int r = (int) Math.sqrt(c);
      while (l <= r) {
        if (l * l == c - r * r)
          return true;
        else if (l * l > c - r * r)
          r--;
        else
          l++;
      }
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}