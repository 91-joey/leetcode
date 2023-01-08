package org.example.leetcode.problems._9_contest.week327;

import java.util.Comparator;
import java.util.PriorityQueue;

public class T4 {
    public static void main(String[] args) {

    }

    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[0] + arr[2]).reversed().thenComparing((o1, o2) -> o2[4] - o1[4]));
        for (int i = 0; i < time.length; i++) {
            pq.offer(new int[]{time[i][0], time[i][1], time[i][2], time[i][3], i});
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] poll = pq.poll();
            ans+=poll[0];
            int[] poll2 = pq.poll();

        }

        return ans;
    }
}
