// 给你两个整数&nbsp;<code>num1</code> 和 <code>num2</code>，返回这两个整数的和。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>num1 = 12, num2 = 5
//<strong>输出：</strong>17
//<strong>解释：</strong>num1 是 12，num2 是 5 ，它们的和是 12 + 5 = 17 ，因此返回 17 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>num1 = -10, num2 = 4
//<strong>输出：</strong>-6
//<strong>解释：</strong>num1 + num2 = -6 ，因此返回 -6 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-100 &lt;= num1, num2 &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 80</li><li>👎 0</li></div>
package _2_algorithm.maths;

// 2235.两整数相加
// 开题时间：2022-11-05 08:31:52
public class AddTwoIntegers {
  public static void main(String[] args) {
    Solution solution = new AddTwoIntegers().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int sum(int num1, int num2) {
      return num1 + num2;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}