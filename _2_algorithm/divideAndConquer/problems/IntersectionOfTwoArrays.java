//<p>给定两个数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code> ，返回 <em>它们的交集</em>&nbsp;。输出结果中的每个元素一定是 <strong>唯一</strong> 的。我们可以 <strong>不考虑输出结果的顺序</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
//<strong>输出：</strong>[2]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//<strong>输出：</strong>[9,4]
//<strong>解释：</strong>[4,9] 也是可通过的
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 662</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

import java.util.Arrays;
import java.util.HashSet;

// 349.两个数组的交集
// 开题时间：2022-11-09 18:18:01
public class IntersectionOfTwoArrays {
  public static void main(String[] args) {
    Solution solution = new IntersectionOfTwoArrays().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
      if (nums1.length < nums2.length)
        return intersection(nums2, nums1);
      
      HashSet<Integer> set = new HashSet<>();
      for (int e : nums1)
        set.add(e);
      
      return Arrays.stream(nums2)
          .distinct()
          .filter(set::contains)
          .toArray();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}