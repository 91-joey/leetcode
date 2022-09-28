package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.Arrays;

//724. 寻找数组的中心下标
public class PivotIndex {
    //    1.自解
    public int pivotIndex1(int[] nums) {
        int left = 0;
        int right = 0;
        if (nums.length > 1) {
            for (int j = 1; j < nums.length; j++) {
                right += nums[j];
            }
        }
        if (left == right) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            left += nums[i - 1];
            right -= nums[i];
            if (left == right) {
                return i;
            }
        }
        return -1;
    }

    //    2.非官方解
    public int pivotIndex2(int[] nums) {
        int left = 0;
        int right = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; i++) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }

    //    3.官方解
    public int pivotIndex3(int[] nums) {
        int left = 0;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; i++) {
//            if(left==sum-left-nums[i]){
            if (2 * left + nums[i] == sum) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
