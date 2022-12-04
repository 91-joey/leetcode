package org.example.leetcode.problems._9_contest.week322;

import java.util.Arrays;

public class T2 {
    public static void main(String[] args) {

    }

    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);

        int n = skill.length;
        for (int l = 1, r = n - 2, sum = skill[0] + skill[n - 1]; l < r; l++, r--)
            if (skill[l] + skill[r] != sum)
                return -1;

        long sum = 0;
        for (int l = 0, r = n - 1; l < r; l++, r--)
            sum += (long) skill[l] * skill[r];

        return sum;
    }

}
