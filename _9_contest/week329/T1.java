package org.example.leetcode.problems._9_contest.week329;

import java.util.ArrayList;

//
public class T1 {
    public static void main(String[] args) {
        Solution solution = new T1().new Solution();
        System.out.println(solution);
    }

    class Solution {
        public int alternateDigitSum(int n) {
            int sum = 0;
            boolean positive = true;
            ArrayList<Integer> list = new ArrayList<>();
            while (n != 0) {
                int digit = n % 10;
                list.add(digit);
                n /= 10;
            }
            for (int i = list.size() - 1; i >= 0; i--) {
                sum += positive ? list.get(i) : -list.get(i);
                positive = !positive;
            }
            return sum;
        }
    }
}
