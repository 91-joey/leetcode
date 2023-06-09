package _1_dataStructure.monotonic;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1124.表现良好的最长时间段 <br>
 * 开题时间：2023-02-14 08:50:21
 */
public class LongestWellPerformingInterval {
  public static void main(String[] args) {
    Solution solution = new LongestWellPerformingInterval().new Solution();
    System.out.println(solution.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // brute force
    public int longestWPI9(int[] hours) {
      int ans = 0;
      int n = hours.length;
      for (int i = 0; i < n - ans; i++) {
        for (int j = i, sum = 0; j < n; j++) {
          if ((sum += 8 < hours[j] ? 1 : -1) > 0) {
            ans = Math.max(ans, j - i + 1);
          }
        }
      }
      return ans;
    }
    
    // ☆☆☆☆☆ 前缀和 + 哈希表
    public int longestWPI7(int[] hours) {
      int ans = 0;
      HashMap<Integer, Integer> val2firstIdx = new HashMap<>();
      for (int i = 0, sum = 0; i < hours.length; i++) {
        sum += 8 < hours[i] ? 1 : -1;
        ans = Math.max(ans, sum > 0 ?
            i + 1 :
            i - val2firstIdx.getOrDefault(sum - 1, i));
        val2firstIdx.putIfAbsent(sum, i);
      }
      return ans;
    }
  
    // 前缀和 + 数组
    public int longestWPI8(int[] hours) {
      int n = hours.length;
      int[] firstIdx = new int[n + 2];
      Arrays.fill(firstIdx, -1);
      int ans = 0;
      for (int i = 0, sum = 0; i < n; i++) {
        sum += (hours[i] > 8 ? 1 : -1);
        if (sum > 0) {
          ans = Math.max(ans, i + 1);
        } else {
          if (firstIdx[sum + n] != -1) {
            ans = Math.max(ans, i - firstIdx[sum + n]);
          }
          if (firstIdx[sum + 1 + n] == -1) {
            firstIdx[sum + 1 + n] = i;
          }
        }
      }
      return ans;
    }
  
    // ☆☆☆☆ 前缀和（取反） + 数组（存{索引+1}）
    public int longestWPI(int[] hours) {
      int n = hours.length;
      int[] pos = new int[n + 2];
      int ans = 0;
    
      for (int i = 1, sum = 0; i <= n; i++) {
        sum += hours[i - 1] > 8 ? -1 : +1;
        if (sum < 0) {
          ans = i;
        } else {
          if (pos[sum + 1] > 0) {
            ans = Math.max(ans, i - pos[sum + 1]);
          }
          if (pos[sum] == 0) {
            pos[sum] = i;
          }
        }
      }
    
      return ans;
    }
    
    // todo 单调栈
  }
  // leetcode submit region end(Prohibit modification and deletion)
}