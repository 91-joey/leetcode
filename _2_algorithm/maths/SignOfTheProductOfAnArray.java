//<p>已知函数&nbsp;<code>signFunc(x)</code> 将会根据 <code>x</code> 的正负返回特定值：</p>
//
//<ul> 
// <li>如果 <code>x</code> 是正数，返回 <code>1</code> 。</li> 
// <li>如果 <code>x</code> 是负数，返回 <code>-1</code> 。</li> 
// <li>如果 <code>x</code> 是等于 <code>0</code> ，返回 <code>0</code> 。</li> 
//</ul>
//
//<p>给你一个整数数组 <code>nums</code> 。令 <code>product</code> 为数组 <code>nums</code> 中所有元素值的乘积。</p>
//
//<p>返回 <code>signFunc(product)</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,-2,-3,-4,3,2,1]
//<strong>输出：</strong>1
//<strong>解释：</strong>数组中所有值的乘积是 144 ，且 signFunc(144) = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,5,0,2,-3]
//<strong>输出：</strong>0
//<strong>解释：</strong>数组中所有值的乘积是 0 ，且 signFunc(0) = 0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,1,-1,1,-1]
//<strong>输出：</strong>-1
//<strong>解释：</strong>数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>-100 &lt;= nums[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 84</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// 1822.数组元素积的符号
// 开题时间：2022-12-04 11:50:34
public class SignOfTheProductOfAnArray {
  public static void main(String[] args) {
    Solution solution = new SignOfTheProductOfAnArray().new Solution();
    System.out.println(solution.arraySign(new int[]{41, 65, 14, 80, 20, 10, 55, 58, 24, 56, 28, 86, 96, 10, 3, 84, 4, 41, 13, 32, 42, 43, 83, 78, 82, 70, 15, -41}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int arraySign9(int[] nums) {
      int sign = 1;
      for (int num : nums)
        sign *= Integer.signum(num);
      return sign;
    }
    
    public int arraySign8(int[] nums) {
      int cntNeg = 0;
      for (int num : nums) {
        if (num == 0)
          return 0;
        else if (num < 0)
          cntNeg++;
      }
      return cntNeg % 2 == 0 ? 1 : -1;
    }
    
    public int arraySign(int[] nums) {
      int sign = 1;
      for (int num : nums) {
        if (num == 0)
          return 0;
        else if (num < 0)
          sign = -sign;
      }
      return sign;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}