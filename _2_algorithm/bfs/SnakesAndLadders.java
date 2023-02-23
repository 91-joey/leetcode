package _2_algorithm.bfs;

import _3_common.tool.Tools;

import java.util.LinkedList;

/**
 * 909.蛇梯棋 <br>
 * 开题时间：2023-02-22 11:20:16
 */
public class SnakesAndLadders {
  public static void main(String[] args) {
    Solution solution = new SnakesAndLadders().new Solution();
    // System.out.println(solution.snakesAndLadders(Tools.to2DIntArray("[[4,-1,-1,-1,-1,-4],[5,-1,-1,-1,-1,-5],[6,-1,-1,-1,-1,-6],[7,35,-1,-1,13,-7],[8,-1,-1,-1,-1,-8],[-9,15,-1,-1,-1,-9]]")));
    System.out.println(solution.snakesAndLadders(Tools.to2DIntArray("[[-1,11,6,-1],[-1,15,16,-1],[-1,7,-1,8],[-1,-1,-1,8]]")));
    // System.out.println(solution.snakesAndLadders(Tools.to2DIntArray("[[1,1,-1],[1,1,1],[-1,1,1]]")));
    // System.out.println(solution.snakesAndLadders(Tools.to2DIntArray("[[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]")));
    // test git config
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 数组扁平化预处理 + bfs
    public int snakesAndLadders9(int[][] board) {
      int n = board.length;
      int[] arr = new int[n * n + 1];
      for (int i = n - 1, idx = 1; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
          arr[idx++] = board[i][j];
        }
        
        if (--i < 0) {
          break;
        }
        
        for (int j = n - 1; j >= 0; j--) {
          arr[idx++] = board[i][j];
        }
      }
      
      LinkedList<Integer> q = new LinkedList<>();
      boolean[] vis = new boolean[n * n + 1];
      q.offer(1);
      vis[1] = true;
      
      int step = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int pos = q.poll();
          
          if (pos == n * n) {
            return step;
          }
          
          for (int j = Math.min(n * n, pos + 6); j >= pos + 1; j--) {
            if (!vis[j]) {
              vis[j] = true;
              q.offer(
                  arr[j] == -1 ?
                      j :
                      arr[j]
              );
            }
          }
        }
        step++;
      }
      
      return -1;
    }
    
    // bfs(优化)
    public int snakesAndLadders(int[][] board) {
      int n = board.length;
      int[] arr = new int[n * n + 1];
      for (int i = n - 1, idx = 1; i >= 0; i--) {
        for (int j = 0; j < n; j++) {
          arr[idx++] = board[i][j];
        }
      
        if (--i < 0) {
          break;
        }
      
        for (int j = n - 1; j >= 0; j--) {
          arr[idx++] = board[i][j];
        }
      }
    
      LinkedList<Integer> q = new LinkedList<Integer>();
      boolean[] vis = new boolean[n * n + 1];
      q.offer(1);
      vis[1] = true;
    
      int step = 1;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          Integer pos = q.poll();
          for (int np = Math.min(pos + 6, n * n); np > pos; np--) {
            // 优化1：真正的目标方格（有蛇或梯子时，传送到蛇或梯子的尾部）
            int npTrue = arr[np] == -1 ? np : arr[np];
            if (!vis[npTrue]) {
              // 优化2：直接在入队列就判断是否能到达终点
              if (npTrue == n * n) {
                return step;
              }
              q.offer(npTrue);
              vis[npTrue] = true;
            }
          }
        }
        step++;
      }
    
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}