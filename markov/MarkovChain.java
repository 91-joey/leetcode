package org.example.leetcode.problems.markov;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//1024小游戏
public class MarkovChain {

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(2, 9, 1, 26, 22, 33, 29, 2);
        List<Character> operators = Arrays.asList(MOD, ADD, AND, MOD, ADD, MULTIPLY);
        max1024s(nums, operators);
        System.out.println(8 * 7 * 6 * 5 * 6 * 5 * 4);
        //1400=7*5*8*5
    }

    public static final char ADD = '+';
    public static final char MINUS = '-';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';
    public static final char MOD = '%';

    public static final char EXPO = '~';

    public static final char OR = '|';
    public static final char AND = '&';
    public static final char XOR = '^';
    public static final char LEFT = '<';
    public static final char RIGHT = '>';

    public static String[] max1024s(List<Integer> nums, List<Character> operators) {
        int m = nums.size();
        int n = operators.size();
        int cnt = Math.min(m / 4, n / 3);
        int total = 0;
//        for (int ordinal = 0; ordinal < cnt; ordinal++) {
        for (int i1 = 0; i1 < m; i1++) {
            for (int i2 = 0; i2 < m; i2++) {
                if (i2 == i1) continue;
                for (int i3 = 0; i3 < m; i3++) {
                    if (i3 == i2 || i3 == i1) continue;
                    for (int i4 = 0; i4 < m; i4++) {
                        if (i4 == i1 || i4 == i2 || i4 == i3) continue;

                        for (int j1 = 0; j1 < n; j1++) {
                            for (int j2 = 0; j2 < n; j2++) {
                                if (j2 == j1) continue;
                                for (int j3 = 0; j3 < n; j3++) {
                                    if (j3 == j2 || j3 == j1) continue;
                                    Integer num1 = nums.get(i1);
                                    Integer num2 = nums.get(i2);
                                    Integer num3 = nums.get(i3);
                                    Integer num4 = nums.get(i4);
                                    Character op1 = operators.get(j1);
                                    Character op2 = operators.get(j2);
                                    Character op3 = operators.get(j3);
                                    int result = getResult(op1, num1, num2);
                                    result = getResult(op2, result, num3);
                                    result = getResult(op3, result, num4);
                                    if (result == 1024)
                                        System.out.printf("%d %s %d %s %d %s %d = %d\n", num1, op1, num2, op2, num3, op3, num4, result);
                                    total++;
                                }
                            }
                        }

                    }
                }
            }
        }
//        }

        System.out.println("total = " + total);

//        for (int i = 0; i < m - 1; i++) {
//            for (int j = i + 1; j < m; j++) {
//                for (int k = 0; k < n; k++) {
//                    //todo 运算结果的取值范围为 32 位有符号整数，超过此范围将提示报错。
//                    int result = getResult(operators.get(k), nums.get(i), nums.get(j));
//
//                    LinkedList<Integer> numsNew = new LinkedList<>(nums);
//                    numsNew.remove(i);
//                    numsNew.remove(j);
//                    LinkedList<Character> operatorsNew = new LinkedList<>(operators);
//                    operatorsNew.remove(k);
//                    result = calculate(numsNew, operatorsNew, result);
//                }
//            }
//        }

        return new String[]{};
    }

    private static int calculate(LinkedList<Integer> nums, LinkedList<Character> operators, int result) {
        int m = nums.size();
        int n = operators.size();
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                result = getResult(operators.get(k), result, nums.get(i));

                LinkedList<Integer> numsNew = new LinkedList<>(nums);
                numsNew.remove(i);
                LinkedList<Character> operatorsNew = new LinkedList<>(operators);
                operatorsNew.remove(k);
                result = calculate(numsNew, operatorsNew, result);

            }
        }

        return -1;
    }

    private static int getResult(Character c, int a, int b) {
        return switch (c) {
            case ADD -> a + b;
            case MINUS -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
            case MOD -> a % b;
            case EXPO -> (int) Math.pow(a, b);
            case OR -> a | b;
            case AND -> a & b;
            case XOR -> a ^ b;
            case LEFT -> a << b;
            case RIGHT -> a >> b;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
