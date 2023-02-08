package org.example.leetcode.problems._1_dataStructure.graph;

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
    // 拓扑排序
    public int minimumSemesters(int n, int[][] prerequisites) {
      ArrayList<Integer>[] g = new ArrayList[n + 1];
      Arrays.setAll(g, i -> new ArrayList<>());
      int[] inDeg = new int[n + 1];
      for (int[] edge : prerequisites) {
        g[edge[0]].add(edge[1]);
        inDeg[edge[1]]++;
      }
      
      Queue<Integer> q = new LinkedList<>();
      for (int i = 1; i < n + 1; i++) {
        if (inDeg[i] == 0) {
          q.offer(i);
        }
      }
      
      int idx = 0;
      int semester = 0;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          idx++;
          for (int v : g[q.poll()]) {
            if (--inDeg[v] == 0) {
              q.offer(v);
            }
          }
        }
        semester++;
      }
      
      return idx == n ? semester : -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}