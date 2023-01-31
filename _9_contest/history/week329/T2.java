package org.example.leetcode.problems._9_contest.history.week329;

import java.util.Arrays;
import java.util.Comparator;

//2545. Sort the Students by Their Kth Score
public class T2 {
    public static void main(String[] args) {
        Solution solution = new T2().new Solution();
        System.out.println(solution);
    }

    class Solution {
        public int[][] sortTheStudents(int[][] score, int k) {
            return Arrays.stream(score).sorted(Comparator.<int[]>comparingInt(a -> a[k]).reversed()).toArray(int[][]::new);
        }
    }
}
