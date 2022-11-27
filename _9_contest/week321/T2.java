package org.example.leetcode.problems._9_contest.week321;

public class T2 {
    public static void main(String[] args) {

    }

    public int appendCharacters(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int m = sChars.length;
        int n = tChars.length;

        int i = 0, j = 0;
        for (; i < m && j < n; ) {
            if (sChars[i] == tChars[j])
                j++;
            i++;
        }

        return n - j;
    }

}
