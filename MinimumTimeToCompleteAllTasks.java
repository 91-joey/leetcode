import java.util.Arrays;
import java.util.Comparator;

/**
 * 2589.完成所有任务的最少时间 <br>
 * 开题时间：2023-03-15 09:36:55
 */
public class MinimumTimeToCompleteAllTasks {
  public static void main(String[] args) {
    Solution solution = new MinimumTimeToCompleteAllTasks().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int findMinimumTime(int[][] tasks) {
      Arrays.sort(tasks, Comparator.<int[]>comparingInt(o -> o[0]).reversed());
      boolean[] vis = new boolean[2001];
      int ans = 0;
      for (int[] task : tasks) {
        int s = task[0];
        int e = task[1];
        int d = task[2];
        for (int i = s; i <= e; i++) {
          if (vis[i]) {
            d--;
          }
        }
        for (int i = s; d > 0; i++) {
          if (!vis[i]) {
            vis[i] = true;
            d--;
            ans++;
          }
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}