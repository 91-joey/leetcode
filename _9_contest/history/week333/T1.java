package org.example.leetcode.problems._9_contest.history.week333;

import java.util.ArrayList;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
      ArrayList<int[]> list = new ArrayList<>();
      for (int i = 0, j = 0; i < nums1.length || j < nums2.length; ) {
        if (j >= nums2.length) {
          list.add(nums1[i++]);
        } else if (i >= nums1.length) {
          list.add(nums2[j++]);
        } else if (nums1[i][0] < nums2[j][0]) {
          list.add(nums1[i++]);
        } else if (nums1[i][0] > nums2[j][0]) {
          list.add(nums2[j++]);
        } else {
          list.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});
          i++;
          j++;
        }
      }
      return list.toArray(new int[list.size()][]);
    }
  }
}
