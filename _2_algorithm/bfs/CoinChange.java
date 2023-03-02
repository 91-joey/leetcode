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
      int[] f = new int[amount + 1];
      Arrays.fill(f, INF);
      f[0] = 0;
      
      Arrays.sort(coins);
      
      for (int i = 1; i < amount + 1; i++) {
        for (int coin : coins) {
          int pre = i - coin;
          if (pre < 0) {
            break;
          }
          f[i] = Math.min(f[i], f[pre]);
        }
        f[i]++;
      }
      
      return f[amount] >= INF ? -1 : f[amount];
    }
    
    // bfs
    public int coinChange(int[] coins, int amount) {
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
  }
  // leetcode submit region end(Prohibit modification and deletion)
}