package _2_algorithm.dfs;

/**
 * 529.扫雷游戏 <br>
 * 开题时间：2023-02-16 17:50:03
 */
public class Minesweeper {
  public static void main(String[] args) {
    Solution solution = new Minesweeper().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    private char[][] board;
  
    // dfs + 模拟
    public char[][] updateBoard(char[][] board, int[] click) {
      this.board = board;
      int n = board.length;
      int m = board[0].length;
  
      int r = click[0];
      int c = click[1];
      if (board[r][c] == 'M') {
        board[r][c] = 'X';
      } else {
        dfs(r, c);
      }
      
      return board;
    }
    
    private void dfs(int r, int c) {
      int cnt = 0;
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (i == 0 && j == 0)
            continue;
          int nr = r + i;
          int nc = c + j;
          if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M') {
            cnt++;
          }
        }
      }
      
      if (cnt == 0) {
        board[r][c] = 'B';
        for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
            if (i == 0 && j == 0)
              continue;
            int nr = r + i;
            int nc = c + j;
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'E') {
              dfs(nr, nc);
            }
          }
        }
      } else {
        board[r][c] = (char) ('0' + cnt);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}