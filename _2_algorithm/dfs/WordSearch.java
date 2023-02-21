package org.example.leetcode.problems._2_algorithm.dfs;

/**
 * 79.单词搜索 <br>
 * 开题时间：2023-02-11 17:37:00
 */
public class WordSearch {
  public static void main(String[] args) {
    Solution solution = new WordSearch().new Solution();
    System.out.println(solution.exist(new char[][]{{'a', 'a'}}, "aaa"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
    
    // 回溯
    public boolean exist(char[][] board, String word) {
      // 剪枝：字符网格中的字符必须包含目标单词相应的字符数
      int[] freq = new int[123];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
          freq[board[i][j]]++;
        }
      }
      for (int i = 0; i < word.length(); i++) {
        if (--freq[word.charAt(i)] < 0) {
          return false;
        }
      }
      
      char[] t = word.toCharArray();
      int m = board.length;
      int n = board[0].length;
      boolean[][] vis = new boolean[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (backtrack(board, t, vis, i, j, 0)) {
            return true;
          }
        }
      }
      return false;
    }
    
    private boolean backtrack(char[][] arr, char[] t, boolean[][] vis, int r, int c, int len) {
      if (arr[r][c] != t[len]) {
        return false;
      }
      if (len == t.length - 1) {
        return true;
      }
      
      vis[r][c] = true;
      for (int i = 0; i < 4; i++) {
        int rNew = r + DIRS[i];
        int cNew = c + DIRS[i + 1];
        if (0 <= rNew && rNew < arr.length && 0 <= cNew && cNew < arr[0].length &&
            !vis[rNew][cNew]) {
          if (backtrack(arr, t, vis, rNew, cNew, len + 1)) {
            return true;
          }
        }
      }
      vis[r][c] = false;
      
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}