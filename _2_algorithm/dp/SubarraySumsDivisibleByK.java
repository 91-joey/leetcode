//<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数 <code>k</code> ，返回其中元素之和可被 <code>k</code>&nbsp;整除的（连续、非空） <strong>子数组</strong> 的数目。</p>
//
//<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [4,5,0,-2,-3,1], k = 5
//<strong>输出：</strong>7
//<strong>解释：
//</strong>有 7 个子数组满足其元素之和可被 k = 5 整除：
//[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [5], k = 9
//<strong>输出:</strong> 0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
// <li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<div><li>👍 404</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.HashMap;

// 974.和可被 K 整除的子数组
// 开题时间：2022-12-17 17:15:17
public class SubarraySumsDivisibleByK {
  public static void main(String[] args) {
    Solution solution = new SubarraySumsDivisibleByK().new Solution();
    System.out.println(solution.subarraysDivByK(new int[]{-1, 2, 9}, 2));
    System.out.println(-13 % 5);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // ZJ
    public int subarraysDivByK9(int[] nums, int k) {
      HashMap<Integer, Integer> mod2cnt = new HashMap<>();
      int sum = 0;
      mod2cnt.put(0, 1);
      
      int ans = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        int key = sum % k;
        ans += mod2cnt.getOrDefault(key, 0) +
            mod2cnt.getOrDefault(key + k, 0) +
            mod2cnt.getOrDefault(key - k, 0);
        mod2cnt.merge(key, 1, Integer::sum);
      }
      
      return ans;
    }
    
    //（同余原理）前缀和 + 哈希计数 + 逐一统计，注意取模为负数时需要转正处理
    public int subarraysDivByK8(int[] nums, int k) {
      HashMap<Integer, Integer> mod2cnt = new HashMap<>();
      int sum = 0;
      mod2cnt.put(0, 1);
      
      int ans = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        int key = (sum % k + k) % k;
        ans += mod2cnt.getOrDefault(key, 0);
        mod2cnt.merge(key, 1, Integer::sum);
      }
      
      return ans;
    }
    
    //☆☆☆☆☆（同余原理）前缀和 + 哈希计数 + 单次统计，注意取模为负数时需要转正处理
    public int subarraysDivByK(int[] nums, int k) {
      HashMap<Integer, Integer> mod2cnt = new HashMap<>();
      int sum = 0;
      mod2cnt.put(0, 1);
      
      int ans = 0;
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
        mod2cnt.merge(Math.floorMod(sum, k), 1, Integer::sum);
      }
      
      for (int cnt : mod2cnt.values())
        ans += cnt * (cnt - 1) / 2;
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}