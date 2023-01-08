package org.example.leetcode.problems._9_contest.week327;

public class T1 {
    public static void main(String[] args) {

    }

    public int maximumCount9(int[] nums) {
        int neg = 0, pos = 0;
        for (int x : nums)
            if (x < 0)
                neg++;
            else if (x > 0)
                pos++;
        return Math.max(neg, pos);
    }

    public int maximumCount(int[] nums) {
        int neg = -1;
        int n = nums.length;
        if (nums[0] < 0)
            neg = binarySearch(nums, 0, n - 1, 0);
        int pos = binarySearch2(nums, neg + 1, n, 1);

        return Math.max(neg+1, n -pos);
    }

    public static int binarySearch(int[] arr, int l, int r, int target) {
        while (l < r) {
            int mid = ((r - l + 1) >> 1) + l;
            if (target <= arr[mid])
                r = mid - 1;
            else
                l = mid;
        }
        return r;
    }

    public static int binarySearch2(int[] arr, int l, int r, int target) {
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            if (target <= arr[mid])
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }


}
