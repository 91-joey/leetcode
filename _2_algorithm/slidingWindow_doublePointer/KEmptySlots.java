//<p><code>n</code>&nbsp;个灯泡排成一行，编号从 <code>1</code> 到
// <meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;。最初，所有灯泡都关闭。每天&nbsp;<strong>只打开一个</strong>&nbsp;灯泡，直到
// <meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;天后所有灯泡都打开。</p>
//
//<p>给你一个长度为
// <meta charset="UTF-8" />&nbsp;<code>n</code>&nbsp;的灯泡数组 <code>blubs</code> ，其中 <code>bulls[i] = x</code> 意味着在第 <code>(i+1)</code> 天，我们会把在位置 <code>x</code> 的灯泡打开，其中 <code>i</code> <strong>从 0 开始</strong>，<code>x</code> <strong>从 1 开始</strong>。</p>
//
//<p>给你一个整数
// <meta charset="UTF-8" />&nbsp;<code>k</code>&nbsp;，请返回<em>恰好有两个打开的灯泡，且它们中间 <strong>正好</strong> 有
//  <meta charset="UTF-8" />&nbsp;<code>k</code>&nbsp;个&nbsp;<strong>全部关闭的</strong> 灯泡的 <strong>最小的天数</strong> </em>。<em>如果不存在这种情况，返回 <code>-1</code> 。</em></p>
//
//<p>&nbsp;</p>
//
//<p><b>示例 1：</b></p>
//
//<pre>
//<b>输入：</b>
// bulbs = [1,3,2]，k = 1
//<b>输出：</b>2
//<b>解释：</b>
// 第一天 bulbs[0] = 1，打开第一个灯泡 [1,0,0]
// 第二天 bulbs[1] = 3，打开第三个灯泡 [1,0,1]
// 第三天 bulbs[2] = 2，打开第二个灯泡 [1,1,1]
// 返回2，因为在第二天，两个打开的灯泡之间恰好有一个关闭的灯泡。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>bulbs = [1,2,3]，k = 1
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><b>提示：</b></p>
//
//<ul> 
// <li><code>n == bulbs.length</code></li> 
// <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= bulbs[i] &lt;= n</code></li> 
// <li><code>bulbs</code> 是一个由从 <code>1</code> 到 <code>n</code> 的数字构成的排列</li> 
// <li><code>0 &lt;= k &lt;= 2 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树状数组</li><li>数组</li><li>有序集合</li><li>滑动窗口</li></div></div><br><div><li>👍 75</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.TreeSet;

// 683.K 个关闭的灯泡
// 开题时间：2022-10-18 12:00:50
public class KEmptySlots {
  public static void main(String[] args) {
    Solution solution = new KEmptySlots().new Solution();
    System.out.println(solution.kEmptySlots2(new int[]{1, 2, 3}, 1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * {1,100,30,31,60,59,57}    2
     *
     */
    // 有序集合
    public int kEmptySlots(int[] bulbs, int k) {
      int len = bulbs.length;
      if (len - 2 < k)
        return -1;
      
      TreeSet<Integer> set = new TreeSet<>();
      for (int i = 0, t = k + 1; i < bulbs.length; i++) {
        Integer neighbor;
        int e = bulbs[i];
        if (((neighbor = set.floor(e)) != null && t == e - neighbor) ||
            ((neighbor = set.ceiling(e)) != null && t == neighbor - e))
          return i + 1;
        set.add(e);
      }
      
      return -1;
    }
    
    // 桶（二维数组）
    public int kEmptySlots2(int[] bulbs, int k) {
      int len = bulbs.length;
      if (len - 2 < k)
        return -1;
      
      int size = k + 1;
      int[][] buckets = new int[len / size + 1][2];
      for (int i = 0; i < bulbs.length; i++) {
        int e = bulbs[i];
        int id = e / size;
        
        if (buckets[id][0] == 0) {
          buckets[id][0] = buckets[id][1] = e;
          if (isKWithPrev2(id, buckets, size, e) ||
              isKWithNext2(id, buckets, size, e))
            return i + 1;
        } else {
          if (e < buckets[id][0]) {
            buckets[id][0] = e;
            if (isKWithPrev2(id, buckets, size, e))
              return i + 1;
          } else if (e > buckets[id][1]) {
            buckets[id][1] = e;
            if (isKWithNext2(id, buckets, size, e))
              return i + 1;
          }
        }
      }
      
      return -1;
    }
    
    private boolean isKWithPrev2(int id, int[][] buckets, int t, int e) {
      return id > 0 && buckets[id - 1][1] != 0 && t == e - buckets[id - 1][1];
    }
    
    private boolean isKWithNext2(int id, int[][] buckets, int t, int e) {
      return id < buckets.length - 1 && buckets[id + 1][0] != 0 && t == buckets[id + 1][0] - e;
    }
    
    
    /*
     * 基于桶排序的方法：
     * 以数组元素值（灯泡位置）建桶，桶的大小为 k+1，桶中的元素间间隔范围如下：
     *   桶相同：[0,k-1]
     *   桶相邻：[0,2k]
     *   桶相隔：[k+1, +inf) 题目要找到的元素间间隔为 k，故查找范围为「相邻桶」。
     * > 题目说：中间 正好 有 k 个 全部关闭的 灯泡，也就是中间不能有「打开的灯泡」。 所以，当前桶中元素的查找对象为：
     *   前一个桶的最大值
     *   后一个桶的最小值
     */
    // ☆☆☆☆☆ 桶（2个一维数组）
    public int kEmptySlots3(int[] bulbs, int k) {
      int len = bulbs.length;
      if (len - 2 < k)
        return -1;
      
      int[] bucketsMin = new int[len / ++k + 1];
      int[] bucketsMax = new int[bucketsMin.length];
      
      for (int i = 0; i < bulbs.length; ) {
        int e = bulbs[i++];
        int id = e / k;
        
        if (bucketsMin[id] == 0) {
          bucketsMin[id] = bucketsMax[id] = e;
          if (isKWithPrev(id, bucketsMax, k, e) ||
              isKWithNext(id, bucketsMin, k, e))
            return i;
        } else {
          if (e < bucketsMin[id]) {
            bucketsMin[id] = e;
            if (isKWithPrev(id, bucketsMax, k, e))
              return i;
          } else if (e > bucketsMax[id]) {
            bucketsMax[id] = e;
            if (isKWithNext(id, bucketsMin, k, e))
              return i;
          }
        }
      }
      
      return -1;
    }
    
    
    private boolean isKWithPrev(int id, int[] buckets, int t, int e) {
      return id > 0 && buckets[id - 1] != 0 && t == e - buckets[id - 1];
    }
    
    private boolean isKWithNext(int id, int[] buckets, int t, int e) {
      return id < buckets.length - 1 && buckets[id + 1] != 0 && t == buckets[id + 1] - e;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}