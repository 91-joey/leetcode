package _1_dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 207.课程表 <br>
 * 开题时间：2023-02-08 11:39:27
 */
public class CourseSchedule {
  public static void main(String[] args) {
    Solution solution = new CourseSchedule().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 拓扑排序
    public boolean canFinish(int n, int[][] prerequisites) {
      ArrayList<Integer>[] g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      int[] inDeg = new int[n];
      for (int[] edge : prerequisites) {
        // if (edge[0] != edge[1]) {
          g[edge[1]].add(edge[0]);
          inDeg[edge[0]]++;
        // }
      }
      
      Queue<Integer> q = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        if (inDeg[i] == 0) {
          q.offer(i);
        }
      }
      
      int idx = 0;
      while (!q.isEmpty()) {
        idx++;
        for (int v : g[q.poll()]) {
          if (--inDeg[v] == 0) {
            q.offer(v);
          }
        }
      }
      
      return idx == n;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}