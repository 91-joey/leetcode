//<p>给定一个未经排序的整数数组，找到最长且<strong> 连续递增的子序列</strong>，并返回该序列的长度。</p>
//
//<p><strong>连续递增的子序列</strong> 可以由两个下标 <code>l</code> 和 <code>r</code>（<code>l &lt; r</code>）确定，如果对于每个 <code>l &lt;= i &lt; r</code>，都有 <code>nums[i] &lt; nums[i + 1]</code> ，那么子序列 <code>[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]]</code> 就是连续递增子序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,3,5,4,7]
//<strong>输出：</strong>3
//<strong>解释：</strong>最长连续递增序列是 [1,3,5], 长度为3。
// 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,2,2,2]
//<strong>输出：</strong>1
//<strong>解释：</strong>最长连续递增序列是 [2], 长度为1。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li></div></div><br><div><li>👍 330</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 674.最长连续递增序列
// 开题时间：2022-10-01 11:39:08
public class LongestContinuousIncreasingSubsequence {
  public static void main(String[] args) {
    Solution solution = new LongestContinuousIncreasingSubsequence().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findLengthOfLCIS(int[] nums) {
      //☆☆☆☆☆ all in [l,r) are incremental
      int max = 1;
      int length = nums.length;
      int l = 0;
      for (int r = 1; r < length && (length - l) > max; r++) {
        if (nums[r - 1] >= nums[r]) {
          max = Math.max(max, r - l);
          l = r;
        }
      }
      max = Math.max(max, length - l);
      return max;
    }
    
    public int findLengthOfLCIS2(int[] nums) {
      // all in [l,r] are incremental
      int max = 1;
      int length = nums.length;
      int l = 0;
      for (int r = 0; r < length - 1 && (length - l) > max; r++) {
        if (nums[r] >= nums[r + 1]) {
          max = Math.max(max, r - l + 1);
          l = r + 1;
        }
      }
      max = Math.max(max, length - l);
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}