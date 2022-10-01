package org.example.leetcode.problems.algorithm.sort.algorithm.nlogn;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Quick {
    public static void main(String[] args) {
        Swap.sort(Quick::quickSort);
//        quickSort(new int[]{1,2,3,4});
//        quickSort(new int[]{4,3,2,1});
    }

    public static void quickSort(int[] arr) {

        //region 分区优化2（排序前先「洗牌」）
        /*
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        System.arraycopy(list.stream().mapToInt(Integer::valueOf).toArray(), 0, arr, 0, arr.length);
        */
        //endregion

        //region 分区优化3（排序前先查验有序性（顺序 -> 直接返回; 逆序 -> 倒序返回））
        boolean naturalSorted = true;
        boolean reverseSorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < arr[i + 1]) reverseSorted = false;
            else if (arr[i] > arr[i + 1]) naturalSorted = false;
        }
        if (naturalSorted) return;
        if (reverseSorted) {
            List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
            Collections.reverse(list);
            System.arraycopy(list.stream().mapToInt(Integer::valueOf).toArray(), 0, arr, 0, arr.length);
            return;
        }
        //endregion

        helper(arr, 0, arr.length - 1);
    }

    private static void helper(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition2(arr, start, end);
        helper(arr, start, mid - 1);
        helper(arr, mid + 1, end);
    }

    //简单分区
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int l = start + 1;
        int r = end;
        while (l < r) {
            if (arr[l] > pivot) Swap.swap(arr, l, r--);
            else l++;
        }
        if (l == r && arr[l] > pivot) r--;
        Swap.swap(arr, start, r);
        return r;
    }

    //双指针分区
    private static int partition2(int[] arr, int start, int end) {
        //region 分区优化1（随机选择基数）
        int rndIdx = new Random().nextInt(start, end + 1);
        Swap.swap(arr, start, rndIdx);
        //endregion
        int pivot = arr[start];
        int l = start + 1;
        int r = end;
        while (l < r) {
            if (arr[l] > pivot) {
                while (l < r && arr[r] > pivot) r--;
                if (l != r) Swap.swap(arr, l++, r--);
            } else {
                l++;
            }
        }
        if (l == r && arr[l] > pivot) r--;
        Swap.swap(arr, start, r);
        return r;
    }
}
