package org.example.leetcode.problems._9_contest.week326;

import java.util.ArrayList;
import java.util.Arrays;

//6280. Closest Prime Numbers in Range
public class T4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(4, 6)));
//        System.out.println(Arrays.toString(closestPrimes(10, 19)));
//        System.out.println(Arrays.toString(closestPrimes(1, 1000000)));
    }

    //枚举 (right-left)*sqrt(right)
    public static int[] closestPrimes9(int left, int right) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = Math.max(left, 2); i <= right; i++)
            if (isPrime(i))
                list.add(i);

        int min = Integer.MAX_VALUE;
        int[] ans = {-1, -1};
        int pre = 0;
        if (!list.isEmpty())
            pre = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);
            if (cur - pre < min) {
                min = cur - pre;
                ans = new int[]{pre, cur};
            }
            pre = cur;
        }
        return ans;
    }

    public static int[] closestPrimes8(int left, int right) {
        boolean[] notPrime = new boolean[right + 1];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= right; i++)
            if (!notPrime[i]) {
                list.add(i);
                for (long j = (long) i * i; j <= right; j += i)
                    notPrime[(int) j] = true;
            }

        list.add(1000003);
        int min = Integer.MAX_VALUE;
        int[] ans = {-1, -1};
        int lowerBound = lowerBound(list, left);
        for (int i = lowerBound + 1, pre = list.get(lowerBound); i < list.size() - 1; i++) {
            int cur = list.get(i);
            if (cur - pre < min) {
                min = cur - pre;
                ans = new int[]{pre, cur};
            }
            pre = cur;
        }
        return ans;
    }

    /*
     * ☆☆☆☆☆ 埃筛预处理质数 + 二分找第一个 >= left 的质数 + 枚举求最小间隙
     * 可以额外添加两个质数  1e6 + 1，从而无需判断下标是否越界。
     */
    static int[] primes = new int[78500];

    static {
        int max = 1000033;
        boolean[] notPrime = new boolean[max + 1];
        for (int i = 2, idx = 0; i <= max; i++)
            if (!notPrime[i]) {
                primes[idx++] = i;
                for (long j = (long) i * i; j <= max; j += i)
                    notPrime[(int) j] = true;
            }
    }

    public static int[] closestPrimes(int left, int right) {
        int min = Integer.MAX_VALUE;
        int[] ans = {-1, -1};
        int lowerBound = lowerBound(primes, left);
        for (int i = lowerBound + 1, pre = primes[lowerBound]; primes[i] <= right; i++) {
            int cur = primes[i];
            if (cur - pre < min) {
                min = cur - pre;
                ans = new int[]{pre, cur};
            }
            pre = cur;
        }
        return ans;
    }

    private static int lowerBound(int[] primes, int left) {
        int l = 0, r = primes.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            if (left <= primes[mid])
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }

    private static int lowerBound(ArrayList<Integer> list, int left) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            if (left <= list.get(mid))
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++)
            if (x % i == 0)
                return false;
        return true;
    }
}
