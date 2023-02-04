//<p>给定一个由 <strong>整数 </strong>组成的<strong> 非空</strong> 数组所表示的非负整数，在该数的基础上加一。</p>
//
//<p>最高位数字存放在数组的首位， 数组中每个元素只存储<strong>单个</strong>数字。</p>
//
//<p>你可以假设除了整数 0 之外，这个整数不会以零开头。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>digits = [1,2,3]
//<strong>输出：</strong>[1,2,4]
//<strong>解释：</strong>输入数组表示数字 123。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>digits = [4,3,2,1]
//<strong>输出：</strong>[4,3,2,2]
//<strong>解释：</strong>输入数组表示数字 4321。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>digits = [0]
//<strong>输出：</strong>[1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= digits.length &lt;= 100</code></li> 
// <li><code>0 &lt;= digits[i] &lt;= 9</code></li> 
//</ul>
//
//<div><li>👍 1143</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

import java.math.BigInteger;
import java.util.Arrays;

// 66.加一
// 开题时间：2022-12-29 12:28:38
public class PlusOne {
  public static void main(String[] args) {
    Solution solution = new PlusOne().new Solution();
    System.out.println(Arrays.toString(solution.plusOne(new int[]{1, 2, 3})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  //    import java.math.BigInteger;
  
  class Solution {
    public int[] plusOne9(int[] digits) {
      StringBuilder sb = new StringBuilder();
      for (int digit : digits)
        sb.append(digit);
      return new BigInteger(sb.toString()).add(new BigInteger("1")).toString().chars().map(v -> v - '0').toArray();
    }
    
    public int[] plusOne8(int[] digits) {
      int n = digits.length;
      for (int i = n - 1; i >= 0; i--) {
        if (digits[i] < 9) {
          digits[i]++;
          break;
        } else
          digits[i] = 0;
      }
      
      if (digits[0] == 0) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
      }
      return digits;
    }
    
    //☆☆☆☆☆ 精简版
    public int[] plusOne(int[] digits) {
      int n = digits.length;
      for (int i = n - 1; i >= 0; i--)
        if ((digits[i] = (digits[i] + 1) % 10) != 0)
          return digits;
      
      int[] ans = new int[n + 1];
      ans[0] = 1;
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}