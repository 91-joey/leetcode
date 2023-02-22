package _2_algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 37.解数独 <br>
 * 开题时间：2023-02-09 10:56:56
 */
public class SudokuSolver {
  public static void main(String[] args) {
    Solution solution = new SudokuSolver().new Solution();
    char[][] board = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    solution.solveSudokuX(board);
    System.out.println("board = " + Arrays.deepToString(board));
    // System.out.println(solution.solveSudoku(Tools.to2DIntArray("[['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.','.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']]\n")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][][] box = new boolean[3][3][10];
    int cntDigit = 0;
    
    public void solveSudokuX(char[][] board) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.') {
            int idx = board[i][j] - '0';
            row[i][idx] = true;
            col[j][idx] = true;
            box[i / 3][j / 3][idx] = true;
            cntDigit++;
          }
        }
      }
      backtrackX(board, 0, -1);
    }
    
    private void backtrackX(char[][] board, int r, int c) {
      if (cntDigit == 81) {
        return;
      }
      for (int i = r; i < 9; i++) {
        int jStart = i == r ? c + 1 : 0;
        for (int j = jStart; j < 9; j++) {
          if (board[i][j] == '.') {
            for (int idx = 1; idx < 10; idx++) {
              if (!row[i][idx] && !col[j][idx] && !box[i / 3][j / 3][idx]) {
                board[i][j] = (char) (idx + '0');
                row[i][idx] = true;
                col[j][idx] = true;
                box[i / 3][j / 3][idx] = true;
                cntDigit++;
                // if (cntDigit == 81) {
                //   return;
                // }
                backtrackX(board, i, j);
                if (cntDigit == 81) {
                  return;
                }
                board[i][j] = '.';
                row[i][idx] = false;
                col[j][idx] = false;
                box[i / 3][j / 3][idx] = false;
                cntDigit--;
              }
            }
          }
        }
      }
    }
    
    List<int[]> spaces = new ArrayList<>();
    boolean found = false; // 找到一个解决方案，即可返回
    
    // 预处理枚举空间 + 回溯
    public void solveSudoku(char[][] board) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (board[i][j] != '.') {
            int digit = board[i][j] - '0';
            row[i][digit] = col[j][digit] = box[i / 3][j / 3][digit] = true; // 多个访问标记变量可以连写
          } else {
            spaces.add(new int[]{i, j});
          }
        }
      }
      backtrack(board, 0);
    }
    
    
    private void backtrack(char[][] board, int idx) {
      if (idx == spaces.size()) {
        found = true;
        return;
      }
      
      int[] pos = spaces.get(idx);
      int i = pos[0];
      int j = pos[1];
      for (int digit = 1; digit < 10; digit++) {
        if (!row[i][digit] && !col[j][digit] && !box[i / 3][j / 3][digit]) {
          board[i][j] = (char) (digit + '0');
          row[i][digit] = col[j][digit] = box[i / 3][j / 3][digit] = true;
          backtrack(board, idx + 1);
          if (found) {
            return;
          }
          // 这里可以不用回溯
          // board[i][j] = '.';
          row[i][digit] = col[j][digit] = box[i / 3][j / 3][digit] = false;
        }
      }
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}