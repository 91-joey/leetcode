//<p>给你一个二进制数组&nbsp;<code>nums</code>&nbsp;，你需要从中删掉一个元素。</p>
//
//<p>请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。</p>
//
//<p>如果不存在这样的子数组，请返回 0 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>提示 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,0,1]
//<strong>输出：</strong>3
//<strong>解释：</strong>删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,1,1,1,0,1,1,0,1]
//<strong>输出：</strong>5
//<strong>解释：</strong>删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1]
//<strong>输出：</strong>2
//<strong>解释：</strong>你必须要删除一个元素。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>nums[i]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>滑动窗口</li></div></div><br><div><li>👍 65</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

// 1493.删掉一个元素以后全为 1 的最长子数组
// 开题时间：2022-10-09 08:58:38
public class LongestSubarrayOf1sAfterDeletingOneElement {
  public static void main(String[] args) {
    Solution solution = new LongestSubarrayOf1sAfterDeletingOneElement().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int longestSubarray(int[] nums) {
      int l = 0;
      int r = 0;
      for (int sum = 0; r < nums.length; r++) {
        sum += nums[r];
        if (sum < r - l)
          sum -= nums[l++];
      }
      return r - l - 1;
    }
    
    /*
     * 递推
     * pre[i - 1] = 「以第 i - 1 位结尾的最长连续全 1 子数组长度」
     *      nums[i] = 0 ->  pre[i] = 0
     *      nums[i] = 1 ->  pre[i] = pre[i - 1] + 1
     * suf[i + 1] = 「以第 i + 1 位开头的最长连续全 1 子数组长度」
     *      nums[i] = 0 ->  suf[i] = 0
     *      nums[i] = 1 ->  suf[i] = suf[i + 1] + 1
     * max[i] = pre[i - 1] + suf[i + 1]
     */
    public int longestSubarrayGJ1(int[] nums) {
      int len = nums.length;
      
      //「以第 i 位结尾的最长连续全 1 子数组长度」
      int[] pre = new int[len];
      pre[0] = nums[0];
      for (int i = 1; i < len - 1; i++)
        pre[i] = nums[i] == 0 ? 0 : pre[i - 1] + 1;
      
      //「以第 i 位开头的最长连续全 1 子数组长度」
      int[] suf = new int[len];
      suf[len - 1] = nums[len - 1];
      for (int i = len - 2; i > 0; i--)
        suf[i] = nums[i] == 0 ? 0 : suf[i + 1] + 1;
      
      // 计算「删掉第 i 位后连续全 1 子数组长度」的最大值
      int max = 0;
            /*
            for (int i = 0; i < len; i++)
                max = Math.max(max, (i == 0 ? 0 : pre[i - 1]) +
                                    (i == len - 1 ? 0 : suf[i + 1]));
            */
      if (len > 1) {
        max = Math.max(max, suf[1]);
        max = Math.max(max, pre[len - 2]);
      }
      for (int i = 1; i < len - 1; i++)
        max = Math.max(max, pre[i - 1] + suf[i + 1]);
      return max;
    }
    
    public int longestSubarrayGJ1Enhance(int[] nums) {
      int len = nums.length;
      
      //「以第 i 位开头的最长连续全 1 子数组长度」
      int[] suf = new int[len];
      suf[len - 1] = nums[len - 1];
      for (int i = len - 2; i > 0; i--)
        suf[i] = nums[i] == 0 ? 0 : suf[i + 1] + 1;
      
      int max = 0;
      //「以第 i 位结尾的最长连续全 1 子数组长度」
      // 计算「删掉第 i 位后连续全 1 子数组长度」的最大值
      int[] pre = new int[len];
      pre[0] = nums[0];
      for (int i = 1; i < len - 1; i++) {
        pre[i] = nums[i] == 0 ? 0 : pre[i - 1] + 1;
        max = Math.max(max, pre[i - 1] + suf[i + 1]);
      }
      
      if (len > 1) {
        max = Math.max(max, suf[1]);
        max = Math.max(max, pre[len - 2]);
      }
      return max;
    }
    
    /*
     * 递推优化
     * pre[i - 1] = 「以第 i - 1 位结尾的最长连续全 1 子数组长度」
     *      nums[i] = 0 ->  pre[i] = 0
     *      nums[i] = 1 ->  pre[i] = pre[i - 1] + 1
     * preAtMostOneZeroDeleted[i + 1] = 「以第 i + 1 位开头的最长连续全 1 子数组长度」
     *      nums[i] = 0 ->  preAtMostOneZeroDeleted[i] = pre[i - 1]
     *      nums[i] = 1 ->  preAtMostOneZeroDeleted[i] = preAtMostOneZeroDeleted[i - 1] + 1
     * max=max(preAtMostOneZeroDeleted)
     *      数组全为 1 时，需要长度再减一
     *      if max == nums.length
     *          max--
     */
    public int longestSubarrayGJ1Enhance2(int[] nums) {
      int max = 0;
      int pre = 0;
      int preWith0 = 0;
      for (int num : nums) {
        if (num == 0) {
          preWith0 = pre;
          pre = 0;
        } else {
          pre++;
          preWith0++;
        }
        max = Math.max(max, preWith0);
      }
      
      return max == nums.length ? max - 1 : max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}