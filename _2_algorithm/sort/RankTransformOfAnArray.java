//<p>给你一个整数数组&nbsp;<code>arr</code> ，请你将数组中的每个元素替换为它们排序后的序号。</p>
//
//<p>序号代表了一个元素有多大。序号编号的规则如下：</p>
//
//<ul> 
// <li>序号从 1 开始编号。</li> 
// <li>一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。</li> 
// <li>每个数字的序号都应该尽可能地小。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [40,10,20,30]
//<strong>输出：</strong>[4,1,2,3]
//<strong>解释：</strong>40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [100,100,100]
//<strong>输出：</strong>[1,1,1]
//<strong>解释：</strong>所有元素有相同的序号。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr = [37,12,28,9,100,56,80,5,12]
//<strong>输出：</strong>[5,3,4,2,8,6,7,1,3]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= arr.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>9</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 124</li><li>👎 0</li></div>
package _2_algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

// 1331.数组序号转换
// 开题时间：2022-12-26 09:58:26
public class RankTransformOfAnArray {
  public static void main(String[] args) {
    Solution solution = new RankTransformOfAnArray().new Solution();
    System.out.println(Arrays.toString(solution.arrayRankTransform(new int[]{})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 二维数组（值、索引）排序
    public int[] arrayRankTransform9(int[] arr) {
      int n = arr.length;
      if (n <= 0)
        return arr;
      
      int[][] val2idx = new int[n][2];
      for (int i = 0; i < n; i++) {
        val2idx[i][0] = arr[i];
        val2idx[i][1] = i;
      }
      Arrays.sort(val2idx, Comparator.comparingInt(a -> a[0]));
      
      arr[val2idx[0][1]] = 1;
      for (int i = 1, rank = 1; i < n; i++)
        if (val2idx[i - 1][0] == val2idx[i][0])
          arr[val2idx[i][1]] = rank;
        else
          arr[val2idx[i][1]] = ++rank;
      
      return arr;
    }
    
    // TreeMap<Integer, Set<Integer>>
    public int[] arrayRankTransform8(int[] arr) {
      int n = arr.length;
      if (n <= 0)
        return arr;
      
      TreeMap<Integer, Set<Integer>> val2inidces = new TreeMap<>();
      for (int i = 0; i < n; i++) {
        if (val2inidces.containsKey(arr[i])) {
          val2inidces.get(arr[i]).add(i);
        } else {
          HashSet<Integer> set = new HashSet<>();
          set.add(i);
          val2inidces.put(arr[i], set);
        }
      }
      
      final int[] rank = {1};
      val2inidces.forEach((k, v) -> {
        v.forEach(idx -> arr[idx] = rank[0]);
        rank[0]++;
      });
      
      return arr;
    }
    
    //☆☆☆☆☆ 数组拷贝排序 + HashMap
    public int[] arrayRankTransform(int[] arr) {
      int n = arr.length;
      int[] sorted = Arrays.copyOf(arr, n);
      Arrays.sort(sorted);
      
      HashMap<Integer, Integer> val2rank = new HashMap<>();
      for (int x : sorted)
        val2rank.putIfAbsent(x, val2rank.size() + 1);
      
      for (int i = 0; i < n; i++)
        arr[i] = val2rank.get(arr[i]);
      
      return arr;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}