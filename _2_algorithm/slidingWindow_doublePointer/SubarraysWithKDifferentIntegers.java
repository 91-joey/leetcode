//<p>给定一个正整数数组 <code>nums</code>和一个整数 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">k</span></span></font></font>&nbsp;，返回 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">num</span></span></font></font>&nbsp;中 「<strong>好子数组」</strong><em>&nbsp;</em>的数目。</p>
//
//<p>如果 <code>nums</code>&nbsp;的某个子数组中不同整数的个数恰好为 <code>k</code>，则称 <code>nums</code>&nbsp;的这个连续、不一定不同的子数组为 <strong>「</strong><strong>好子数组 」</strong>。</p>
//
//<ul> 
// <li>例如，<code>[1,2,3,1,2]</code> 中有&nbsp;<code>3</code>&nbsp;个不同的整数：<code>1</code>，<code>2</code>，以及&nbsp;<code>3</code>。</li> 
//</ul>
//
//<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,1,2,3], k = 2
//<strong>输出：</strong>7
//<strong>解释：</strong>恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,1,3,4], k = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数</li><li>滑动窗口</li></div></div><br><div><li>👍 406</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.HashMap;
import java.util.Map;

// 992.K 个不同整数的子数组
// 开题时间：2022-10-14 08:36:27
public class SubarraysWithKDifferentIntegers {
  public static void main(String[] args) {
    Solution solution = new SubarraysWithKDifferentIntegers().new Solution();
    //        System.out.println(solution.subarraysWithKDistinct(new int[]{
    //                1, 1, 1, 2, 1, 2, 1, 2, 3, 3, 3, 2, 3, 4, 5
    //        }, 2));
    System.out.println(solution.subarraysWithKDistinctGJ_enhance(new int[]{1, 2, 1, 2, 3}, 2));
    //        System.out.println(solution.subarraysWithKDistinct(new int[]{27, 27, 43, 28, 11, 20, 1, 4, 49, 18, 37, 31, 31, 7, 3, 31, 50, 6, 50, 46, 4, 13, 31, 49, 15, 52, 25, 31, 35, 4, 11, 50, 40, 1, 49, 14, 46, 16, 11, 16, 39, 26, 13, 4, 37, 39, 46, 27, 49, 39, 49, 50, 37, 9, 30, 45, 51, 47, 18, 49, 24, 24, 46, 47, 18, 46, 52, 47, 50, 4, 39, 22, 50, 40, 3, 52, 24, 50, 38, 30, 14, 12, 1, 5, 52, 44, 3, 49, 45, 37, 40, 35, 50, 50, 23, 32, 1, 2}, 20));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 1.r++，直到 [0,r) 满足条件，求索引位置往前的「最大数组长（已知）」-「最小数组长」
     * 2.nums[r]条件判断
     *      nums[r] not distinct: 求索引位置往前的「最大数组长（已知）」-「最小数组长」
     *      nums[r] is  distinct: 去掉开头的单个重复元素后，再求索引位置往前的「最大数组长（已知）」-「最小数组长」
     */
    //[l,r) ~ [i,r)恰好k个不同整数
    public int subarraysWithKDistinct(int[] nums, int k) {
      int cnt = 0;
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0, l = 0, r = 0; r < nums.length; ) {
        map.merge(nums[r++], 1, Integer::sum);
        int size = map.size();
        if (size >= k) {
          if (size > k) {
            while (!map.get(nums[i]).equals(1))
              map.merge(nums[i++], -1, Integer::sum);
            map.remove(nums[i++]);
            l = i;
          }
          while (!map.get(nums[i]).equals(1))
            map.merge(nums[i++], -1, Integer::sum);
          cnt += i - l + 1;
        }
      }
      return cnt;
    }
    
    public int subarraysWithKDistinct2(int[] nums, int k) {
      int cnt = 0;
      int len = nums.length;
      int[] cnts = new int[len + 1];
      for (int i = 0, l = 0, r = 0, size = 0; r < len; ) {
        if (cnts[nums[r++]]++ == 0)
          size++;
        if (size >= k) {
          if (size > k) {
            while (--cnts[nums[i++]] != 0) {
            }
            size = k;
            l = i;
          }
          while (cnts[nums[i]] != 1)
            --cnts[nums[i++]];
          cnt += i - l + 1;
        }
      }
      return cnt;
    }
    
    public int subarraysWithKDistinct3(int[] nums, int k) {
      int cnt = 0;
      int len = nums.length;
      int[] cnts = new int[len + 1];
      for (int i = 0, l = 0, r = 0, size = 0; r < len; ) {
        if (cnts[nums[r++]]++ == 0)
          size++;
        if (size == k) {
          while (cnts[nums[i]] != 1)
            --cnts[nums[i++]];
          cnt += i - l + 1;
        } else if (size > k) {
          while (--cnts[nums[i++]] != 0) {
          }
          size = k;
          l = i;
          while (cnts[nums[i]] != 1)
            --cnts[nums[i++]];
          cnt += i - l + 1;
        }
      }
      return cnt;
    }
    
    /*
     *  恰好为k个不同整数的子数组个数=
     *       最多 k 个不同整数的子数组个数
     *       -
     *       最多k-1个不同整数的子数组个数
     */
    // 滑动窗口
    public int subarraysWithKDistinctGJ(int[] nums, int k) {
      return atMostNDistinct(nums, k) - atMostNDistinct(nums, k - 1);
    }
    
    private int atMostNDistinct(int[] nums, int n) {
      int cnt = 0;
      int len = nums.length;
      int[] cnts = new int[len + 1];
      for (int l = 0, r = 0, size = 0; r < len; ) {
        if (cnts[nums[r++]]++ == 0)
          size++;
        if (size > n) {
          while (--cnts[nums[l++]] != 0) {
          }
          size = n;
        }
        cnt += r - l;
      }
      return cnt;
    }
    
    public int subarraysWithKDistinctGJ_enhance(int[] nums, int k) {
      int cnt = 0;
      int len = nums.length;
      int[] cnts1 = new int[len + 1];
      int[] cnts2 = new int[len + 1];
      for (int l1 = 0, l2 = 0, r = 0, size1 = 0, size2 = 0, k2 = k - 1; r < len; ) {
        if (cnts1[nums[r]]++ == 0)
          size1++;
        if (cnts2[nums[r++]]++ == 0)
          size2++;
        
        // region 「if+while」结构 5ms
        if (size1 > k) {
          while (--cnts1[nums[l1++]] > 0) {
          }
          size1 = k;
        }
        if (size2 > k2) {
          while (--cnts2[nums[l2++]] > 0) {
          }
          size2 = k2;
        }
        // endregion
        
        // region 「while+if」结构 3ms
        //                while (size1 > k)
        //                    if (--cnts1[nums[l1++]] == 0)
        //                        size1--;
        //
        //                while (size2 > k2)
        //                    if (--cnts2[nums[l2++]] == 0)
        //                        size2--;
        // endregion
        
        cnt += l2 - l1;
      }
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}