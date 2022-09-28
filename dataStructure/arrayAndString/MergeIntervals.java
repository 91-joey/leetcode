package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//56. 合并区间
public class MergeIntervals {
    //    1.自解
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

    //    2.排序
    public static int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            if (list.isEmpty() || interval[0] > list.get(list.size() - 1)[1]) {
                list.add(interval);
            } else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], interval[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        merge1(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}});
    }
}
