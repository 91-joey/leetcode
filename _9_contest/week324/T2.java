package org.example.leetcode.problems._9_contest.week324;

//6266. Smallest Value After Replacing With Sum of Prime Factors
public class T2 {
    public static void main(String[] args) {
//        System.out.println(smallestValue(36));
        System.out.println(smallestValue(4));
//        System.out.println(smallestValue(15));
        for (int i = 2; i < 1000; i++) {
            System.out.println(i+" : " + smallestValue(i));
        }
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

    public static int smallestValue9(int n) {
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

    //☆☆☆☆☆ 质因数分解，质因数和与原始值相同即返回 $O(\sqrt n)$
    public static int smallestValue(int n) {
        while (true) {
            int sum = 0;
            int x = n;
            for (int i = 2; i * i <= x; i++)
                while (x % i == 0) {
                    sum += i;
                    x /= i;
                }
            if (x > 1)
                sum += x;
            if (sum == n)
                return n;
            n = sum;
        }
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
