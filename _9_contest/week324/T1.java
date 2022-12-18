package org.example.leetcode.problems._9_contest.week324;

import java.util.HashMap;

public class T1 {
    public static void main(String[] args) {
        System.out.println(similarPairs(new String[]{"aba", "aabb", "abcd", "bac", "aabc"}));
    }

    public static int similarPairs(String[] words) {
        int ans = 0;
        HashMap<Integer, Integer> state2cnt = new HashMap<>();
        for (String word : words) {
            int state = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                state |= (1 << (c - 'a'));
            }
            ans += state2cnt.getOrDefault(state, 0);
            state2cnt.merge(state, 1, Integer::sum);
        }
        return ans;
    }
}
