package org.example.leetcode.problems._9_contest.week322;

public class T1 {
    public static void main(String[] args) {

    }

    public boolean isCircularSentence(String sentence) {
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++)
            if (split[i].charAt(split[i].length() - 1) !=
                    split[(i + 1) % split.length].charAt(0))
                return false;
        return true;
    }

}
