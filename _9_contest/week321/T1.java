package org.example.leetcode.problems._9_contest.week321;

public class T1 {
    public static void main(String[] args) {

    }

    public int pivotInteger(int n) {
        for (int i = 1; i <= n; i++) {
            int sum1 = 0;
            for (int j = 1; j <= i; j++) {
                sum1 += j;
            }

            int sum2 = 0;
            for (int j = i; j <= n; j++) {
                sum2 += j;
            }

            if (sum1 == sum2)
                return i;
        }
        return -1;
    }

}
