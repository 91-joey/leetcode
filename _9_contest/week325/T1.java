package org.example.leetcode.problems._9_contest.week325;

public class T1 {
    public static void main(String[] args) {

    }

    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for (int i = 0; i <= (n + 1) / 2; i++) {
            if (target.equals(words[(startIndex - i + n) % n]) ||
                    target.equals(words[(startIndex + i) % n]))
                return i;
        }
        return -1;
    }

}
