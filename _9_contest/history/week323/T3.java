package org.example.leetcode.problems._9_contest.history.week323;

import java.util.Arrays;

//6259. 设计内存分配器
public class T3 {
    public static void main(String[] args) {

    }

    class Allocator {
        int[] arr;

        public Allocator(int n) {
            arr = new int[n];
        }

        public int allocate9(int size, int mID) {
            int l = 0, r = 0;
            while (r < arr.length && r - l < size)
                if (arr[r] == 0)
                    r++;
                else
                    l = ++r;

            if (r - l < size)
                return -1;
            Arrays.fill(arr, l, r, mID);

            return l;
        }

        public int allocate(int size, int mID) {
            int i = 0, cnt = 0;
            while (i < arr.length && cnt < size)
                if (arr[i++] == 0)
                    cnt++;
                else
                    cnt = 0;

            if (cnt < size)
                return -1;
            Arrays.fill(arr, i - cnt, i, mID);

            return i - cnt;
        }

        public int free(int mID) {
            int cnt = 0;
            for (int i = 0; i < arr.length; i++)
                if (arr[i] == mID) {
                    cnt++;
                    arr[i] = 0;
                }
            return cnt;
        }
    }
}
