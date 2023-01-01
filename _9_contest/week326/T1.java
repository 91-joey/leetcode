package org.example.leetcode.problems._9_contest.week326;

public class T1 {
    public static void main(String[] args) {

    }

    public int countDigits(int num) {
        int cnt = 0;
        for (int i = num; i != 0; i /= 10) {
            int digit = i % 10;
            if (num % digit == 0)
                cnt++;
        }
        return cnt;
    }
}
