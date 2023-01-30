package org.example.leetcode.problems._9_contest.week330;

// 2552. Count Increasing Quadruplets
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // brute force  n^4
    public long countQuadrupletsTLE(int[] nums) {
      long ans = 0;
      int n = nums.length;
      for (int i = 0; i < n; i++) {
        for (int k = i + 2; k < n; k++) {
          if (nums[i] >= nums[k]) {
            continue;
          }
          for (int j = i + 1; j < k; j++) {
            if (nums[k] >= nums[j]) {
              continue;
            }
            for (int l = k + 1; l < n; l++) {
              if (nums[j] < nums[l]) {
                ans++;
              }
            }
          }
        }
      }
      return ans;
    }
    
    /*
     * 有技巧的枚举 + 预处理前后缀 + 贡献法  n^2
     * 预处理前后缀：
     *  在 k 右侧的比 nums[j] 大的元素个数，记作 great[k][nums[j]]
     *  在 j 左侧的比 nums[k] 小的元素个数，记作 less[j][nums[k]]
     * 枚举 j 和 k，对于固定的 j 和 k，根据乘法原理，对答案的贡献为 less[j][nums[k]] * great[k][nums[j]]
     */
    public long countQuadruplets9(int[] nums) {
      int n = nums.length;
      
      int[][] great = new int[n][n + 1]; // 在 k 右边、比 nums[j] 大的 l 的个数
      for (int k = n - 2; k >= 2; k--) {
        for (int x = 1; x < n + 1; x++) {
          great[k][x] = great[k + 1][x] + (x < nums[k + 1] ? 1 : 0);
        }
      }
      
      long ans = 0;
      int[] less = new int[n + 1]; // 在 j 左边、比 nums[k] 小的 i 的个数
      for (int j = 1; j < n - 2; j++) {
        for (int x = 1; x < n + 1; x++) {
          less[x] += (nums[j - 1] < x ? 1 : 0);
        }
        for (int k = j + 1; k < n - 1; k++) {
          if (nums[j] > nums[k]) {
            ans += (long) less[nums[k]] * great[k][nums[j]];
          }
        }
      }
      
      return ans;
    }
    
    // 利用排列特性优化掉 less 数组
    public long countQuadruplets(int[] nums) {
      int n = nums.length;
      
      int[][] great = new int[n][n + 1]; // 在 k 右边、比 nums[j] 大的 l 的个数
      for (int k = n - 2; k >= 1; k--) {
        for (int x = 1; x < n + 1; x++) {
          great[k][x] = great[k + 1][x] + (x < nums[k + 1] ? 1 : 0);
        }
      }
      
      long ans = 0;
      for (int j = 1; j < n - 2; j++) {
        for (int k = j + 1; k < n - 1; k++) {
          if (nums[j] > nums[k]) {
            ans += (long) (nums[k] - (n - j - 1 - great[j][nums[k]])) * great[k][nums[j]];
          }
        }
      }
      
      return ans;
    }
  }
}
