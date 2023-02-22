//<p>给你一个正整数数组 <code>nums</code> ，请你从中删除一个含有 <strong>若干不同元素</strong> 的子数组<strong>。</strong>删除子数组的 <strong>得分</strong> 就是子数组各元素之 <strong>和</strong> 。</p>
//
//<p>返回 <strong>只删除一个</strong> 子数组可获得的 <strong>最大得分</strong><em> 。</em></p>
//
//<p>如果数组 <code>b</code> 是数组 <code>a</code> 的一个连续子序列，即如果它等于 <code>a[l],a[l+1],...,a[r]</code> ，那么它就是&nbsp;<code>a</code> 的一个子数组。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [4,2,4,5,6]
//<strong>输出：</strong>17
//<strong>解释：</strong>最优子数组是 [2,4,5,6]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [5,2,1,2,5,2,1,2,5]
//<strong>输出：</strong>8
//<strong>解释：</strong>最优子数组是 [5,2,1] 或 [1,2,5]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>滑动窗口</li></div></div><br><div><li>👍 57</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 1695.删除子数组的最大得分
// 开题时间：2022-10-06 17:46:15
public class MaximumErasureValue {
  public static void main(String[] args) {
    Solution solution = new MaximumErasureValue().new Solution();
    System.out.println(solution.maximumUniqueSubarray(new int[]{4, 2, 4, 5, 6}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 1.r++ 直到出现第一个重复元素 a，计算最大得分
     * 2.l++ 直到元素 a
     * 3.重复步骤1
     */
    public int maximumUniqueSubarray(int[] nums) {
      int max = nums[0];
      int sum = 0;
      boolean[] exists = new boolean[10001];
      
      for (int l = 0, r = 0; r < nums.length; r++) {
        if (exists[nums[r]]) {
          max = Math.max(max, sum);
          while (nums[r] != nums[l]) {
            sum -= nums[l];
            exists[nums[l++]] = false;
          }
          l++;
        } else {
          sum += nums[r];
          exists[nums[r]] = true;
        }
      }
      
      max = Math.max(max, sum);
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}