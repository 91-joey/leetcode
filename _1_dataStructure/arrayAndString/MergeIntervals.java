package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 56. 合并区间
public class MergeIntervals {
  // 1.自解
  public static int[][] merge1(int[][] intervals) {
    List<int[]> list = new ArrayList<>();
    outer:
    for (int i = 0; i < intervals.length - 1; i++) {
      for (int j = i + 1; j < intervals.length; j++) {
        if (!(intervals[i][1] < intervals[j][0] || intervals[j][1] < intervals[i][0])) {
          intervals[j][0] = Math.min(intervals[i][0], intervals[j][0]);
          intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
          //                        flags[j] = !flags[j];
          continue outer;
        }
      }
      list.add(intervals[i]);
    }
    list.add(intervals[intervals.length - 1]);
    return list.toArray(new int[0][]);
  }
  
  //☆☆☆☆☆ 排序（按左端点排序 + 正序遍历）
  public int[][] merge2(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
    List<int[]> list = new ArrayList<>() {{
      add(intervals[0]);
    }};
    int[] lst;
    for (int[] interval : intervals)
      if (interval[0] > (lst = list.get(list.size() - 1))[1])
        list.add(interval);
      else
        lst[1] = Math.max(lst[1], interval[1]);
    return list.toArray(new int[list.size()][]);
  }
  
  //☆☆☆☆☆ 排序（按右端点排序 + 逆序遍历）
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
    int n = intervals.length;
    List<int[]> ans = new ArrayList<>() {{
      add(intervals[n - 1]);
    }};
    int[] lst;
    for (int i = n - 2; i >= 0; i--)
      if ((lst = ans.get(ans.size() - 1))[0] <= intervals[i][1])
        lst[0] = Math.min(lst[0], intervals[i][0]);
      else
        ans.add(intervals[i]);
    return ans.toArray(new int[ans.size()][]);
  }
  
  public static void main(String[] args) {
    merge1(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}});
  }
}
