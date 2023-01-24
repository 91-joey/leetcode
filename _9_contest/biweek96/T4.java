package org.example.leetcode.problems._9_contest.biweek96;

//2543. Check if Point Is Reachable
public class T4 {
    public static void main(String[] args) {
        Solution solution = new T4().new Solution();
        System.out.println(solution);
    }

    class Solution {
        public boolean isReachableX(int targetX, int targetY) {
            int[] arr = new int[31];
            for (int i = 0; i < 31; i++) {
                arr[i] = 1 << i;
            }

            boolean a = false, b = false;
            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] - arr[j] == targetX) {
                        a = true;
                    }
                    if (arr[i] - arr[j] == targetY) {
                        b = true;
                    }
                    if (a && b)
                        return true;
                }
            }
            return false;
        }

        /*
         * 问题转换 + GCD
         * 如果从 (1,1) 能够到达 (x,y) 的话，从 (x,y) 逆推也能够到达 (1,1)
         *
         * 4 种移动方案：
         *     正推         逆推
         * (x, y - x)   (x, y + x)
         * (x - y, y)   (x + y, y)
         * (2 * x, y)   (x / 2, y), x is even
         * (x, 2 * y)   (x, y / 2), y is even
         *
         * 设 g = gcd(x,y)
         * 前 2 种移动方案下，(x,y) 能够到达 (ax+by,cx+dy)，根据贝祖定理，g = 1
         * 后 2 种移动方案下，g 可以扩大到 2^k
         */
        public boolean isReachable(int targetX, int targetY) {
            return isPowerOfTwo(gcd(targetX, targetY));
        }

        public static int gcd(int a, int b) {
            while (a != 0) {
                int tmp = a;
                a = b % a;
                b = tmp;
            }
            return b;
        }

        public static boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n - 1)) == 0;
        }
    }
}
