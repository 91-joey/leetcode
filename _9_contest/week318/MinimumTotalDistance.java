package org.example.leetcode.problems._9_contest.week318;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumTotalDistance {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int min = 0;
        Arrays.sort(factory, Comparator.comparingInt(o -> o[0]));
        for (Integer e : robot) {
            int l = 0, r = factory.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (e <= factory[mid][0])
                    r = mid;
                else
                    l = mid + 1;
            }
            if (factory[r][0] == e) {
                if (factory[r][1] > 0) {
                    factory[r][1]--;
                } else {
                    l--;
                    r++;
                }
            } else {
                l--;
            }
            if (l != r) {
                while (l >= 0 && factory[l][1] <= 0) {
                    l--;
                }
                while (r < factory.length && factory[r][1] <= 0) {
                    r++;
                }
                if (r >= factory.length || (l >= 0 && e - factory[l][0] <= factory[r][0] - e)) {
                    min += e - factory[l][0];
                    factory[l][1]--;
                } else {
                    min += e - factory[r][0];
                    factory[r][1]--;
                }
            }
        }

        return min;
    }
}
