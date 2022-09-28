package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.Arrays;

//209. 长度最小的子数组
public class MinSubArrayLen {
    //    1.超暴力   n^3 1
    public int minSubArrayLen1(int target, int[] nums) {
        int length = nums.length;
        int sum = 0;
//        1.确定子数组长度
        for (int len = 1; len <= length; len++) {
//            2.确定子数组起点
            for (int i = 0; i <= length - len; i++) {
//                3.遍历子数组
                for (int j = i; j < i + len; j++) {
                    sum += nums[j];
                }
                if (sum >= target) {
                    return len;
                }
                sum = 0;
            }
        }
        return 0;
    }

    //    2.暴力解   n^2 1
    public int minSubArrayLen2(int target, int[] nums) {
        int length = nums.length;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int sum = 0;
            for (int j = i; j < Math.min(length, minLen + i); j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    //        3.前缀和 + 二分查找    n*logn  n
    public int minSubArrayLen3(int target, int[] nums) {
        int length = nums.length;
        int minLen = Integer.MAX_VALUE;
        int[] sums = new int[length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= length; i++) {
//            -(insertion point) - 1    or  >=0
            int j = Arrays.binarySearch(sums, sums[i - 1] + target);
            if (j < 0) {
                j = -j - 1;
            }
            if (j <= length) {
                minLen = Math.min(minLen, j - i + 1);
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

//        4.双指针   n   1
    public int minSubArrayLen4(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (end < nums.length) {
            sum += nums[end++];
            while (sum >= target) {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        System.out.println(new MinSubArrayLen().minSubArrayLen4(7, new int[]{2, 3, 1, 2, 4, 3}));
//        System.out.println(new MinSubArrayLen().minSubArrayLen2(11, new int[]{1, 2, 3, 4, 5}));
    }
}
