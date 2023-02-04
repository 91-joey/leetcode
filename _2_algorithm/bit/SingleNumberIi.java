//<p>给你一个整数数组&nbsp;<code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>
//
//<p>你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,3,2]
//<strong>输出：</strong>3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,1,0,1,0,1,99]
//<strong>输出：</strong>99
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
// <li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li> 
//</ul>
//
//<div><li>👍 962</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

// 137.只出现一次的数字 II
// 开题时间：2023-01-12 21:30:04
public class SingleNumberIi {
  public static void main(String[] args) {
    Solution solution = new SingleNumberIi().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 遍历计数 + 取余
    public int singleNumber9(int[] nums) {
      int[] freq = new int[32];
      for (int num : nums) {
        for (int i = 0; i < 32; i++) {
          freq[i] += (num & 1);
          num >>= 1;
        }
      }
      
      int ans = 0;
      for (int i = 0; i < 32; i++)
        ans |= ((freq[i] % 3) << i);
      return ans;
    }
    
    //☆☆☆☆☆ 有限状态自动机（数字电路设计）
    public int singleNumber(int[] nums) {
      int ones = 0, twos = 0;
      for (int num : nums) {
        ones ^= num & ~twos;
        twos ^= num & ~ones;
      }
      return ones;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}