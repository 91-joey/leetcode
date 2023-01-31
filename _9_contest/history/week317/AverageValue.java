package org.example.leetcode.problems._9_contest.history.week317;

//6220. 可被三整除的偶数的平均值
public class AverageValue {
    public int averageValue(int[] nums) {
        int sum = 0;
        int cnt = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                cnt++;
            }
        }
        return cnt == 0 ? 0 : sum / cnt;
    }
}
