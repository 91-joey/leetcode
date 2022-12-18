package org.example.leetcode.problems._9_contest.week324;

public class T4 {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(12));
        System.out.println(Integer.highestOneBit(1024));
    }

    public int[] cycleLengthQueries9(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            String a = Integer.toBinaryString(queries[i][0]);
            String b = Integer.toBinaryString(queries[i][1]);
            String s = "";
            int j = 0;
            while (j < a.length() && j < b.length()) {
                char c = a.charAt(j);
                if (c == b.charAt(j++))
                    s += c;
                else
                    break;
            }
            ans[i] = a.length() + b.length() - 2 * j + 1;
        }

        return ans;
    }

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            int x = 1 << 31;
            while ((x & a) != x)
                x >>= 1;

            int y = 1 << 31;
            while ((y & b) != y)
                y >>= 1;

            while (((x & a) == x) == ((y & b) == y)) {
                x >>= 1;
                y >>= 1;
            }

            ans[i] = 65 - Integer.numberOfLeadingZeros(x) - Integer.numberOfLeadingZeros(y);
        }

        return ans;
    }

}
