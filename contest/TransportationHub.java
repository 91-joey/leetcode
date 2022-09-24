package org.example.leetcode.problems.contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransportationHub {
    public static void main(String[] args) {
//        System.out.println(transportationHub(new int[][]{{0,1},{0,3},{1,3},{2,0},{2,3}}));
//        System.out.println(transportationHub(new int[][]{{0,3},{1,0},{1,3},{2,0},{3,0},{3,2}}));
//        System.out.println(transportationHub(new int[][]{{2, 5}, {4, 3}, {2, 3}}));
        System.out.println(transportationHub(new int[][]{{0,1},{0,3},{1,3},{2,0},{2,3}}));
    }

    public static int transportationHub(int[][] path) {
        int ans;

        Set<Integer> set = new HashSet<>();
        for (int[] ints : path) {
            for (int i : ints) {
                set.add(i);
            }
        }

        Map<Integer, Set<Integer>> start2end = new HashMap<>();
        for (int[] ints : path) {
            if (start2end.containsKey(ints[0])) {
                start2end.get(ints[0]).add(ints[1]);
            } else {
                Set<Integer> set2 = new HashSet<>();
                set2.add(ints[1]);
                start2end.put(ints[0], set2);
            }
        }

        outer:
        for (Integer hub : set) {
            ans = hub;
            for (Integer i : set) {
                if (i != ans && !start2end.containsKey(i)) {
                    continue outer;
                }
            }
            for (Map.Entry<Integer, Set<Integer>> e : start2end.entrySet()) {
                if (!e.getValue().contains(hub)) {
                    ans = -1;
                    continue outer;
                }
            }
            if (ans != -1 && !start2end.containsKey(ans)) {
                return ans;
            }
        }

        return -1;
    }
}
