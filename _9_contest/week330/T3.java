package org.example.leetcode.problems._9_contest.week330;

import java.util.Arrays;

// 6339. Put Marbles in Bags
public class T3 {
  public static void main(String[] args) {
    Solution solution = new T3().new Solution();
    System.out.println(solution.putMarbles(new int[]{24, 16, 62, 27, 8, 3, 70, 55, 13, 34, 9, 29, 10}, 11));
    // System.out.println(solution.putMarbles(new int[]{1, 3, 5, 1}, 2));
  }
  
  class Solution {
    /*
     * ☆☆☆☆☆ 问题转化 + 排序贪心
     * 问题相当于把 weights 划分成 k 个连续子数组，分数等于每个子数组的两端的值之和。
     * 设连续子数组右端点的索引为 idx_i，则分数为：(w[0]+w[idx_0])+(w[idx_0+1]+w[idx_1])+...+(w[idx_{k-2}+1]+w[idx_{k-1})+(w[idx_{k-1}+1]+w[n-1])
     *                                    即： w[0]+(w[idx_0]+w[idx_0+1])+(w[idx_1]+...+w[idx_{k-2}+1])+(w[idx_{k-1}+w[idx_{k-1}+1])+w[n-1]
     * 即分数为：首尾元素和 + 分割点两侧元素值和
     * 把所有 n−1 个 weights[i] + weights[i+1] 算出来，排序，那么最大的 k−1 个数和最小的 k−1 个数相减，即为答案。
     * 由于子数组长度为 1 时，分数为元素值的 2 倍，故「weights[i-1] + weights[i]」、「weights[i] + weights[i+1]」同在分数组成中也是可以的
     */
    public long putMarbles(int[] weights, int k) {
      int n = weights.length;
      for (int i = 0; i < n - 1; i++) {
        weights[i] += weights[i + 1];
      }
      Arrays.sort(weights, 0, n - 1);
      
      long ans = 0;
      // for (int l = 0, r = n - 2; l < k - 1; l++, r--) {
      //   ans += weights[r] - weights[l];
      // }
      for (int i = 0; i < Math.min(k - 1, n - k); i++) {
        ans += weights[n - 2 - i] - weights[i];
      }
      return ans;
    }
  }
}
