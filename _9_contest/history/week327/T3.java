package org.example.leetcode.problems._9_contest.history.week327;

import java.util.Arrays;

//6284. Make Number of Distinct Characters Equal
public class T3 {
    public static void main(String[] args) {
//        System.out.println(isItPossible("aa", "ab"));
        System.out.println(isItPossible("ac", "b"));
    }

    //枚举
    public static boolean isItPossible9(String word1, String word2) {
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
                            if (freq1[i] == 1) a--;
                            if (freq1[j] == 0) a++;

                            if (freq2[j] == 1) b--;
                            if (freq2[i] == 0) b++;

                            if (a == b)
                                return true;
                        }
                    }

                }
            }
        }

        return false;
    }


    //☆☆☆☆☆ 枚举 + 预处理偏移量
    public static boolean isItPossible(String word1, String word2) {
        int n = 26;
        int[] freq1 = new int[n];
        int[] freq2 = new int[n];
        for (int i = 0; i < word1.length(); i++) freq1[word1.charAt(i) - 'a']++;
        for (int i = 0; i < word2.length(); i++) freq2[word2.charAt(i) - 'a']++;

        int diff = (int) Arrays.stream(freq1).filter(x -> x > 0).count() -
                   (int) Arrays.stream(freq2).filter(x -> x > 0).count();
        int[] diff1 = new int[n];
        int[] diff2 = new int[n];
        for (int i = 0; i < n; i++) {
            diff1[i] = (freq1[i] == 1 ? -1 : 0) + (freq2[i] == 0 ? -1 : 0);
            diff2[i] = (freq1[i] == 0 ? 1 : 0) + (freq2[i] == 1 ? 1 : 0);
        }

        for (int i = 0; i < n; i++)
            if (freq1[i] > 0)
                for (int j = 0; j < n; j++)
                    if (freq2[j] > 0)
                        if (i == j) {
                            if (diff == 0)
                                return true;
                        } else if (diff + diff1[i] + diff2[j] == 0)
                            return true;

        return false;
    }
}
