package _2_algorithm.bfs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 322.零钱兑换 <br>
 * 开题时间：2023-02-23 18:14:21
 */
public class CoinChange {
  public static void main(String[] args) {
    Solution solution = new CoinChange().new Solution();
    System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int INF = 0x3f3f3f3f;
    
    // ☆☆☆☆☆ dp amount*len(coins)
    public int coinChange9(int[] coins, int amount) {
      Arrays.sort(coins);
      
      int[] f = new int[amount + 1];
      Arrays.fill(f, amount + 1);
      f[0] = 0;
      
      for (int i = 0; i < f.length; i++) {
        for (int j = 0; j < coins.length && i - coins[j] >= 0; j++) {
          f[i] = Math.min(f[i], f[i - coins[j]] + 1);
        }
      }
      
      return f[amount] > amount ? -1 : f[amount];
    }
    
    // bfs
    public int coinChange8(int[] coins, int amount) {
      Arrays.sort(coins);
      LinkedList<Integer> q = new LinkedList<>();
      q.offer(0);
      boolean[] vis = new boolean[amount + 1];
      vis[0] = true;
      
      int ans = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int cur = q.poll();
          if (cur == amount) {
            return ans;
          }
          for (int j = 0; j < coins.length && coins[j] <= amount; j++) {
            int next = cur + coins[j];
            if (next > amount) {
              break;
            }
            if (!vis[next]) {
              q.offer(next);
              vis[next] = true;
            }
          }
        }
        ans++;
      }
      
      return -1;
    }
    
    /*
     * 看成是「完全背包」问题
     *  - 总金额 amount := 背包容量 v
     *  - 选择一枚硬币后，增加的金额 coins[i]    := 物品 i 的体积 v_i
     *  - 选择一枚硬币后，增加的个数（固定为 1）  := 物品 i 的价值 w_i
     *  - 每个硬币可以使用无限次 : 是完全背包
     *
     * 状态定义：
     *  f[i][v] := 考虑前 i 个硬币，恰好可以凑成总金额 v 的最少硬币个数
     * 状态转移：
     *  f[i][v] = min(f[i - 1][v], f[i][v - coins[i]] + 1)  i - 1 >= 0 , if v - coins[i] >= 0
     * base case:
     *  f[i][v] = INF
     *  f[i][0] = 0
     * ans:
     *  f[n][amount]
     */
    public int coinChange7(int[] coins, int amount) {
      Arrays.sort(coins);
      
      int n = coins.length;
      int[][] f = new int[n + 1][amount + 1];
      int INF = amount + 1;
      for (int i = 0; i < n + 1; i++) {
        Arrays.fill(f[i], 1, amount + 1, INF);
      }
      
      for (int i = 1; i < n + 1; i++) {
        for (int v = 1; v < amount + 1; v++) {
          int vPre = v - coins[i - 1];
          f[i][v] = Math.min(f[i - 1][v], vPre < 0 ? INF : f[i][vPre] + 1);
        }
      }
      
      return f[n][amount] > amount ? -1 : f[n][amount];
    }
    
    // 「完全背包」优化
    public int coinChange6(int[] coins, int amount) {
      Arrays.sort(coins);
      
      int n = coins.length;
      int[] f = new int[amount + 1];
      int INF = amount + 1;
      Arrays.fill(f, 1, amount + 1, INF);
      
      for (int coin : coins) {
        for (int v = coin; v < amount + 1; v++) {
          f[v] = Math.min(f[v], f[v - coin] + 1);
        }
      }
      
      return f[amount] >= INF ? -1 : f[amount];
    }
    
    
    // 套用「完全背包」模板
    public int coinChange(int[] coins, int amount) {
      int[] value = new int[coins.length];
      Arrays.fill(value, 1);
      return maxValueWithinVolume(coins.length, coins, value, amount);
    }
    
    public int maxValueWithinVolume(int n, int[] size, int[] value, int capacity) {
      int[] f = new int[capacity + 1];
      Arrays.fill(f, capacity + 1);
      f[0] = 0;
      for (int i = 0; i < n; i++) {
        // 细节，j 从 size[i] 开始遍历
        for (int j = size[i]; j < capacity + 1; j++) {
          f[j] = Math.min(f[j], f[j - size[i]] + value[i]);
        }
      }
      return f[capacity] > capacity ? -1 : f[capacity];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}