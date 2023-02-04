// 给你一个整数数组 <code>nums</code> 。如果任一值在数组中出现 <strong>至少两次</strong> ，返回 <code>true</code> ；如果数组中每个元素互不相同，返回 <code>false</code> 。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,1]
//<strong>输出：</strong>true</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,4]
//<strong>输出：</strong>false</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1,3,3,4,3,2,4,2]
//<strong>输出：</strong>true</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>排序</li></div></div><br><div><li>👍 806</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 217.存在重复元素
// 开题时间：2022-09-04 09:22:36
public class ContainsDuplicate {
  public static void main(String[] args) {
    Solution solution = new ContainsDuplicate().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 1.hashtable   n   n
    public boolean containsDuplicate(int[] nums) {
      Set<Integer> set = new HashSet<>();
      for (int num : nums)
        if (!set.add(num))
          return true;
      return false;
    }
    
    // 2.sort    n*logn  n
    public boolean containsDuplicate2(int[] nums) {
      Arrays.parallelSort(nums);
      for (int i = 0; i < nums.length - 1; i++)
        if (nums[i] == nums[i + 1])
          return true;
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}