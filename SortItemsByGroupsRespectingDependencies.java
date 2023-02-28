import _3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 1203.项目管理 <br>
 * 开题时间：2023-02-25 16:24:13
 */
public class SortItemsByGroupsRespectingDependencies {
  public static void main(String[] args) {
    Solution solution = new SortItemsByGroupsRespectingDependencies().new Solution();
    System.out.println(Arrays.toString(solution.sortItems(5, 3, new int[]{0, 0, 2, 1, 0}, Tools.to2DIntList("[[3],[],[],[],[1,3,2]]"))));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // import java.util.Collection;
  class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
      // 1.预处理不输入任何小组的组号
      for (int i = 0; i < n; i++) {
        if (group[i] == -1) {
          group[i] = m++;
        }
      }
      
      // 2.分别对小组和项目进行拓扑排序
      HashSet<Integer>[] gGroup = new HashSet[m];
      Arrays.setAll(gGroup, i -> new HashSet<>());
      int[] degGroup = new int[m];
      HashSet<Integer>[] gItem = new HashSet[n];
      Arrays.setAll(gItem, i -> new HashSet<>());
      int[] degItem = new int[n];
      
      for (int v = 0; v < n; v++) {
        for (Integer u : beforeItems.get(v)) {
          gItem[u].add(v);
          degItem[v]++;
          if (group[u] != group[v]) {
            if (gGroup[group[u]].add(group[v])) {
              degGroup[group[v]]++;
            }
          }
        }
      }
      
      List<Integer> listGroup = getTopologicalSort(gGroup, degGroup, m);
      if (listGroup == null) {
        return new int[0];
      }
      List<Integer> listItem = getTopologicalSort(gItem, degItem, n);
      if (listItem == null) {
        return new int[0];
      }
      
      // 3.根据项目的拓扑序、项目到组的多对一关系，建立组到项目的一对多关系（组中项目依然满足拓扑序）
      List<Integer>[] group2items = new ArrayList[m];
      Arrays.setAll(group2items, i -> new ArrayList<>());
      for (Integer item : listItem) {
        group2items[group[item]].add(item);
      }
  
      // 4.将组到项目的一对多关系按照小组的拓扑序「扁平化」，即为答案
      return listGroup.stream()
          .map(i -> group2items[i])
          .flatMap(Collection::stream)
          .mapToInt(Integer::intValue)
          .toArray();
    }
    
    public static List<Integer> getTopologicalSort(Collection<Integer>[] g, int[] deg, int n) {
      LinkedList<Integer> q = new LinkedList<>();
      for (int i = 0; i < deg.length; i++) {
        if (deg[i] == 0) {
          q.offer(i);
        }
      }
      
      List<Integer> ans = new ArrayList<>();
      while (!q.isEmpty()) {
        int u = q.poll();
        ans.add(u);
        for (int v : g[u]) {
          if (--deg[v] == 0) {
            q.offer(v);
          }
        }
      }
      
      return ans.size() == n ? ans : null;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}