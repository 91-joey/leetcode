//<p>给你一个非负整数 <code>x</code> ，计算并返回&nbsp;<code>x</code>&nbsp;的 <strong>算术平方根</strong> 。</p>
//
//<p>由于返回类型是整数，结果只保留 <strong>整数部分 </strong>，小数部分将被 <strong>舍去 。</strong></p>
//
//<p><strong>注意：</strong>不允许使用任何内置指数函数和算符，例如 <code>pow(x, 0.5)</code> 或者 <code>x ** 0.5</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>x = 4
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>x = 8
//<strong>输出：</strong>2
//<strong>解释：</strong>8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 1174</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

// 69.x 的平方根
// 开题时间：2022-10-28 16:27:30
public class Sqrtx {
  public static void main(String[] args) {
    Solution solution = new Sqrtx().new Solution();
    System.out.println(solution.mySqrt(2147395599));
    System.out.println(Math.sqrt(Integer.MAX_VALUE));
    System.out.println(Integer.MAX_VALUE);
    // int:  [-2^31,2^31-1]
    // long: [-2^63,2^63-1]
    
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 二分查找（自解），使用 46340 作为右边界
    public int mySqrt(int x) {
      int l = 0, r = 46340;
      while (l <= r) {
        int mid = l + (r - l) / 2;
        int product = mid * mid;
        if (x == product)
          return mid;
        else if (x < product)
          r = mid - 1;
        else
          l = mid + 1;
      }
      return r;
    }
    
    // 袖珍计算器算法   x^0.5 = (e^lnx)^0.5 = e^(0.5*lnx)
    public int mySqrt2(int x) {
      if (x == 0) return 0;
      int ans = (int) Math.exp(0.5 * Math.log(x));
      return (long) (ans + 1) * (ans + 1) <= x ? (ans + 1) : ans;
    }
    
    // 二分查找，使用 x 作为右边界，平方值溢出用「转换long」处理
    public int mySqrt3(int x) {
      int l = 0, r = x;
      while (l <= r) {
        int mid = l + (r - l) / 2;
        long product = (long) mid * mid;
        if (x == product)
          return mid;
        else if (x < product)
          r = mid - 1;
        else
          l = mid + 1;
      }
      return r;
    }
    
    public int mySqrt4(int x) {
      int l = 0, r = x;
      while (l < r) {
        int mid = l + (r - l) / 2;
        long product = (long) mid * mid;
        if (x <= product)
          r = mid;
        else
          l = mid + 1;
      }
      return l * l == x ? l : l - 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}