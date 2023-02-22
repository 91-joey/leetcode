package _2_algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 51.N 皇后 <br>
 * 开题时间：2023-02-08 14:33:39
 */
public class NQueens {
  public static void main(String[] args) {
    Solution solution = new NQueens().new Solution();
    System.out.println(solution.solveNQueens(9));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 回溯
    public List<List<String>> solveNQueens(int n) {
      ArrayList<List<String>> ans = new ArrayList<>();
      char[] cs = new char[n];
      Arrays.fill(cs, '.');
      
      backtrack(
          ans,
          new LinkedList<String>(),
          cs,
          new boolean[n],
          new boolean[2 * n - 1],
          new boolean[2 * n - 1],
          0,
          n);
      
      return ans;
    }
    
    /**
     * 回溯
     * <p>
     * 递归终止条件：所有行都放置了皇后，即 i == n
     * 皇后放置合法条件：不在之前行所放置的皇后的「列、斜线、反斜线」上，即：
     * - !col[j]
     * - !slash[i + j]
     * - !backslash[i - j + n - 1] （注意这里反斜线的行列号差值可能为负，要加上 n - 1 变为非负数）
     * 状态空间（需要回溯的变量）：
     * - stack
     * - col
     * - slash
     * - backslash
     * 备注：cs 并不属于状态变量，它只是用于辅助构造字符串（虽然形式上它也进行了“回溯”，但这种回溯只是在同层级别间）。
     *
     * @param ans       结果集合
     * @param stack     棋子摆放方案栈
     * @param cs        字符数组（填充 '.' ）
     * @param col       列访问标记数组
     * @param slash     斜线访问标记数组
     * @param backslash 反斜线访问标记数组
     * @param i         行号
     * @param n         总行数
     */
    private void backtrack(
        List<List<String>> ans,
        Deque<String> stack,
        char[] cs,
        boolean[] col,
        boolean[] slash,
        boolean[] backslash,
        int i,
        int n) {
      if (i == n) {
        ans.add(new LinkedList<>(stack));
        return;
      }
      for (int j = 0; j < n; j++) {
        if (!col[j] && !slash[i + j] && !backslash[i - j + n - 1]) {
          cs[j] = 'Q';
          stack.addLast(new String(cs));
          cs[j] = '.';
          col[j] = true;
          slash[i + j] = true;
          backslash[i - j + n - 1] = true;
          backtrack(ans, stack, cs, col, slash, backslash, i + 1, n);
          col[j] = false;
          slash[j + i] = false;
          backslash[i - j + n - 1] = false;
          stack.removeLast();
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}