package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.Arrays;

//167. 两数之和 II - 输入有序数组
public class TwoSum {
    //    1.暴力解法  n*n 1
    public int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{};
    }

    //    2.二分查找    n*logn  1
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int left = i + 1;
            int right = numbers.length - 1;
            if (numbers[i] + numbers[right] >= target) {
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (numbers[i] + numbers[mid] == target) {
                        return new int[]{i + 1, mid + 1};
                    } else if (numbers[i] + numbers[mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
        return new int[]{};
    }

//        3.双指针   n   1
    public int[] twoSum3(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum2(new int[]{3, 24, 50, 79, 88, 150, 345}, 200)));
    }
}
