//<p>给你四个整数数组 <code>nums1</code>、<code>nums2</code>、<code>nums3</code> 和 <code>nums4</code> ，数组长度都是 <code>n</code> ，请你计算有多少个元组 <code>(i, j, k, l)</code> 能满足：</p>
//
//<ul> 
// <li><code>0 &lt;= i, j, k, l &lt; n</code></li> 
// <li><code>nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 两个元组如下：
// 1. (0, 0, 0, 1) -&gt; nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
// 2. (1, 1, 0, 0) -&gt; nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p>&nbsp; <strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums1.length</code></li> 
// <li><code>n == nums2.length</code></li> 
// <li><code>n == nums3.length</code></li> 
// <li><code>n == nums4.length</code></li> 
// <li><code>1 &lt;= n &lt;= 200</code></li> 
// <li><code>-2<sup>28</sup> &lt;= nums1[i], nums2[i], nums3[i], nums4[i] &lt;= 2<sup>28</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 651</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.HashMap;
import java.util.Map;

// 454.四数相加 II
// 开题时间：2022-09-10 12:14:43
public class FourSumIi {
  public static void main(String[] args) {
    Solution solution = new FourSumIi().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力迭代  n^4 1
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
      int cnt = 0;
      
      for (int v1 : nums1)
        for (int v2 : nums2)
          for (int v3 : nums3)
            for (int v4 : nums4)
              if (v1 + v2 + v3 + v4 == 0)
                cnt++;
      
      return cnt;
    }
    
    // hashmap   n^2 n^2
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
      int cnt = 0;
      
      Map<Integer, Integer> map = new HashMap<>();
      for (int v1 : nums1)
        for (int v2 : nums2)
          map.merge(v1 + v2, 1, Integer::sum);
      
      //！！！此处存储在hashmap中是多余的，直接遍历即可。
      Map<Integer, Integer> map2 = new HashMap<>();
      for (int v3 : nums3)
        for (int v4 : nums4)
          map2.merge(v3 + v4, 1, Integer::sum);
      
      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        Integer value = map2.get(-entry.getKey());
        if (value != null)
          cnt += entry.getValue() * value;
      }
      
      return cnt;
    }
    
    // hashmap   n^2 n^2
    public int fourSumCount3(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
      int cnt = 0;
      
      Map<Integer, Integer> map = new HashMap<>();
      for (int v1 : nums1)
        for (int v2 : nums2)
          map.merge(v1 + v2, 1, Integer::sum);
      
      for (int v3 : nums3)
        for (int v4 : nums4)
          cnt += map.getOrDefault(-v3 - v4, 0);
      
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}