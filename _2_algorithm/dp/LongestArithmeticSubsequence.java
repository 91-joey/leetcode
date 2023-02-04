//<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <code>nums</code>&nbsp;中最长等差子序列的<strong>长度</strong>。</p>
//
//<p>回想一下，<code>nums</code> 的子序列是一个列表&nbsp;<code>nums[i<sub>1</sub>], nums[i<sub>2</sub>], ..., nums[i<sub>k</sub>]</code> ，且&nbsp;<code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt;= nums.length - 1</code>。并且如果&nbsp;<code>seq[i+1] - seq[i]</code>(&nbsp;<code>0 &lt;= i &lt; seq.length - 1</code>) 的值都相同，那么序列&nbsp;<code>seq</code>&nbsp;是等差的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,6,9,12]
//<strong>输出：</strong>4
//<strong>解释： </strong>
// 整个数组是公差为 3 的等差数列。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [9,4,7,2,10]
//<strong>输出：</strong>3
//<strong>解释：</strong>
// 最长的等差子序列是 [4,7,10]。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [20,1,15,3,10,5,8]
//<strong>输出：</strong>4
//<strong>解释：</strong>
// 最长的等差子序列是 [20,15,10,5]。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 500</code></li> 
//</ul>
//
//<div><li>👍 213</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// 1027.最长等差数列
// 开题时间：2022-11-29 16:40:04
public class LongestArithmeticSubsequence {
  public static void main(String[] args) {
    Solution solution = new LongestArithmeticSubsequence().new Solution();
    System.out.println(solution.longestArithSeqLength(new int[]{3, 6, 9, 12}));
    //        System.out.println(solution.longestArithSeqLength(new int[]{9, 4, 7, 2, 10}));
    //        System.out.println(solution.longestArithSeqLength(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
    int[] nums = {44, 46, 22, 68, 45, 66, 43, 9, 37, 30, 50, 67, 32, 47, 44, 11, 15, 4, 11, 6, 20, 64, 54, 54, 61, 63, 23, 43, 3, 12, 51, 61, 16, 57, 14, 12, 55, 17, 18, 25, 19, 28, 45, 56, 29, 39, 52, 8, 1, 21, 17, 21, 23, 70, 51, 61, 21, 52, 25, 28};
    System.out.println(solution.longestArithSeqLength(nums));
    ArrayList<Integer> list = new ArrayList<>();
    int min = Arrays.stream(nums).min().getAsInt();
    int max = Arrays.stream(nums).max().getAsInt();
    
    for (int diff = min - max; diff <= max - min; diff++) {
      for (int i = 0; i < nums.length - 1; i++) {
        list.add(nums[i]);
        for (int j = i + 1, tmp = nums[i] + diff; j < nums.length; j++) {
          if (nums[j] == tmp) {
            tmp += diff;
            list.add(nums[j]);
            if (list.size() == 6) {
              list.forEach(System.out::println);
              System.out.println();
            }
          }
        }
        list.clear();
      }
    }
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * dp[i][j]:以 nums[i]、nums[j]结尾的等差子序列最大长度
     * dp[i][j]=max(dp[k][i]+1),2*dp[i]=dp[k]+dp[j],k<i
     */
    public int longestArithSeqLength(int[] nums) {
      int max = 2;
      
      // 求差值为 0 的等差子序列最大长度
      int[] freq = new int[501];
      for (int num : nums)
        freq[num]++;
      max = Math.max(max, Arrays.stream(freq).max().getAsInt());
      
      // 去除相邻的重复值
      int newSize = 1;
      for (int l = 0, r = 1; r < nums.length; r++) {
        if (nums[l] != nums[r]) {
          nums[newSize++] = nums[r];
          l = r;
        }
      }
      int[] distincts = Arrays.copyOfRange(nums, 0, newSize);
      
      // 建立「值 -> 索引集合」的哈希映射
      HashMap<Integer, List<Integer>> val2idx = new HashMap<>();
      int n = distincts.length;
      for (int i = 0; i < n; i++) {
        if (!val2idx.containsKey(distincts[i])) {
          ArrayList<Integer> list = new ArrayList<>();
          list.add(i);
          val2idx.put(distincts[i], list);
        } else {
          val2idx.get(distincts[i]).add(i);
        }
      }
      
      int[][] dp = new int[n - 1][n];
      for (int[] arr : dp) Arrays.fill(arr, 2);
      for (int i = 1; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          List<Integer> list = val2idx.get(2 * distincts[i] - distincts[j]);
          if (list == null)
            continue;
          
          // 二分查找「最后一个 < i 的索引」
          int l = -1, r = list.size() - 1;
          while (l < r) {
            int mid = ((r - l + 1) >> 1) + l;
            if (i <= list.get(mid))
              r = mid - 1;
            else
              l = mid;
          }
          
          if (r != -1) {
            dp[i][j] = dp[list.get(r)][i] + 1;
            max = Math.max(max, dp[i][j]);
          }
        }
        
      }
      return max;
    }
    
    //☆☆☆☆☆ dp[i][d]:以 arr[i] 结尾、等差为 d 的子序列最大长度
    public int longestArithSeqLength8(int[] nums) {
      int n = nums.length;
      int[][] dp = new int[n][1001];
      
      int max = 0;
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
          int d = nums[i] - nums[j] + 500;
          dp[i][d] = dp[j][d] + 1;
          max = Math.max(max, dp[i][d]);
        }
      }
      return max + 1;
    }
    
    /*
     * dp[i][j]:以 nums[i]、nums[j]结尾的等差子序列最大长度
     * dp[i][j]=max(dp[k][i]+1),2*dp[i]=dp[k]+dp[j],k<i
     * 优化：一边遍历一边加入哈希表
     */
    public int longestArithSeqLength9(int[] nums) {
      int max = 0;
      int n = nums.length;
      
      int[][] dp = new int[n - 1][n];
      HashMap<Integer, Integer> val2lstIdxBeforeI = new HashMap<>();
      val2lstIdxBeforeI.put(nums[0], 0);
      for (int i = 1; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          Integer idx = val2lstIdxBeforeI.get(2 * nums[i] - nums[j]);
          if (idx != null) {
            dp[i][j] = dp[idx][i] + 1;
            max = Math.max(max, dp[i][j]);
          }
        }
        val2lstIdxBeforeI.put(nums[i], i);
      }
      return max + 2;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}