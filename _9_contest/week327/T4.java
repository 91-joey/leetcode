package org.example.leetcode.problems._9_contest.week327;

import java.util.Comparator;
import java.util.PriorityQueue;

//6306. Time to Cross a Bridge
public class T4 {
    public static void main(String[] args) {

    }

    public int findCrossingTimX(int n, int k, int[][] time) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[0] + arr[2]).reversed().thenComparing((o1, o2) -> o2[4] - o1[4]));
        for (int i = 0; i < time.length; i++) {
            pq.offer(new int[]{time[i][0], time[i][1], time[i][2], time[i][3], i});
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] poll = pq.poll();
            ans += poll[0];
            int[] poll2 = pq.poll();

        }

        return ans;
    }

    //todo
    //☆☆☆☆☆ 四堆模拟
    public int findCrossingTime(int n, int k, int[][] time) {
        var workL = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));
        var workR = new PriorityQueue<int[]>(workL.comparator());
        var waitL = new PriorityQueue<int[]>(Comparator.<int[]>comparingInt(arr -> time[arr[0]][0] + time[arr[0]][2]).reversed().thenComparing((a, b) -> b[0] - a[0]));
        var waitR = new PriorityQueue<int[]>(waitL.comparator());
        for (int i = 0; i < k; i++) waitL.offer(new int[]{i, 0});
        int cur = 0;
        while (n > 0) {
            while (!workL.isEmpty() && workL.peek()[1] <= cur) waitL.offer(workL.poll()); // 左边完成放箱
            while (!workR.isEmpty() && workR.peek()[1] <= cur) waitR.offer(workR.poll()); // 右边完成搬箱
            if (!waitR.isEmpty() && waitR.peek()[1] <= cur) { // 右边过桥
                var p = waitR.poll();
                cur += time[p[0]][2];
                p[1] = cur + time[p[0]][3];
                workL.offer(p); // 放箱
            } else if (!waitL.isEmpty() && waitL.peek()[1] <= cur) { // 左边过桥
                var p = waitL.poll();
                cur += time[p[0]][0];
                p[1] = cur + time[p[0]][1];
                workR.offer(p); // 搬箱
                n--;
            } else if (workL.isEmpty()) cur = workR.peek()[1]; // cur 过小，找个最小的放箱/搬箱完成时间来更新 cur
            else if (workR.isEmpty()) cur = workL.peek()[1];
            else cur = Math.min(workL.peek()[1], workR.peek()[1]);
        }
        while (!workR.isEmpty()) {
            var p = workR.poll(); // 右边完成搬箱
            cur = Math.max(p[1], cur) + time[p[0]][2]; // 过桥
        }
        return cur; // 最后一个过桥的时间
    }
}
