package org.example.leetcode.problems._9_contest.week328;

public class T1 {
    public static void main(String[] args) {

    }


    public int differenceOfSum(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int res = 0;
        for (int num : nums) {
            for (int i = num; i != 0; i /= 10) {
                int digit = i % 10;
                res += digit;
            }
        }
        return Math.abs(sum - res);
    }
}
