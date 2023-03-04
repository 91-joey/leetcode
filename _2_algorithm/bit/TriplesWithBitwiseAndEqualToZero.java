package _2_algorithm.bit;

import java.util.HashMap;
import java.util.Map;

/**
 * 982.按位与为零的三元组 <br>
 * 开题时间：2023-03-04 08:47:15
 */
public class TriplesWithBitwiseAndEqualToZero {
  public static void main(String[] args) {
    Solution solution = new TriplesWithBitwiseAndEqualToZero().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 有技巧的枚举 + 数组计数  n^2 + C*n（C=2^16）
     * 预处理 nums[i] & nums[j] 的哈希计数
     * 枚举 nums[k] 和 (nums[i] & nums[j]) ，若 nums[k] & (nums[i] & nums[j]) == 0 ，则加上计数
     */
    public int countTriplets9(int[] nums) {
      int[] cnt = new int[1 << 16];
      int n = nums.length;
      for (int x : nums) {
        for (int y : nums) {
          cnt[x & y]++;
        }
      }
      
      int ans = 0;
      for (int z : nums) {
        for (int xy = (1 << 16) - 1; xy >= 0; xy--) {
          if ((z & xy) == 0) {
            ans += cnt[xy];
          }
        }
      }
      
      return ans;
    }
  
    // 有技巧的枚举 + 哈希计数  n^2 + C*n（C=2^16）
    public int countTriplets8(int[] nums) {
      HashMap<Integer, Integer> cnt = new HashMap<>();
      int n = nums.length;
      for (int x : nums) {
        for (int y : nums) {
          cnt.merge(x & y, 1, Integer::sum);
        }
      }
      
      int ans = 0;
      for (int z : nums) {
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
          if ((entry.getKey() & z) == 0) {
            ans += entry.getValue();
          }
        }
      }
      
      return ans;
    }
  
    // ☆☆☆☆ 有技巧的枚举 + 数组计数 + 子集优化
    public int countTriplets7(int[] nums) {
      int[] cnt = new int[1 << 16];
      int n = nums.length;
      for (int x : nums) {
        for (int y : nums) {
          cnt[x & y]++;
        }
      }
      
      int ans = 0;
      for (int z : nums) {
        int m = 0xffff ^ z; // 求补集
        int s = m;
        do {
          ans += cnt[s];
          s = (s - 1) & m; // 求补集的子集
        } while (s != m);
      }
      
      return ans;
    }
  
    // ☆☆☆☆☆ 有技巧的枚举 + 数组计数 + 子集优化 + 常数优化  n*(n+u)
    public int countTriplets(int[] nums) {
      // 计算 cnt 的实际大小 u，相应的全集就是 u−1
      int u = 1;
      for (int x : nums) {
        while (u <= x) {
          u <<= 1;
        }
      }
      
      int[] cnt = new int[u];
      cnt[0] = nums.length; // 直接统计空集
      for (int x : nums) {
        // 枚举补集的非空子集
        for (int m = x ^ (u - 1), s = m; s > 0; s = (s - 1) & m) {
          cnt[s]++;
        }
      }
      
      int ans = 0;
      for (int x : nums) {
        for (int y : nums) {
          ans += cnt[x & y];
        }
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}