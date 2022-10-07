package org.example.leetcode.problems.contest.lccup22.team;

import java.util.HashMap;
import java.util.Map;

public class MinNumBooths {
    public static void main(String[] args) {
        System.out.println(minNumBooths(new String[]{"acd", "bed", "accd"}));
        System.out.println(minNumBooths(new String[]{"abc","ab","ac","b"}));
    }

    public static int minNumBooths(String[] demand) {
        Map<Character, Integer> char2cnt = new HashMap<>();
        for (String s : demand) {
            Map<Character, Integer> char2cnt2 = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char2cnt2.merge(s.charAt(i), 1, Integer::sum);
            }
            for (Map.Entry<Character, Integer> entry : char2cnt2.entrySet()) {
                Character key = entry.getKey();
                Integer value = entry.getValue();
                if (!char2cnt.containsKey(key)) {
                    char2cnt.put(key, value);
                } else if (value.compareTo(char2cnt.get(key)) > 0) {
                    char2cnt.put(key, value);
                }
            }
        }
        int min = 0;
        for (Integer value : char2cnt.values()) {
            min += value;
        }
        return min;
    }
}
