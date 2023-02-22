package _2_algorithm.dfs;

import _3_common.tool.Tools;

import java.util.Arrays;

/**
 * 473.火柴拼正方形 <br>
 * 开题时间：2023-02-10 17:13:21
 */
public class MatchsticksToSquare {
  public static void main(String[] args) {
    Solution solution = new MatchsticksToSquare().new Solution();
    System.out.println(solution.makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
    // System.out.println(Tools.factorial(15));
    long comb = Tools.comb(16, 4);
    System.out.println(comb * comb * comb);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int edge;
    boolean[] vis;
  
    // 排序 + 回溯 + 剪枝
    public boolean makesquare9(int[] matchsticks) {
      Arrays.sort(matchsticks);
      
      int n = matchsticks.length;
      int sum = Arrays.stream(matchsticks).sum();
      edge = sum / 4;
      vis = new boolean[n];
      if (sum % 4 != 0 || edge < matchsticks[n - 1]) {
        return false;
      }
      
      return backtrack9(matchsticks, 0, edge, sum);
    }
    
    /**
     * @param idx       当前搜索的正方形的边的序号
     * @param t         当前搜索的正方形的边的剩余待填充长度
     * @param remainder 剩余可用的火柴的总长度
     * @return
     */
    private boolean backtrack9(int[] matchsticks, int idx, int t, int remainder) {
      if (idx == 3) {
        if (remainder == edge) {
          return true;
        }
      } else if (t == 0) {
        return backtrack9(matchsticks, idx + 1, edge, remainder);
      } else {
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < matchsticks.length && matchsticks[i] <= t; i++) {
          if (!vis[i] && matchsticks[i] != pre) {
            pre = matchsticks[i];
            vis[i] = true;
            if (backtrack9(matchsticks, idx, t - matchsticks[i], remainder - matchsticks[i])) {
              return true;
            }
            vis[i] = false;
          }
        }
      }
      return false;
    }
  
    // ☆☆☆☆☆ 排序 + 回溯 + 剪枝（5处）
    public boolean makesquare(int[] ms) {
      // int sum = Arrays.stream(ms).sum();
      int sum = 0;
      for (int x : ms) {
        sum += x;
      }
      // 剪枝①：边长须为整数
      if (sum % 4 != 0) {
        return false;
      }
      
      Arrays.sort(ms);
      int n = ms.length;
      edge = sum / 4;
      vis = new boolean[n];
      // 剪枝②：因为需要使用所有的火柴棍，所以火柴棍最大长度须 <= 边长
      if (edge < ms[n - 1]) {
        return false;
      }
      
      return backtrack(ms, 0, ms.length - 1, edge);
    }
    
    /**
     * @param idx   当前搜索的正方形的边的序号（从 0 开始）
     * @param begin 当前遍历火柴数组的起点
     * @param t     当前搜索的正方形的边的目标待填充长度
     * @return
     */
    private boolean backtrack(int[] ms, int idx, int begin, int t) {
      // 递归终止条件：三条边已满足条件，第四条边必满足条件
      if (idx == 3) {
        return true;
        // 当前边已满足条件，继续对下一条边进行搜索（注意：遍历火柴数组的起点需要重设为数组最后一位（即最大值））
      } else if (t == 0) {
        return backtrack(ms, idx + 1, ms.length - 1, edge);
      } else {
        // 逆序遍历（从大到小），加快速度
        // 剪枝③：从起点开始向左遍历（不回头），原因：放置火柴棍的先后顺序与结果无关，我们可以按逆序放置
        for (int i = begin; i >= 0; i--) {
          // 剪枝④：当前火柴棍长度 <= 目标待填充长度
          if (ms[i] <= t && !vis[i]) {
            vis[i] = true;
            if (backtrack(ms, idx, i - 1, t - ms[i])) {
              return true;
            }
            vis[i] = false;
            // 剪枝⑤：相同长度火柴棍只需选取一个
            while (i - 1 >= 0 && ms[i - 1] == ms[i]) {
              i--;
            }
          }
        }
      }
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}