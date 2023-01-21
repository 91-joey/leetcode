package org.example.leetcode.problems._9_contest.biweek96;

//
public class T1 {
    public static void main(String[] args) {
        Solution solution = new T1().new Solution();
        System.out.println(solution);
    }

    class Solution {
        public int getCommon(int[] nums1, int[] nums2) {
            int i = 0, j = 0;
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] == nums2[j]) {
                    return nums1[i];
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
            }
            return -1;
        }
    }
}
