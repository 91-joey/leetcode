//<p>给你一个整数数组 <code>nums</code> 。</p>
//
//<p>如果一组数字 <code>(i,j)</code> 满足 <code>nums[i]</code> == <code>nums[j]</code> 且 <code>i</code> &lt; <code>j</code> ，就可以认为这是一组 <strong>好数对</strong> 。</p>
//
//<p>返回好数对的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,2,3,1,1,3]
//<strong>输出：</strong>4
//<strong>解释：</strong>有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,1,1,1]
//<strong>输出：</strong>6
//<strong>解释：</strong>数组中的每组数字都是好数对</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,2,3]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 101</li><li>👎 0</li></div>
package _2_algorithm.maths;

import java.util.Arrays;

// 1512.好数对的数目
// 开题时间：2022-11-09 08:44:18
public class NumberOfGoodPairs {
  public static void main(String[] args) {
    Solution solution = new NumberOfGoodPairs().new Solution();
    System.out.println(-1 / 2);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 计数 + 乘法   n + D  D    (D = range of values of nums)
    public int numIdenticalPairs9(int[] nums) {
      int[] freq = new int[101];
      for (int num : nums)
        freq[num]++;
      
      return Arrays.stream(freq)
          .filter(value -> value != 0 && value != 1)
          .map(value -> value * (value - 1) / 2)
          .sum();
    }
    
    // 排序+遍历 nlogn   1
    public int numIdenticalPairs8(int[] nums) {
      int cnt = 0;
      Arrays.sort(nums);
      
      for (int l = 0, r = 1; r <= nums.length; r++) {
        if (r == nums.length || nums[l] != nums[r]) {
          int n = r - l;
          cnt += n * (n - 1) / 2;
          l = r;
        }
      }
      
      return cnt;
    }
    
    //☆☆☆☆☆ 计数 + 加法 n   D
    public int numIdenticalPairs(int[] nums) {
      int cnt = 0;
      int[] freq = new int[101];
      
      for (int num : nums)
        cnt += freq[num]++;
      
      return cnt;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}