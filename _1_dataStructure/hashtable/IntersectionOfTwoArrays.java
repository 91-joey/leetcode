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
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 611</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// 349.两个数组的交集
// 开题时间：2022-09-04 11:33:01
public class IntersectionOfTwoArrays {
  public static void main(String[] args) {
    Solution solution = new IntersectionOfTwoArrays().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // hashset m+n   m+n
    public int[] intersection(int[] nums1, int[] nums2) {
      // transform both to hashsets
      Set<Integer> set1 = new HashSet<>();
      Set<Integer> set2 = new HashSet<>();
      for (int i : nums1)
        set1.add(i);
      for (int i : nums2)
        set2.add(i);
      
      return set1.size() < set2.size() ? getInts(set1, set2) : getInts(set2, set1);
    }
    
    private int[] getInts(Set<Integer> set1, Set<Integer> set2) {
      // remove duplicates from set1 by set2
      set1.removeIf(e -> !set2.contains(e));
      
      // return array
      int[] ans = new int[set1.size()];
      int idx = 0;
      for (Integer e : set1) {
        ans[idx++] = e;
      }
      return ans;
    }
    
    //☆☆☆☆☆ 哈希    m+n m+min(m,n)
    public int[] intersection3(int[] nums1, int[] nums2) {
      HashSet<Integer> set = new HashSet<>();
      for (int e : nums1)
        set.add(e);
      
      HashSet<Integer> ans = new HashSet<>();
      for (int e : nums2)
        if (set.contains(e))
          ans.add(e);
      
      return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] intersection2(int[] nums1, int[] nums2) {
      Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
      return Arrays.stream(nums2).filter(set::contains).distinct().toArray();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}