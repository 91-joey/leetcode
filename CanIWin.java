
/**
 * 464.我能赢吗 <br>
 * 开题时间：2023-03-14 11:18:36
 */
public class CanIWin {
  public static void main(String[] args) {
    Solution solution = new CanIWin().new Solution();
    for (int i = 0; i <= 300; i++) {
      System.out.println(i + " : " + solution.canIWin(10, i));
    }
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    
    boolean[] vis;
    
    // 标记数组 + 回溯
    public boolean canIWinX(int maxChoosableInteger, int desiredTotal) {
      // !!WA：注意如果所有候选整数的和 < 目标值，则两个玩家都不能赢，先手玩家当然也不能赢
      
      vis = new boolean[maxChoosableInteger + 1];
      
      return canIWin(desiredTotal);
    }
    
    /**
     * 当前回合玩家是否稳赢
     *
     * @param t 当前回合剩余目标值
     * @return 稳赢则返回 true ，否则返回 false
     */
    private boolean canIWin(int t) {
      for (int i = 1; i < vis.length; i++) {
        if (!vis[i]) {
          // 选择了一个数后达到目标值，当前回合玩家稳赢
          if (t - i <= 0) {
            return true;
          }
          vis[i] = true;
          boolean b = canIWin(t - i);
          vis[i] = false;
          // 下一回合玩家稳输，当前回合玩家稳赢
          // 下一回合玩家稳赢，继续尝试使用其他整数
          if (!b) {
            return true;
          }
        }
      }
      // 当前回合玩家稳输
      return false;
    }
    
    Boolean[] memo;
    private int n;
    
    // ☆☆☆☆☆ 状压 + 记搜
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
      n = maxChoosableInteger;
      if (n >= desiredTotal) {
        return true;
      }
      // !!注意如果所有候选整数的和 < 目标值，则两个玩家都不能赢，先手玩家当然也不能赢
      if (n * (n + 1) / 2 < desiredTotal) {
        return false;
      }
      
      /*
       * 缓存整数池使用状态 s 对应的结果（当前回合玩家是否稳赢）：
       *  null ：未计算
       *  true ：稳赢
       *  false：稳输
       */
      memo = new Boolean[1 << (n + 1)];
      
      return dfs(0, desiredTotal);
    }
    
    /**
     * 当前回合玩家是否稳赢
     *
     * @param s 公共整数池的使用状态（二进制表示下，右起第 i 位为 1 表示整数 i 已使用）
     * @param t 当前回合剩余目标值
     */
    private boolean dfs(int s, int t) {
      if (memo[s] != null) {
        return memo[s];
      }
      if (t <= 0) {
        return memo[s] = false;
      }
      
      for (int i = 1; i <= n; i++) {
        // 未使用该整数、且下一回合玩家稳输
        if ((s & (1 << i)) == 0 && !dfs(s | (1 << i), t - i)) {
          return memo[s] = true;
        }
      }
      
      // 无论怎么选，都是稳输
      return memo[s] = false;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}