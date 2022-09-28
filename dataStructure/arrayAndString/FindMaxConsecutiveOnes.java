package org.example.leetcode.problems.dataStructure.arrayAndString;

//485. 最大连续 1 的个数
public class FindMaxConsecutiveOnes {

//    1.快慢指针    n   1
    public int findMaxConsecutiveOnes1(int[] nums) {
        int max = 0;
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == 0) {
                max = Math.max(max, fast - slow);
                slow = fast + 1;
            }
            fast++;
        }
        max = Math.max(max, fast - slow);
        return max;
    }

//    2.官解    n   1
    public int findMaxConsecutiveOnes2(int[] nums) {
        int maxCnt = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                cnt++;
            } else if (i - 1 >= 0 && nums[i - 1] == 1) {
                maxCnt = Math.max(maxCnt, cnt);
                cnt=0;
            }
        }
        maxCnt = Math.max(maxCnt, cnt);
        return maxCnt;
    }
}
