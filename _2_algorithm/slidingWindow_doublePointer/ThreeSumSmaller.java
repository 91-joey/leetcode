//<p>给定一个长度为 <code>n</code> 的整数数组和一个目标值 <code>target</code>&nbsp;，寻找能够使条件&nbsp;<code>nums[i] + nums[j] + nums[k] &lt; target</code>&nbsp;成立的三元组&nbsp; <code>i, j, k</code>&nbsp;个数（<code>0 &lt;= i &lt; j &lt; k &lt; n</code>）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入: </strong><em>nums</em> = <span><code>[-2,0,1,3]</code></span>, <em>target</em> = 2
//<strong>输出: </strong>2 
//<strong>解释: </strong>因为一共有两个三元组满足累加和小于 2:
//&nbsp;    [-2,0,1]
//     [-2,0,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入: </strong><em>nums</em> = <span><code>[]</code></span>, <em>target</em> = 0
//<strong>输出: </strong>0 </pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入: </strong><em>nums</em> = <span><code>[0]</code></span>, <em>target</em> = 0
//<strong>输出: </strong>0 </pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>0 &lt;= n &lt;= 3500</code></li> 
// <li><code>-100 &lt;= nums[i] &lt;= 100</code></li> 
// <li><code>-100 &lt;= target &lt;= 100</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 128</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

// 259.较小的三数之和
// 开题时间：2022-10-25 12:05:08
public class ThreeSumSmaller {
  public static void main(String[] args) {
    Solution solution = new ThreeSumSmaller().new Solution();
    System.out.println(solution.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 双指针
    public int threeSumSmaller(int[] nums, int target) {
      int len = nums.length;
      if (len < 3)
        return 0;
      
      Arrays.sort(nums);
      if (nums[0] + nums[1] + nums[2] >= target)
        return 0;
      else if (nums[len - 1] + nums[len - 2] + nums[len - 3] < target)
        return len * (len - 1) * (len - 2) / 6;
      
      int cnt = 0;
      for (int i = 0; i < len - 2; i++) {
        int t = target - nums[i];
        if (nums[i + 1] + nums[i + 2] >= t)
          break;
        
        for (int l = i + 1, r = len - 1; l < r; ) {
          if (nums[l] + nums[r] < t)
            cnt += r - l++;
          else
            r--;
        }
      }
      
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}