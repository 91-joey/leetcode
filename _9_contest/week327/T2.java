package org.example.leetcode.problems._9_contest.week327;

import java.util.Comparator;
import java.util.PriorityQueue;

public class T2 {
    public static void main(String[] args) {

    }

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : nums)
            pq.offer(x);

        long ans = 0;
        for (int i = 0; i < k; i++) {
            Integer poll = pq.poll();
            ans += poll;
            pq.offer((int) Math.ceil((double) poll / 3));
        }

        return ans;
    }

}
