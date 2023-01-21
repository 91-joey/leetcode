package org.example.leetcode.problems._9_contest.biweek96;

//
public class T4 {
    public static void main(String[] args) {
        Solution solution = new T4().new Solution();
        System.out.println(solution);
    }

    class Solution {
        public boolean isReachable(int targetX, int targetY) {
            int[] arr = new int[31];
            for (int i = 0; i < 31; i++) {
                arr[i] = 1 << i;
            }

            boolean a = false, b = false;
            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] - arr[j] == targetX) {
                        a = true;
                    }
                    if (arr[i] - arr[j] == targetY) {
                        b = true;
                    }
                    if (a && b)
                        return true;
                }
            }
            return false;
        }
    }
}
