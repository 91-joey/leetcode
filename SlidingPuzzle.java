import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 773.滑动谜题 <br>
 * 开题时间：2023-03-01 14:57:50
 */
public class SlidingPuzzle {
  public static void main(String[] args) {
    Solution solution = new SlidingPuzzle().new Solution();
    System.out.println(solution.slidingPuzzle(new int[][]{{1, 2, 3}, {4, 0, 5}}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int[] DIRS = {1, 0, -1, 0, 1};
  
    // ☆☆☆☆☆ bfs：自行实现数组的哈希函数（把数组、0的位置包装成一个类）
    public int slidingPuzzle9(int[][] board) {
      int n = board.length;
      int m = board[0].length;
      Queue<State> q = new LinkedList<>();
      HashSet<State> vis = new HashSet<>();
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (board[i][j] == 0) {
            State s = new State(board, i, j);
            q.offer(s);
            vis.add(s);
          }
        }
      }
      
      int step = 0;
      int[][] t = new int[][]{{1, 2, 3}, {4, 5, 0}};
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          State s = q.poll();
          int[][] arr = s.board;
          if (Arrays.deepEquals(arr, t)) {
            return step;
          }
          int r = s.r;
          int c = s.c;
          
          for (int j = 0; j < 4; j++) {
            int nr = r + DIRS[j];
            int nc = c + DIRS[j + 1];
            if (0 <= nr && nr < n && 0 <= nc && nc < m) {
              arr[r][c] = arr[nr][nc];
              arr[nr][nc] = 0;
              
              if (!vis.contains(s)) {
                int[][] nArr = new int[n][];
                for (int k = 0; k < n; k++) {
                  nArr[k] = Arrays.copyOf(arr[k], arr[k].length);
                }
                State ns = new State(nArr, nr, nc);
                q.offer(ns);
                vis.add(ns);
              }
              
              arr[nr][nc] = arr[r][c];
              arr[r][c] = 0;
            }
          }
        }
        step++;
      }
      
      return -1;
    }
    
    // 每个位置的 0 ，可以交换的数的位置
    public static final int[][] map = {
        {1, 3},
        {0, 2, 4},
        {1, 5},
        {0, 4},
        {3, 1, 5},
        {2, 4}
    };
  
    // bfs：将数组转换成语言中可以直接进行哈希的类型（字符串）
    public int slidingPuzzle(int[][] board) {
      int n = board.length;
      int m = board[0].length;
      Queue<String> q = new LinkedList<>();
      Queue<Integer> qPos0 = new LinkedList<>(); // 0的位置
      HashSet<String> vis = new HashSet<>();
      
      String s = "";
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          s += board[i][j];
          if (board[i][j] == 0) {
            qPos0.add(i * m + j);
          }
        }
      }
      q.offer(s);
      vis.add(s);
  
      int step = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          s = q.poll();
          if (s.equals("123450")) {
            return step;
          }
          int pos = qPos0.poll();
          
          char[] arr = s.toCharArray();
          for (int nPos : map[pos]) {
            arr[pos] = arr[nPos];
            arr[nPos] = '0';
            
            String ns = new String(arr);
            if (!vis.contains(ns)) {
              q.offer(ns);
              qPos0.add(nPos);
              vis.add(ns);
            }
            
            // 回溯
            arr[nPos] = arr[pos];
            arr[pos] = '0';
          }
        }
        step++;
      }
      
      return -1;
    }
  }
  
  class State {
    int[][] board;
    int r;
    int c;
    
    public State(int[][] board, int r, int c) {
      this.board = board;
      this.r = r;
      this.c = c;
    }
    
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      
      State state = (State) o;
      
      return Arrays.deepEquals(board, state.board);
    }
    
    @Override
    public int hashCode() {
      return Arrays.deepHashCode(board);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}