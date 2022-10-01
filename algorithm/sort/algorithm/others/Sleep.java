package org.example.leetcode.problems.algorithm.sort.algorithm.others;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;

import java.util.ArrayList;
import java.util.List;

//睡眠排序
public class Sleep {
    public static void main(String[] args) {
//        Swap.sort(Sleep::sleepSort);
        Swap.sortHard(Sleep::sleepSort);
    }

    public static List<Integer> sorted = new ArrayList<>();

    public static void sleepSort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int e : arr) {
            if (e < min) min = e;
            else if (max < e) max = e;
        }

        for (int e : arr)
            new Thread(new RunnableImpl(e, e - min)).start();

        try {
            Thread.sleep((max - min) * 100L);
            sorted.forEach(System.out::println);
            System.out.println(Swap.isSortedNaturally(sorted));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class RunnableImpl implements Runnable {
        int val;
        long sleepTime;

        public RunnableImpl(int val, long sleepTime) {
            this.val = val;
            this.sleepTime = sleepTime * 100L;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
                sorted.add(val);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
