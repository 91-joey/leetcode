package _2_algorithm.greedy;

import java.util.Arrays;

/**
 * 1798.你能构造出连续值的最大数目 <br>
 * 开题时间：2023-02-04 09:26:17
 */
public class MaximumNumberOfConsecutiveValuesYouCanMake {
  public static void main(String[] args) {
    Solution solution = new MaximumNumberOfConsecutiveValuesYouCanMake().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ 排序 + 贪心
     * 假设若干元素可以构造出整数：[0,x]，此时再多选一个元素 y 、则可以构造出整数：[0,x]、[y,x+y]
     * 若要使得多选元素一个元素后、答案变大，因为 y 为正整数，即恒有 x < x+y ，我们只要保证 y <= x+1 即可
     *
     * 注意：
     * 构造的整数包括 0 。
     */
    public int getMaximumConsecutive(int[] coins) {
      int ans = 1;
      Arrays.sort(coins);
      for (int coin : coins) {
        if (coin > ans) {
          return ans;
        }
        ans += coin;
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}