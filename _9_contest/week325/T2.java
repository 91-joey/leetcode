package org.example.leetcode.problems._9_contest.week325;

public class T2 {
    public static void main(String[] args) {
        System.out.println(takeCharacters("aabaaaacaabc", 2));
        System.out.println(takeCharacters("a", 0));
    }

    public static int takeCharacters(String s, int k) {
        char[] cs = s.toCharArray();
        int[] freq = new int[3];
        for (char c : cs) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] < k)
                return -1;
            freq[i] -= k;
        }

        int n = cs.length;

        int l = 0, r = 0;
        int max = 0;
        while (r < n) {
            if (--freq[cs[r] - 'a'] < 0) {
                max = Math.max(max, r - l);
                while (freq[cs[r] - 'a'] < 0)
                    ++freq[cs[l++] - 'a'];
            }
            r++;
        }
        max = Math.max(max, r - l);

        return n - max;
//        return n - (r - l);
    }

}
