//<p>给你两个整数 <code>a</code> 和 <code>b</code> ，<strong>不使用 </strong>运算符&nbsp;<code>+</code> 和&nbsp;<code>-</code>&nbsp;​​​​​​​，计算并返回两整数之和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>a = 1, b = 2
//<strong>输出：</strong>3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>a = 2, b = 3
//<strong>输出：</strong>5
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-1000 &lt;= a, b &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 649</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

// 371.两整数之和
// 开题时间：2023-01-05 18:35:50
public class SumOfTwoIntegers {
  public static void main(String[] args) {
    Solution solution = new SumOfTwoIntegers().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int getSum9(int a, int b) {
      int sum = 0;
      for (int i = 0, carry = 0; i < 32; i++) {
        int x = a & 1;
        int y = b & 1;
        sum += (x ^ y ^ carry) << i;
        carry = ((x & y) == 1 || (x & carry) == 1 || (y & carry) == 1) ? 1 : 0;
        a >>>= 1;
        b >>>= 1;
      }
      return sum;
    }
    
    //☆☆☆☆☆ 位运算：两数之和 = 无进位和 + 进位和
    public int getSum(int a, int b) {
      while (b != 0) {
        int carry = (a & b) << 1;// 进位和
        a = a ^ b;// 无进位和
        b = carry;
      }
      return a;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}