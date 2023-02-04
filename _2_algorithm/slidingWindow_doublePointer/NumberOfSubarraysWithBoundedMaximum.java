//<p>给你一个整数数组 <code>nums</code> 和两个整数：<code>left</code> 及 <code>right</code> 。找出 <code>nums</code> 中连续、非空且其中最大元素在范围&nbsp;<code>[left, right]</code> 内的子数组，并返回满足条件的子数组的个数。</p>
//
//<p>生成的测试用例保证结果符合 <strong>32-bit</strong> 整数范围。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,1,4,3], left = 2, right = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>满足条件的三个子数组：[2], [2, 1], [3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,9,2,5,6], left = 2, right = 8
//<strong>输出：</strong>7
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>0 &lt;= left &lt;= right &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 195</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 795.区间子数组个数
// 开题时间：2022-10-13 11:56:49
public class NumberOfSubarraysWithBoundedMaximum {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubarraysWithBoundedMaximum().new Solution();
    //        System.out.println(solution.numSubarrayBoundedMax(new int[]{1, 2, 5, 3, 4, 5, 0, 6, 7, 8, 9, 10}, 4, 7));
    //        System.out.println(solution.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
    System.out.println(solution.numSubarrayBoundedMax(new int[]{876, 880, 482, 260, 132, 421, 732, 703, 795, 420, 871, 445, 400, 291, 358, 589, 617, 202, 755, 810, 227, 813, 549, 791, 418, 528, 835, 401, 526, 584, 873, 662, 13, 314, 988, 101, 299, 816, 833, 224, 160, 852, 179, 769, 646, 558, 661, 808, 651, 982, 878, 918, 406, 551, 467, 87, 139, 387, 16, 531, 307, 389, 939, 551, 613, 36, 528, 460, 404, 314, 66, 111, 458, 531, 944, 461, 951, 419, 82, 896, 467, 353, 704, 905, 705, 760, 61, 422, 395, 298, 127, 516, 153, 299, 801, 341, 668, 598, 98, 241},
        658, 719));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * all in [l,r) <= right
     * x<left           cnt+=pre
     * left<=x<=right   cnt+=r-l
     * right<x          cnt+=0
     */
    public int numSubarrayBoundedMax9(int[] nums, int left, int right) {
      int cnt = 0;
      for (int l = 0, r = 0, tmp = 0; r < nums.length; ) {
        int num = nums[r++];
        if (left <= num && num <= right) {
          tmp = r - l;
          cnt += tmp;
        } else if (num < left) {
          cnt += tmp;
        } else {
          tmp = 0;
          l = r;
        }
      }
      return cnt;
    }
    
    public int numSubarrayBoundedMaxZJ_enhance(int[] nums, int left, int right) {
      int cnt = 0;
      for (int l = 0, r = 0, tmp = 0; r < nums.length; ) {
        int num = nums[r++];
        if (right < num)
          l = r;
        if (left <= num)
          tmp = r - l;
        cnt += tmp;
      }
      return cnt;
    }
    
    /*
     * 官解（计数）
     *      设 -1 = all in ( ,left)
     *      设 0  = all in [left,right]
     *      设 1  = all in (right, )
     *  则答案为 cnt(只包含 0/1 的子数组)-cnt(只包含 0 的子数组)
     */
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
      return cnt(nums, right + 1) - cnt(nums, left);
    }
    
    /**
     * @param bound exclusive
     */
    private int cnt(int[] nums, int bound) {
      int cnt = 0;
      int cur = 0;
      for (int num : nums) {
        if (num < bound) {
          cur++;
          cnt += cur;
        } else
          cur = 0;
      }
      return cnt;
    }
    
    // 官解优化
    public int numSubarrayBoundedMax3(int[] nums, int left, int right) {
      int cnt = 0;
      int curL = 0;
      int curR = 0;
      for (int num : nums) {
        if (num <= right) {
          curR++;
          cnt += curR;
          if (num < left) {
            curL++;
            cnt -= curL;
          } else {
            curL = 0;
          }
        } else {
          curL = 0;
          curR = 0;
        }
      }
      return cnt;
    }
    
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
      return numSubarrayBoundedMax(nums, left) - numSubarrayBoundedMax(nums, right + 1);
    }
    
    private int numSubarrayBoundedMax(int[] nums, int min) {
      int n = nums.length;
      int cnt = 0;
      for (int i = 0, cur = 0; i < n; i++) {
        if (nums[i] >= min)
          cur = i + 1;
        cnt += cur;
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}