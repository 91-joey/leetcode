//<p>给你一个整数数组&nbsp;<code>nums</code>，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 <strong>任意顺序</strong> 返回答案。</p>
//
//<p>你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,1,3,2,5]
//<strong>输出：</strong>[3,5]
//<strong>解释：</strong>[5, 3] 也是有效的答案。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,0]
//<strong>输出：</strong>[-1,0]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,1]
//<strong>输出：</strong>[1,0]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
// <li>除两个只出现一次的整数外，<code>nums</code> 中的其他数字都出现两次</li> 
//</ul>
//
//<div><li>👍 690</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

import java.util.Arrays;

// 260.只出现一次的数字 III
// 开题时间：2023-01-13 14:17:33
public class SingleNumberIii {
  public static void main(String[] args) {
    Solution solution = new SingleNumberIii().new Solution();
    System.out.println(-Integer.MIN_VALUE);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] singleNumber9(int[] nums) {
      int xor = Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt();
      int mask = xor & (-xor);
      int[] ans = new int[2];
      for (int num : nums)
        if ((num & mask) == 0)
          ans[0] ^= num;
        else
          ans[1] ^= num;
      return ans;
    }
    
    /*
     * 位运算 + 分治思想
     * a & (-a) 可以获得 a 的 LSB（ Least Significant Bit，最低有效位：最低的 1 位）
     *      num & lsb == 0，分为一组，元素一出现一次，其余元素出现两次
     *      num & lsb != 0，分为一组，元素二出现一次，其余元素出现两次
     */
    public int[] singleNumber(int[] nums) {
      int xor = Arrays.stream(nums)
          .reduce((x, y) -> x ^ y)
          .getAsInt();
      int lsb = xor & (-xor);
      
      int a = Arrays.stream(nums)
          .filter(num -> (num & lsb) == 0)
          .reduce((x, y) -> x ^ y)
          .getAsInt();
      
      return new int[]{a, a ^ xor};
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}