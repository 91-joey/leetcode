package org.example.leetcode.problems._3_common.tool;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Tools {
    public static final int[] SHUFFLED_ARR_EASY = {6, 2, 1, 3, 5, 4};
    public static final int[] SHUFFLED_ARR_HARD = {69, 54, 87, 27, 37, 56, 31, 76, 23, 47, 38, 44, 60, 86, 84, 96, 10, 73, 25, 19, 40, 30, 66, 65, 67, 4, 35, 12, 55, 0, 32, 8, 59, 33, 46, 97, 14, 91, 24, 71, 75, 89, 50, 92, 2, 90, 63, 64, 45, 39, 51, 52, 18, 94, 21, 5, 57, 3, 72, 61, 77, 53, 83, 13, 48, 9, 74, 16, 99, 6, 62, 93, 26, 58, 95, 98, 41, 15, 22, 49, 88, 81, 29, 42, 20, 70, 43, 79, 82, 85, 1, 11, 28, 7, 68, 80, 17, 34, 36, 78};

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

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isVowelLowerAndUpper(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u',
                    'A', 'E', 'I', 'O', 'U' -> true;
            default -> false;
        };
    }

    public static boolean isVowelLowerOnly(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u' -> true;
            default -> false;
        };
    }

    public static int maxWindow(int[] arr, int k) {
        int sumMax = 0;
        for (int i = 0; i < k; i++)
            sumMax += arr[i];

        for (int i = k, sumCur = sumMax; i < arr.length; i++) {
            sumCur += arr[i] - arr[i - k];
            sumMax = Math.max(sumMax, sumCur);
        }

        return sumMax;
    }

    public static void sort(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(SHUFFLED_ARR_EASY));
        consumer.accept(SHUFFLED_ARR_EASY);
        System.out.println(Arrays.toString(SHUFFLED_ARR_EASY));
        System.out.println("isSortedNaturally = " + isSortedNaturally(SHUFFLED_ARR_EASY));
    }

    public static void sortHard(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(SHUFFLED_ARR_HARD));
        consumer.accept(SHUFFLED_ARR_HARD);
        System.out.println(Arrays.toString(SHUFFLED_ARR_HARD));
        System.out.println("isSortedNaturally = " + isSortedNaturally(SHUFFLED_ARR_HARD));
    }

    public static boolean isSortedNaturally(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }

    public static boolean isSortedNaturally(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++)
            if (list.get(i) > list.get(i + 1))
                return false;
        return true;
    }
}
