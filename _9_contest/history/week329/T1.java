package org.example.leetcode.problems._9_contest.history.week329;

import java.util.ArrayList;

//6296. Alternating Digit Sum
public class T1 {
    public static void main(String[] args) {
        Solution solution = new T1().new Solution();
        System.out.println(solution);
    }

    class Solution {
        //从低位到高位遍历存储在集合中，再逆序遍历集合、使用正负标记求和
        public int alternateDigitSum9(int n) {
            ArrayList<Integer> list = new ArrayList<>();
            while (n != 0) {
                list.add(n % 10);
                n /= 10;
            }

            int sum = 0;
            boolean positive = true;
            for (int i = list.size() - 1; i >= 0; i--) {
                sum += positive ? list.get(i) : -list.get(i);
                positive = !positive;
            }
            return sum;
        }

        //转字符串，从高位到低位遍历求和（正负号作为乘数）
        public int alternateDigitSum(int n) {
            String s = String.valueOf(n);
            int sum = 0, sign = 1;
            for (int i = 0; i < s.length(); i++) {
                sum += sign * (s.charAt(i) - '0');
                sign *= -1;
            }
            return sum;
        }

        //☆☆☆☆☆ 最低位取正，从低位到高位遍历求和，最后若最高位取负再对结果取反
        public int alternateDigitSum7(int n) {
            int sum = 0, sign = 1;
            for (; n != 0; n /= 10) {
                sum += sign * n % 10;
                sign = -sign;
            }
            return sum * -sign;
        }
    }
}
