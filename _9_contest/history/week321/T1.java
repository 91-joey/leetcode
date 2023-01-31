package org.example.leetcode.problems._9_contest.history.week321;

//6245. Find the Pivot Integer
public class T1 {
    public static void main(String[] args) {
        System.out.println(pivotInteger(8));
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            int ans = pivotInteger(i);
            if (ans != -1) {
//                System.out.print(ans + ",");
                System.out.println(i + " : " + ans);
            }
        }
    }

    //BL    n^2
    public int pivotInteger9(int n) {
        for (int i = 1; i <= n; i++) {
            int pre = 0;
            for (int j = 1; j <= i; j++) pre += j;

            int suf = 0;
            for (int j = i; j <= n; j++) suf += j;

            if (pre == suf)
                return i;
        }
        return -1;
    }

    public static int pivotInteger8(int n) {
        int sum = (1 + n) * n / 2;
        for (int i = 1, pre = 0; i <= n; i++) {
            pre += i;
            if (pre == sum - pre + i)
                return i;
        }
        return -1;
    }

    public static int pivotInteger7(int n) {
        for (int l = 1, r = n, tmp = (n + 1) * n / 2; l <= r; ) {
            int mid = ((r - l) >> 1) + l;
            int diff = mid * mid - tmp;
            if (diff == 0)
                return mid;
            else if (diff < 0)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    public static int pivotInteger6(int n) {
        double ans = Math.sqrt(n * (n + 1) / 2);
        return ans == (int) ans ? (int) ans : -1;
    }

    //打表
    public static int pivotInteger(int n) {
        return switch (n) {
            case 1 -> 1;
            case 8 -> 6;
            case 49 -> 35;
            case 288 -> 204;
            default -> -1;
        };
    }
}
