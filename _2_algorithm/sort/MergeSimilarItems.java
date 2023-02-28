package _2_algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 2363.合并相似的物品 <br>
 * 开题时间：2023-02-28 09:06:41
 */
public class MergeSimilarItems {
  public static void main(String[] args) {
    Solution solution = new MergeSimilarItems().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 归并排序
    public List<List<Integer>> mergeSimilarItems9(int[][] items1, int[][] items2) {
      Arrays.sort(items1, (a, b) -> a[0] - b[0]);
      Arrays.sort(items2, (a, b) -> a[0] - b[0]);
      
      List<List<Integer>> ans = new ArrayList<>();
      for (int i = 0, j = 0; i < items1.length || j < items2.length; ) {
        if (i >= items1.length) {
          ans.add(Arrays.asList(items2[j][0], items2[j++][1]));
        } else if (j >= items2.length) {
          ans.add(Arrays.asList(items1[i][0], items1[i++][1]));
        } else if (items1[i][0] == items2[j][0]) {
          ans.add(Arrays.asList(items1[i][0], items1[i++][1] + items2[j++][1]));
        } else if (items1[i][0] < items2[j][0]) {
          ans.add(Arrays.asList(items1[i][0], items1[i++][1]));
        } else {
          ans.add(Arrays.asList(items2[j][0], items2[j++][1]));
        }
      }
      
      return ans;
    }
    
    // 有序集合
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
      TreeMap<Integer, Integer> map = new TreeMap<>();
      for (int[] item : items1) {
        map.put(item[0], item[1]);
      }
      for (int[] item : items2) {
        map.merge(item[0], item[1], Integer::sum);
      }
      
      return map.entrySet().stream().map(e -> Arrays.asList(e.getKey(), e.getValue())).toList();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}