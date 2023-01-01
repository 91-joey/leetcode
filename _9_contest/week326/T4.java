package org.example.leetcode.problems._9_contest.week326;

import java.util.ArrayList;
import java.util.Arrays;

public class T4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(1
                , 1000000)));
    }

    public static int[] closestPrimes(int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = Math.max(left, 2); i <= right; i++) {
            if (isPrime(i))
                list.add(i);
        }

        if (list.size() < 2)
            return new int[]{-1, -1};
        int min = Integer.MAX_VALUE;
        int[] ans = {-1, -1};
        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);
            int pre = list.get(i - 1);
            if (cur - pre < min) {
                min = cur - pre;
                ans = new int[]{pre, cur};
            }
        }
        return ans;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++)
            if (x % i == 0)
                return false;
        return true;
    }
}
