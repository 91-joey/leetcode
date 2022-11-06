package org.example.leetcode.problems._9_contest.week318;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TotalCost {
    public long totalCost(int[] costs, int k, int candidates) {
        long ans = 0;
        int len = costs.length;
        if (candidates * 2 >= len) {
            Arrays.sort(costs);
            for (int i = 0; i < k; i++) {
                ans += costs[i];
            }
            return ans;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
            int diff = costs[o1] - costs[o2];
            return diff != 0 ? diff : o1 - o2;
        });
        for (int i = 0; i < candidates; i++) {
            q.offer(i);
            q.offer(len - 1 - i);
        }

        for (int l = candidates, r = len - 1 - candidates; k > 0 && l <= r; k--) {
            Integer poll = q.poll();
            ans += costs[poll];
            if (poll < l) {
                q.offer(l++);
            } else {
                q.offer(r--);
            }
        }

        for (; k > 0; k--) {
            Integer poll = q.poll();
            ans += costs[poll];
        }
        return ans;
    }
}
