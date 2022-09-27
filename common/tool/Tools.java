package org.example.leetcode.problems.common.tool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tools {
    public static void main(String[] args) {
        List<Integer> list = toList(new int[]{1, 2, 3});
        System.out.println(list);
        int[] ints = toArray(list);
        System.out.println(Arrays.toString(ints));
    }

    public static List<Integer> toList(int[] ints) {
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    public static int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}
