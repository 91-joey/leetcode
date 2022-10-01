package org.example.leetcode.problems.algorithm.sort.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Swap {
    public static final int[] arr = {6, 2, 1, 3, 5, 4};
    public static final int[] shuffled = {69, 54, 87, 27, 37, 56, 31, 76, 23, 47, 38, 44, 60, 86, 84, 96, 10, 73, 25, 19, 40, 30, 66, 65, 67, 4, 35, 12, 55, 0, 32, 8, 59, 33, 46, 97, 14, 91, 24, 71, 75, 89, 50, 92, 2, 90, 63, 64, 45, 39, 51, 52, 18, 94, 21, 5, 57, 3, 72, 61, 77, 53, 83, 13, 48, 9, 74, 16, 99, 6, 62, 93, 26, 58, 95, 98, 41, 15, 22, 49, 88, 81, 29, 42, 20, 70, 43, 79, 82, 85, 1, 11, 28, 7, 68, 80, 17, 34, 36, 78};

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        //a^a === 0
        System.out.println(a ^ a);
        //0^b === b
        System.out.println(0 ^ b);

        System.out.println("Initialized:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        swap1(a, b);
        swap2(a, b);
        swap3(a, b);
        swap4(a, b);
        swap5(a, b);
    }

    //交换法一：加法
    public static void swap1(int a, int b) {
        System.out.println("\n交换法一：加法");
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    //交换法二：减法1
    public static void swap2(int a, int b) {
        System.out.println("\n交换法二：减法1");
        a = a - b;
        b = a + b;
        a = b - a;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    //交换法二：减法2
    public static void swap3(int a, int b) {
        System.out.println("\n交换法二：减法2");
        a = b - a;
        b = b - a;
        a = a + b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    //交换法三：位运算（数值不会溢出）
    public static void swap4(int a, int b) {
        System.out.println("\n交换法三：位运算");
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    //交换法四：引入第三变量
    public static void swap5(int a, int b) {
        System.out.println("\n交换法四：引入第三变量");
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
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

    public static void sort(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(arr));
        consumer.accept(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("isSortedNaturally = " + isSortedNaturally(arr));
    }

    public static void sortHard(Consumer<int[]> consumer) {
        System.out.println(Arrays.toString(shuffled));
        consumer.accept(shuffled);
        System.out.println(Arrays.toString(shuffled));
        System.out.println("isSortedNaturally = " + isSortedNaturally(shuffled));
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
