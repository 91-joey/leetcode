package org.example.leetcode.problems._9_contest.week327;

public class T3 {
    public static void main(String[] args) {
        System.out.println(isItPossible("aa", "ab"));
    }

    public static boolean isItPossible(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (int i = 0; i < word1.length(); i++) freq1[word1.charAt(i) - 'a']++;
        for (int i = 0; i < word2.length(); i++) freq2[word2.charAt(i) - 'a']++;

        int aTmp = 0, bTmp = 0;
        for (int i = 0; i < 26; i++) if (freq1[i] > 0) aTmp++;
        for (int i = 0; i < 26; i++) if (freq2[i] > 0) bTmp++;

        for (int i = 0; i < 26; i++) {
            if (freq1[i] > 0) {
                for (int j = 0; j < 26; j++) {
                    if (freq2[j] > 0) {
                        if (i == j) {
                            if (aTmp == bTmp)
                                return true;
                        } else {
                            int a = aTmp, b = bTmp;
                            if (freq1[i] == 1)
                                a--;
                            if (freq1[j] == 0)
                                a++;

                            if (freq2[j] == 1)
                                b--;
                            if (freq2[i] == 0)
                                b++;

                            if (a == b)
                                return true;
                        }
                    }

                }
            }
        }

        return false;
    }

}
