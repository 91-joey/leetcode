package org.example.leetcode.problems._9_contest.week321;

import java.util.HashMap;

//2031. Count Subarrays With More Ones Than Zeros
public class T4 {
    public static void main(String[] args) {
//        System.out.println(countSubarrays(new int[]{3, 2, 1, 4, 5}, 4));
        System.out.println(countSubarrays(new int[]{2, 5, 1, 4, 3, 6}, 1));
    }

    //TLE
    public static int countSubarraysX(int[] nums, int k) {
        int idx = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                idx = i;
                break;
            }
        }

        int[] cnts = new int[idx + 1];
        for (int i = 1, cnt = 0; i <= idx; i++) {
            if (nums[idx - i] > k)
                cnt++;
            cnts[i] = cnt;
        }

        int cnt = 0;
        for (int i = idx, tmp = 0; i < n; i++) {
            if (nums[i] > k)
                tmp++;
            for (int j = 0; j < idx + 1; j++) {
                if ((i - idx + j + 1) / 2 == cnts[j] + tmp)
                    cnt++;
            }
        }
        return cnt;
    }

    //等价转换+哈希映射
    public static int countSubarrays9(int[] nums, int k) {
        int idx = 0;
        while (k != nums[idx]) idx++;

        HashMap<Integer, Integer> val2cnt = new HashMap<>();
        val2cnt.put(0, 1);

        for (int i = idx - 1, cnt = 0; i >= 0; i--) {
            cnt += nums[i] > k ? 1 : -1;
            val2cnt.merge(cnt, 1, Integer::sum);
        }

        int ans = val2cnt.get(0) + val2cnt.getOrDefault(1, 0);
        for (int i = idx + 1, cnt = 0; i < nums.length; i++) {
            cnt += nums[i] < k ? 1 : -1;
            ans += val2cnt.getOrDefault(cnt, 0) +
                    val2cnt.getOrDefault(cnt + 1, 0);
        }

        return ans;
    }

    //等价转换+数组
    public static int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int idx = 0;
        while (k != nums[idx]) idx++;

        int[] val2cnt = new int[n * 2 + 1];
        val2cnt[n] = 1;

        for (int i = idx - 1, cnt = n; i >= 0; i--) {
            cnt += nums[i] > k ? 1 : -1;
            val2cnt[cnt]++;
        }

        int ans = val2cnt[n] + val2cnt[n + 1];
        for (int i = idx + 1, cnt = n; i < n; i++) {
            cnt += nums[i] < k ? 1 : -1;
            ans += val2cnt[cnt] + val2cnt[cnt + 1];
        }

        return ans;
    }
}
