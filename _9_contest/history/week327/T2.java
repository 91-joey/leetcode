package org.example.leetcode.problems._9_contest.history.week327;

import java.util.Comparator;
import java.util.PriorityQueue;

//6285. Maximal Score After Applying K Operations
public class T2 {
    public static void main(String[] args) {

    }

    //优先队列
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : nums)
            pq.offer(x);

        long ans = 0;
        for (int i = 0; i < k; i++) {
            int poll = pq.poll();
            ans += poll;
            pq.offer((poll + 2) / 3);
//            pq.offer((int) Math.ceil((double) poll / 3));
        }

        return ans;
    }

}
