//<p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [0,1]
//<strong>输出:</strong> 2
//<strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [0,1,0]
//<strong>输出:</strong> 2
//<strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li> 
//</ul>
//
//<div><li>👍 604</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.HashMap;

// 525.连续数组
// 开题时间：2022-12-16 17:42:28
public class ContiguousArray {
  public static void main(String[] args) {
    Solution solution = new ContiguousArray().new Solution();
    //        System.out.println(solution.findMaxLength(new int[]{0, 1}));
    System.out.println(solution.findMaxLength(new int[]{0, 1, 0, 1}));
    //        System.out.println(solution.findMaxLength(new int[]{0, 1, 0}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 设 0 的权值为 -1 ，1 的权值为 +1，则有相同数量的 0 和 1 的连续子数组元素和为 0。
     * 我们一边遍历一边维护哈希映射（元素和 → 索引）
     */
    public int findMaxLength9(int[] nums) {
      HashMap<Integer, Integer> sum2idx = new HashMap<>();
      sum2idx.put(0, -1);
      
      int max = 0;
      for (int i = 0, sum = 0; i < nums.length; i++) {
        sum += nums[i] == 0 ? -1 : 1;
        Integer left = sum2idx.get(sum);
        if (left != null)
          max = Math.max(max, i - left);
        sum2idx.putIfAbsent(sum, i);
      }
      
      return max;
    }
    
    //☆☆☆☆☆ 小优化
    public int findMaxLength(int[] nums) {
      HashMap<Integer, Integer> sum2idx = new HashMap<>();
      sum2idx.put(0, -1);
      
      int max = 0;
      for (int i = 0, sum = 0; i < nums.length; i++) {
        sum += (nums[i] << 1) - 1;
        Integer left = sum2idx.get(sum);
        if (left != null)
          max = Math.max(max, i - left);
        else
          sum2idx.put(sum, i);
      }
      
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}