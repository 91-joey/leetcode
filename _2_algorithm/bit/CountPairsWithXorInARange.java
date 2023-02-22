//<p>给你一个整数数组 <code>nums</code> （下标 <strong>从 0 开始</strong> 计数）以及两个整数：<code>low</code> 和 <code>high</code> ，请返回 <strong>漂亮数对</strong> 的数目。</p>
//
//<p><strong>漂亮数对</strong> 是一个形如 <code>(i, j)</code> 的数对，其中 <code>0 &lt;= i &lt; j &lt; nums.length</code> 且 <code>low &lt;= (nums[i] XOR nums[j]) &lt;= high</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>nums = [1,4,2,7], low = 2, high = 6
//<strong>输出：</strong>6
//<strong>解释：</strong>所有漂亮数对 (i, j) 列出如下：
//    - (0, 1): nums[0] XOR nums[1] = 5 
//    - (0, 2): nums[0] XOR nums[2] = 3
//    - (0, 3): nums[0] XOR nums[3] = 6
//    - (1, 2): nums[1] XOR nums[2] = 6
//    - (1, 3): nums[1] XOR nums[3] = 3
//    - (2, 3): nums[2] XOR nums[3] = 5
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>nums = [9,8,4,2,1], low = 5, high = 14
//<strong>输出：</strong>8
//<strong>解释：</strong>所有漂亮数对 (i, j) 列出如下：
//​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
//&nbsp;   - (0, 3): nums[0] XOR nums[3] = 11
//&nbsp;   - (0, 4): nums[0] XOR nums[4] = 8
//&nbsp;   - (1, 2): nums[1] XOR nums[2] = 12
//&nbsp;   - (1, 3): nums[1] XOR nums[3] = 10
//&nbsp;   - (1, 4): nums[1] XOR nums[4] = 9
//&nbsp;   - (2, 3): nums[2] XOR nums[3] = 6
//&nbsp;   - (2, 4): nums[2] XOR nums[4] = 5</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= low &lt;= high &lt;= 2 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 113</li><li>👎 0</li></div>
package _2_algorithm.bit;

import java.util.HashMap;

// 1803.统计异或值在范围内的数对有多少
// 开题时间：2023-01-05 14:38:09
public class CountPairsWithXorInARange {
  public static void main(String[] args) {
    Solution solution = new CountPairsWithXorInARange().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int countPairsTLE(int[] nums, int low, int high) {
      int ans = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          int xor = nums[i] ^ nums[j];
          if (low <= xor && xor <= high)
            ans++;
        }
      }
      return ans;
    }
    
    //☆☆☆☆☆ 位运算 + 哈希表
    public int countPairs(int[] nums, int low, int high) {
      int ans = 0;
      var cnt = new HashMap<Integer, Integer>();
      for (int x : nums)
        cnt.merge(x, 1, Integer::sum);
      for (++high; high > 0; high >>= 1, low >>= 1) {
        var nxt = new HashMap<Integer, Integer>();
        for (var e : cnt.entrySet()) {
          int x = e.getKey(), c = e.getValue();
          ans += c * (high % 2 * cnt.getOrDefault((high - 1) ^ x, 0) -
              low % 2 * cnt.getOrDefault((low - 1) ^ x, 0));
          nxt.put(x >> 1, nxt.getOrDefault(x >> 1, 0) + c);
        }
        cnt = nxt;
      }
      return ans / 2;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}