import _3_common.tool.Tools;

import java.util.Arrays;

/**
 * 1049.最后一块石头的重量 II <br>
 * 开题时间：2023-03-07 10:25:20
 */
public class LastStoneWeightIi {
  public static void main(String[] args) {
    Solution solution = new LastStoneWeightIi().new Solution();
    System.out.println(solution);
    System.out.println(Tools.factorial(10));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * ☆☆☆☆☆ 01背包
     * 假设最优解的方案为：(a-b)-(c-(d-e))，展开即为：a-b-c+d-e
     * 归纳可得出：最后所剩石头的重量（重量为 0 表示没有石头剩下）即为 stones 数组中的数字添加 +/- 符号之后的总和
     *
     * 具体的，将数组划分为两个石子堆（正数堆s1/负数堆s2），则有：
     *  ① s1+s2=sum
     *  ② s1>=s2
     * 我们的目标是求 s1-s2>=0 的最小值，将 ① 代入 ② ，也即求 s2<=sum/2 的最大值
     *
     * 转化为 01 背包问题：从 stones 数组中选择，凑成总和不超过 sum/2 的最大价值。
     * 其中「成本」&「价值」均为数值本身。
     *
     * 答案为 s1-s2=(sum-s2)-s2=sum-2*s2
     */
    public int lastStoneWeightII(int[] stones) {
      int sum = Arrays.stream(stones).sum();
      int[] f = new int[sum / 2 + 1];
      for (int x : stones) {
        for (int j = f.length - 1; j >= x; j--) {
          f[j] = Math.max(f[j], f[j - x] + x);
        }
      }
      return sum - 2 * f[f.length - 1];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}