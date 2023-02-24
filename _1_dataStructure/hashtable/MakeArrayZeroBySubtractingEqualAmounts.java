package _1_dataStructure.hashtable;

import java.util.Arrays;

/**
 * 2357.使数组中所有元素都等于零 <br>
 * 开题时间：2023-02-24 09:10:40
 */
public class MakeArrayZeroBySubtractingEqualAmounts {
  public static void main(String[] args) {
    Solution solution = new MakeArrayZeroBySubtractingEqualAmounts().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 贪心 + 哈希表：统计不同正整数的个数
    public int minimumOperations9(int[] nums) {
      boolean[] exists = new boolean[101];
      for (int x : nums) {
        exists[x] = true;
      }
      
      int ans = 0;
      for (int i = 1; i < 101; i++) {
        if (exists[i]) {
          ans++;
        }
      }
      
      return ans;
    }
    
    // ☆☆☆☆☆ 流操作
    public int minimumOperations(int[] nums) {
      return (int) Arrays.stream(nums)
          .filter(x -> x > 0)
          .distinct()
          .count();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}