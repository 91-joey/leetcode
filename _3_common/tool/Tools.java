package org.example.leetcode.problems._3_common.tool;

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
        return Arrays.stream(ints).boxed().toList();
    }

    public static int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public static int maxWindow(int[] arr,int k){
        int sumMax = 0;
        for (int i = 0; i < k; i++)
            sumMax += arr[i];

        for (int i = k, sumCur = sumMax; i < arr.length; i++) {
            sumCur += arr[i] - arr[i - k];
            sumMax = Math.max(sumMax, sumCur);
        }

        return sumMax;
    } 
}
