//<p>给你一个整数数组 <code>arr</code> 。</p>
//
//<p>现需要从数组中取三个下标 <code>i</code>、<code>j</code> 和 <code>k</code> ，其中 <code>(0 &lt;= i &lt; j &lt;= k &lt; arr.length)</code> 。</p>
//
//<p><code>a</code> 和 <code>b</code> 定义如下：</p>
//
//<ul> 
// <li><code>a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]</code></li> 
// <li><code>b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]</code></li> 
//</ul>
//
//<p>注意：<strong>^</strong> 表示 <strong>按位异或</strong> 操作。</p>
//
//<p>请返回能够令 <code>a == b</code> 成立的三元组 (<code>i</code>, <code>j</code> , <code>k</code>) 的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [2,3,1,6,7]
//<strong>输出：</strong>4
//<strong>解释：</strong>满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [1,1,1,1,1]
//<strong>输出：</strong>10
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr = [2,3]
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>arr = [1,3,5,7,9]
//<strong>输出：</strong>3
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre><strong>输入：</strong>arr = [7,11,12,9,5,2,7,17,22]
//<strong>输出：</strong>8
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 300</code></li> 
// <li><code>1 &lt;= arr[i] &lt;= 10^8</code></li> 
//</ul>
//
//<div><li>👍 214</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.HashMap;

// 1442.形成两个异或相等数组的三元组数目
// 开题时间：2022-12-19 20:44:42
public class CountTripletsThatCanFormTwoArraysOfEqualXor {
  public static void main(String[] args) {
    Solution solution = new CountTripletsThatCanFormTwoArraysOfEqualXor().new Solution();
    System.out.println(solution.countTriplets(new int[]{2, 3, 1, 6, 7}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 双重循环
    public int countTriplets9(int[] arr) {
      int ans = 0;
      int n = arr.length;
      for (int i = 0; i < n - 1; i++) {
        int xors = arr[i];
        for (int j = i + 1; j < n; j++) {
          xors ^= arr[j];
          if (xors == 0)
            ans += j - i;
        }
      }
      return ans;
    }
    
    // 双哈希表（一重循环）
    public int countTriplets8(int[] arr) {
      int ans = 0;
      HashMap<Integer, Integer> sum2cnt = new HashMap<>();
      sum2cnt.put(0, 1);
      HashMap<Integer, Integer> sum2indices = new HashMap<>();
      sum2indices.put(0, -1);
      for (int i = 0, sum = 0; i < arr.length; i++) {
        sum ^= arr[i];
        ans += (i - 1) * sum2cnt.getOrDefault(sum, 0) - sum2indices.getOrDefault(sum, 0);
        sum2cnt.merge(sum, 1, Integer::sum);
        sum2indices.merge(sum, i, Integer::sum);
      }
      return ans;
    }
    
    //☆☆☆☆☆ 单哈希表（一重循环） + 高位存值
    public int countTriplets(int[] arr) {
      int ans = 0;
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1 << 20);
      for (int i = 1, sum = 0, mask = (1 << 20) - 1; i < arr.length + 1; i++) {
        sum ^= arr[i - 1];
        
        int num = map.getOrDefault(sum, 0);
        int indices = num & mask;
        int cnt = num >> 20;
        
        ans += (i - 1) * cnt - indices;
        map.put(sum, (indices + i) | ((cnt + 1) << 20));
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}