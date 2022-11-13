package org.example.leetcode.problems._9_contest.week319;

//6234. 最小公倍数为 K 的子数组数目
public class T2 {
    public static void main(String[] args) {
        T2 t = new T2();
        System.out.println(t.subarrayLCM(new int[]{3, 6, 2, 7, 1}, 6));
    }

    //32*2 32*3 32*5    32*2*3*5
    //3*5 2*5 2*3
    int[][] lcms;

    public int subarrayLCM(int[] nums, int k) {
        int cnt = 0;
        int len = nums.length;
        lcms = new int[len][len];
        for (int i = 0; i < len; i++)
            lcms[i][i] = nums[i];

        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                if (check(nums, k, j, i))
                    cnt++;
            }
        }
        return cnt;
    }

    private boolean check(int[] nums, int k, int start, int end) {
        if (start == end)
            return k == nums[start];

//        int lcm = nums[end];
//        for (int i = end - 1; i >= start; i--) {
////            lcm = lcm(lcm, nums[i]);
//            lcms[i][end] = lcm(lcms[i - 1][end], nums[i]);
//            if (lcms[i][end] % lcm != 0)
//                return false;
//        }

        lcms[start][end] = lcm(lcms[start][end - 1], nums[end]);
        return lcms[start][end] == k;
    }

    private int lcm(int m, int n) {
        int a = 1, b = 1;
        int i = m, j = n;
        while (b != 0) {
            a = m / n;
            b = m % n;
            m = n;
            n = b;
        }
        return i * j / m;
    }

}
