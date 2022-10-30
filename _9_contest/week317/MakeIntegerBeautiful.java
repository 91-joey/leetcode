package org.example.leetcode.problems._9_contest.week317;

public class MakeIntegerBeautiful {
    public static void main(String[] args) {
        MakeIntegerBeautiful makeIntegerBeautiful = new MakeIntegerBeautiful();
//        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful(16L, 6));
        System.out.println(makeIntegerBeautiful.makeIntegerBeautiful(123456L, 7));
    }

    public long makeIntegerBeautiful(long n, int target) {
        if (target >= 99)
            return 0;

        int sum = 0;
        long[] digits = new long[12];
        int idx = 11;
        for (long i = n; i != 0; i /= 10) {
            long digit = i % 10;
            digits[idx--] = digit;
            sum += digit;
        }
        if (sum <= target)
            return 0;

//        int sum2 = 1;
//        while (sum2 < target) {
//            sum2 += digits[++idx];
//        }

        for (long i = n; i <= 1_0000_0000_0000L; i++) {
            sum = 0;
            for (long j = i; j != 0; j /= 10) {
                sum += j % 10;
            }
            if (sum <= target)
                return i - n;
        }

        return -1;
    }
}
