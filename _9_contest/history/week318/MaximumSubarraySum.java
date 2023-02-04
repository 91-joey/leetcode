package org.example.leetcode.problems._9_contest.history.week318;

// 6230. 长度为 K 子数组中的最大和
public class MaximumSubarraySum {
  public long maximumSubarraySum(int[] nums, int k) {
    // 数字出现频率的数组
    int[] freq = new int[10_0001];
    // 不同的数字个数
    int cnt = 0;
    long sum = 0;
    long max = 0;
    // 1.先扩展窗口到大小为 k
    for (int i = 0; i < k; i++) {
      if (freq[nums[i]]++ == 0)
        cnt++;
      sum += nums[i];
    }
    
    // 2.保持窗口大小不变，不断向右滑动
    for (int i = 0; i < nums.length - k; i++) {
      // 窗口中元素各不相同时，更新子数组最大和
      if (cnt == k)
        max = Math.max(max, sum);
      if (--freq[nums[i]] == 0)
        cnt--;
      if (freq[nums[i + k]]++ == 0)
        cnt++;
      // 移出原窗口首元素，移入新窗口尾元素
      sum += nums[i + k] - nums[i];
    }
    // 3.窗口到达最右边（数组末尾）时，再次更新子数组最大和
    if (cnt == k)
      max = Math.max(max, sum);
    return max;
  }
}
