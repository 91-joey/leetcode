package org.example.leetcode.problems._9_contest.week324;

public class T2 {
    public static void main(String[] args) {
//        System.out.println(smallestValue(36));
        System.out.println(smallestValue(4));
//        System.out.println(smallestValue(15));
    }

    public static int smallestValueX(int n) {
        for (int i = 2; i <= n; ) {
            if (i == n) {
                System.out.println(n);
                break;
            } else if (n % i == 0) {
                System.out.println("*" + i);
                n /= i;
            } else
                i++;
        }
        return 0;
    }

    public static int smallestValue(int n) {
        int pre = 0;
        int sum = -1;
        while (pre != sum) {
            pre = sum;
            sum = 0;
            for (int i = 2; i < n; ) {
                if (n % i == 0) {
                    sum += i;
                    n /= i;
                } else
                    i++;
            }
            sum += n;
            n = sum;
        }
        return sum;
    }

    private static int getSum(int n) {
        int sum = 0;
        for (int i = 2; i < n; ) {
            if (n % i == 0) {
                sum += i;
                n /= i;
            } else
                i++;
        }
        return sum + n;
    }
}
