package org.example.leetcode.problems._9_contest.week323;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//6258. 数组中最长的方波
public class T2 {
    public static void main(String[] args) {

    }

    //哈希表 + 动态规划
    public int longestSquareStreak6(int[] nums) {
        Arrays.sort(nums);

        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int num : nums)
            dp.put(num, 1);

        int max = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            double sqrt = Math.sqrt(nums[i]);
            if ((int) sqrt == sqrt) {
                dp.put(nums[i], dp.getOrDefault((int) sqrt, 0) + 1);
                max = Math.max(max, dp.get(nums[i]));
            }
        }
        return max <= 1 ? -1 : max;
    }

    public int longestSquareStreak9(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int max = -1;
        for (int num : nums) {
            int cnt = 1;
            for (int target = num * num; set.contains(target); target *= target)
                cnt++;
            max = Math.max(max, cnt);
        }
        return max == 1 ? -1 : max;
    }

    public int longestSquareStreak8(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int max = -1;
        for (int num : set) {
            int cnt = 1;
            for (int target = num * num; set.contains(target); target *= target)
                cnt++;
            max = Math.max(max, cnt);
        }
        return max <= 1 ? -1 : max;
    }

    //☆☆☆☆☆ 哈希表 + 枚举
    public int longestSquareStreak(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        int max = -1;
        for (int num : set) {
            int cnt = 0;
            for (; set.contains(num); num *= num)
                cnt++;
            max = Math.max(max, cnt);
        }

        return max <= 1 ? -1 : max;
    }
}
