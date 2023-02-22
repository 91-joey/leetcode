//<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，请你将数组按照每个值的频率 <strong>升序</strong> 排序。如果有多个值的频率相同，请你按照数值本身将它们 <strong>降序</strong> 排序。&nbsp;</p>
//
//<p>请你返回排序后的数组。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums = [1,1,2,2,2,3]
//<b>输出：</b>[3,1,1,2,2,2]
//<b>解释：</b>'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums = [2,3,1,3,2]
//<b>输出：</b>[1,3,3,2,2]
//<b>解释：</b>'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>nums = [-1,1,-6,4,5,-6,1,4,1]
//<b>输出：</b>[5,-1,4,4,-6,-6,1,1,1]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
// <li><code>-100 &lt;= nums[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 145</li><li>👎 0</li></div>
package _2_algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

// 1636.按照频率将数组升序排序
// 开题时间：2022-12-22 18:34:12
public class SortArrayByIncreasingFrequency {
  public static void main(String[] args) {
    Solution solution = new SortArrayByIncreasingFrequency().new Solution();
    System.out.println(Arrays.toString(solution.frequencySort(new int[]{2, 3, 1, 3, 2})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希计数 + 自定义排序
    public int[] frequencySort9(int[] nums) {
      HashMap<Integer, Integer> val2cnt = new HashMap<>();
      for (int x : nums)
        val2cnt.merge(x, 1, Integer::sum);
      
      ArrayList<int[]> list = new ArrayList<>();
      val2cnt.forEach((k, v) -> list.add(new int[]{k, v}));
      list.sort(Comparator.<int[]>comparingInt(arr -> arr[1])
          .thenComparing(Comparator.<int[]>comparingInt(arr -> arr[0]).reversed()));
      
      int i = 0;
      for (int[] arr : list)
        for (int j = 0; j < arr[1]; j++)
          nums[i++] = arr[0];
      
      return nums;
    }
    
    public int[] frequencySort(int[] nums) {
      HashMap<Integer, Integer> val2cnt = new HashMap<>();
      for (int x : nums)
        val2cnt.merge(x, 1, Integer::sum);
      
      return Arrays.stream(nums)
          .boxed()
          .sorted(Comparator.<Integer>comparingInt(val2cnt::get)
              .thenComparing(Comparator.reverseOrder()))
          .mapToInt(Integer::intValue)
          .toArray();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}