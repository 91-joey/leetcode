//<p>给两个整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，返回 <em>两个数组中 <strong>公共的</strong> 、长度最长的子数组的长度&nbsp;</em>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
//<strong>输出：</strong>3
//<strong>解释：</strong>长度最长的公共子数组是 [3,2,1] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
//<strong>输出：</strong>5
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 829</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 718.最长重复子数组
// 开题时间：2022-12-12 09:13:25
public class MaximumLengthOfRepeatedSubarray {
  public static void main(String[] args) {
    Solution solution = new MaximumLengthOfRepeatedSubarray().new Solution();
    System.out.println(solution.findLength(new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 0, 1, 1}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findLength9(int[] nums1, int[] nums2) {
      HashMap<Integer, Set<Integer>> val2indices = new HashMap<>();
      int m = nums1.length;
      int n = nums2.length;
      for (int i = 0; i < n; i++) {
        int finalI = i;
        if (!val2indices.containsKey(nums2[i]))
          val2indices.put(nums2[i], new HashSet<>() {{
            add(finalI);
          }});
        else
          val2indices.get(nums2[i]).add(i);
      }
      
      int max = 0;
      for (int i = 0; i < m; i++) {
        Set<Integer> set = val2indices.get(nums1[i]);
        if (set == null)
          continue;
        
        for (int j : set) {
          int len = 0;
          for (int i2 = i; i2 < m && j < n && nums1[i2] == nums2[j]; i2++, j++)
            len++;
          max = Math.max(max, len);
        }
      }
      
      return max;
    }
    
    public int findLength8(int[] nums1, int[] nums2) {
      int m = nums1.length + 1;
      int n = nums2.length + 1;
      
      int[][] f = new int[m][n];
      int max = 0;
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          if (nums1[i - 1] == nums2[j - 1]) {
            f[i][j] = f[i - 1][j - 1] + 1;
            max = Math.max(max, f[i][j]);
          }
      return max;
    }
    
    public int findLength(int[] nums1, int[] nums2) {
      int m = nums1.length + 1;
      int n = nums2.length + 1;
      
      int[] f = new int[n];
      int max = 0;
      for (int i = 1; i < m; i++)
        for (int j = n - 1; j >= 1; j--)
          if (nums1[i - 1] == nums2[j - 1]) {
            f[j] = f[j - 1] + 1;
            max = Math.max(max, f[j]);
          } else
            f[j] = 0;
      return max;
    }
    
    // todo 滑窗
    // todo 二分
  }
  // leetcode submit region end(Prohibit modification and deletion)
}