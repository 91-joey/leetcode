package org.example.leetcode.problems._9_contest.history.week326;

import java.util.HashSet;

//6279. Distinct Prime Factors of Product of Array
public class T2 {
    public static void main(String[] args) {

    }

    //分解质因数
    public int distinctPrimeFactors(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            for (int i = 2; i * i <= x; i++)
                if (x % i == 0) {
                    set.add(i);
                    while (x % i == 0)
                        x /= i;
                }
            if (x > 1)
                set.add(x);
        }
        return set.size();
    }
}
