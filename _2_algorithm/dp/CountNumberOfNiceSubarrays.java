//<p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数 <code>k</code>。如果某个连续子数组中恰好有 <code>k</code> 个奇数数字，我们就认为这个子数组是「<strong>优美子数组</strong>」。</p>
//
//<p>请返回这个数组中 <strong>「优美子数组」</strong> 的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,2,1,1], k = 3
//<strong>输出：</strong>2
//<strong>解释：</strong>包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,4,6], k = 1
//<strong>输出：</strong>0
//<strong>解释：</strong>数列中不包含任何奇数，所以不存在优美子数组。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//<strong>输出：</strong>16
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 50000</code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10^5</code></li> 
// <li><code>1 &lt;= k &lt;= nums.length</code></li> 
//</ul>
//
//<div><li>👍 246</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.HashMap;

// 1248.统计「优美子数组」
// 开题时间：2022-12-17 14:34:40
public class CountNumberOfNiceSubarrays {
  public static void main(String[] args) {
    Solution solution = new CountNumberOfNiceSubarrays().new Solution();
    System.out.println(solution.numberOfSubarrays(new int[]{1, 1, 1, 1, 1}, 1));
    //        System.out.println(solution.numberOfSubarrays(new int[]{2,2,2,1,2,2,1,2,2,2}, 2));
    //        System.out.println(solution.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 前缀和 + 哈希计数
    public int numberOfSubarrays9(int[] nums, int k) {
      HashMap<Integer, Integer> sum2cnt = new HashMap<>();
      int sum = 0;
      sum2cnt.put(sum, 1);
      
      int ans = 0;
      for (int x : nums) {
        sum += (x & 1);
        ans += sum2cnt.getOrDefault(sum - k, 0);
        sum2cnt.merge(sum, 1, Integer::sum);
      }
      
      return ans;
    }
    
    //☆☆☆☆☆ 前缀和 + 数组计数
    public int numberOfSubarrays8(int[] nums, int k) {
      int[] sum2cnt = new int[nums.length + 1];
      int sum = 0;
      sum2cnt[sum] = 1;
      
      int ans = 0;
      for (int x : nums) {
        sum += (x & 1);
        ans += sum >= k ? sum2cnt[sum - k] : 0;
        sum2cnt[sum]++;
      }
      
      return ans;
    }
    
    /*
     * 贡献法
     * 1.数组记录第 i 个奇数的索引，特别的：
     *      第 0 个奇数的索引为 -1
     *      最后一个奇数的索引为 len
     * 2.第 i 个奇数开始、第 i + k -1 个奇数为结尾的子数组对答案的贡献为：
     *      (odd2idx[i] - odd2idx[i - 1]) * (odd2idx[i + k] - odd2idx[i + k - 1]
     */
    public int numberOfSubarrays(int[] nums, int k) {
      int[] odd2idx = new int[nums.length + 2];
      odd2idx[0] = -1;
      int idx = 1;
      for (int i = 0; i < nums.length; i++)
        if ((nums[i] & 1) == 1)
          odd2idx[idx++] = i;
      odd2idx[idx] = nums.length;
      
      int ans = 0;
      for (int i = 1; i <= idx - k; i++)
        ans += (odd2idx[i] - odd2idx[i - 1]) * (odd2idx[i + k] - odd2idx[i + k - 1]);
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}