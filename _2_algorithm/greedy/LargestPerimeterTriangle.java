//<p>给定由一些正数（代表长度）组成的数组 <code>nums</code>&nbsp;，返回 <em>由其中三个长度组成的、<strong>面积不为零</strong>的三角形的最大周长</em>&nbsp;。如果不能形成任何面积不为零的三角形，返回&nbsp;<code>0</code>。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,1,2]
//<strong>输出：</strong>5
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,1]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><li>👍 220</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

import java.util.Arrays;

// 976.三角形的最大周长
// 开题时间：2022-12-03 11:25:33
public class LargestPerimeterTriangle {
  public static void main(String[] args) {
    Solution solution = new LargestPerimeterTriangle().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int largestPerimeter9(int[] nums) {
      Arrays.sort(nums);
      
      int max = 0;
      int n = nums.length;
      for (int i = 0; i < n - 2; i++) {
        for (int j = i + 1; j < n - 1; j++) {
          int sum = nums[i] + nums[j];
          int k = lstLessThan(nums, j, n - 1, sum);
          if (k != j)
            max = Math.max(max, sum + nums[k]);
        }
      }
      return max;
    }
    
    public static int lstLessThan(int[] arr, int l, int r, int target) {
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if (target <= arr[mid])
          r = mid - 1;
        else
          l = mid;
      }
      return r;
    }
    
    /*
     * 排序+贪心：
     *   设 a<=b<=c，倒序枚举 c，第一个满足nums[i - 2] + nums[i - 1] > nums[i]的，即为最大值
     */
    public int largestPerimeter(int[] nums) {
      Arrays.sort(nums);
      
      for (int i = nums.length - 1; i >= 2; i--)
        if (nums[i - 2] + nums[i - 1] > nums[i])
          return nums[i - 2] + nums[i - 1] + nums[i];
      
      return 0;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}