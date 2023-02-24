package _1_dataStructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1136.并行课程 <br>
 * 开题时间：2023-02-08 12:09:00
 */
public class ParallelCourses {
  public static void main(String[] args) {
    Solution solution = new ParallelCourses().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 拓扑排序（bfs）
    public int minimumSemesters(int n, int[][] relations) {
      ArrayList<Integer>[] g = new ArrayList[n + 1];
      Arrays.setAll(g, i -> new ArrayList<>());
      int[] deg = new int[n + 1];
      for (int[] edge : relations) {
        deg[edge[1]]++;
        g[edge[0]].add(edge[1]);
      }
    
      LinkedList<Integer> q = new LinkedList<>();
      for (int i = 1; i < n + 1; i++) {
        if (deg[i] == 0) {
          q.add(i);
        }
      }
    
      int semesters = 0;
      int courses = 0;
      while (!q.isEmpty()) {
        courses += q.size();
        for (int i = q.size(); i > 0; i--) {
          for (Integer v : g[q.poll()]) {
            if (--deg[v] == 0) {
              q.add(v);
            }
          }
        }
        semesters++;
      }
    
      return courses == n ? semesters : -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}