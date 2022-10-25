package org.example.leetcode.problems.markov;

import java.util.*;

//1024小游戏
public class MarkovChain {

    public static void main(String[] args) {
        int[] allNums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 955, 965, 996, 1075, 1024, 1337};
        String[] allOperators = {"+", "-", "*", "//", "%", "**", ">>", "<<", "^", "&", "|"};

        List<Integer> nums = Arrays.asList(955, 1075, 1337,7, 24, 35, 2, 35, 1024, 2, 28, 7, 34, 2, 1024, 3, 32, 7, 32, 2, 2, 1024, 996, 2, 1, 18, 2, 9, 1, 26, 22, 33, 29, 2);
        String operators[] = {"^", "|", ">>", "^", "<<", "|", ">>", "<<", "<<", "|", "|", "**", "&", "&", "&", "-", "%", "+", "&", "%", "+", "*"};

        List<Integer> numsXiaohao = Arrays.asList(27, 31, 4, 2, 16, 30, 955, 1075, 1337, 14, 2, 26, 23, 33, 0, 1024, 7, 2, 0, 1024);
        String operatorsXiaohao[] = {"//"};

        System.out.println("allNums.length = " + allNums.length);
        System.out.println("allOperators.length = " + allOperators.length);
        HashSet<Integer> setNums = new HashSet<>(nums);
        HashSet<String> setOperators = new HashSet<>(Arrays.asList(operators));
        HashSet<Integer> setNumsXiaohao = new HashSet<>(numsXiaohao);
        HashSet<String> setOperatorsXiaohao = new HashSet<>(Arrays.asList(operatorsXiaohao));
        int[] numsWaitedToBeCollected = Arrays.stream(allNums).filter(value -> !setNums.contains(value)).toArray();
        String[] operatorsWaitedToBeCollected = Arrays.stream(allOperators).filter(value -> !setOperators.contains(value)).toArray(String[]::new);
        int lenAll = numsWaitedToBeCollected.length + operatorsWaitedToBeCollected.length;
        System.out.println("集齐还需要 " + lenAll + "张卡，最多 " + (lenAll + 2) / 3 + " 天");
        System.out.println("numsWaitedToBeCollected = " + Arrays.toString(numsWaitedToBeCollected));
        System.out.println("operatorsWaitedToBeCollected = " + Arrays.toString(operatorsWaitedToBeCollected));
        int[] numsAbleToGetFromXiaohao = Arrays.stream(numsWaitedToBeCollected).filter(setNumsXiaohao::contains).toArray();
        System.out.println("numsAbleToGetFromXiaohao = " + Arrays.toString(numsAbleToGetFromXiaohao));
        int[] numsDisableToGetFromXiaohao = Arrays.stream(numsWaitedToBeCollected).filter(value -> !setNumsXiaohao.contains(value)).toArray();
        System.out.println("numsDisableToGetFromXiaohao = " + Arrays.toString(numsDisableToGetFromXiaohao));
        String[] operatorsAbleToGetFromXiaohao = Arrays.stream(allOperators).filter(setOperatorsXiaohao::contains).toArray(String[]::new);
        System.out.println("operatorsAbleToGetFromXiaohao = " + Arrays.toString(operatorsAbleToGetFromXiaohao));


        var num2cnt = new HashMap<Integer, Integer>();
        nums.forEach(val -> num2cnt.merge(val, 1, Integer::sum));
        num2cnt.forEach((k, v) -> System.out.println(k + " : " + v + "张"));
//        List<Character> operators = Arrays.asList(OR, EXPO, AND, AND, AND, MINUS, MOD, ADD, AND, MOD, ADD, MULTIPLY);
        int m = nums.size();
        int n = operators.length;
        limit = Math.min(m / 4, n / 3);
        int numsNeeded = ++limit * 4 - m;
        int operatorsNeeded = limit * 3 - n;
        System.out.println("m = " + m);
        System.out.println("n = " + n);
        System.out.println("需要数字牌： " + numsNeeded + "张");
        System.out.println("需要符号牌： " + operatorsNeeded + "张");
//        max1024s(nums, operators);

        /*
        26 & 22 & 9 | 1024
        18 & 1 + 32 * 32
        996 | 29 % 1 + 3
        2 % 2 - 33 ** 2
        */
        TreeMap<Integer, Integer> val2cnt = new TreeMap<>();
        nums.forEach(integer -> val2cnt.merge(integer, 1, Integer::sum));
        TreeMap<String, Integer> op2cnt = new TreeMap<>();
        Arrays.asList(operators).forEach(s -> op2cnt.merge(s, 1, Integer::sum));
        String plans = "        26 & 22 & 9 | 1024\n" +
                "        18 & 1 + 32 * 32\n" +
                "        996 | 29 % 1 + 3\n" +
                "        7 & 2 << 2 << 7\n" +
//                "        34 + 2 >> 7 | 1024\n" +
                "        34 << 2 >> 28 | 1024\n" +
                "        2 ^ 2 >> 35 | 1024\n" +
                "        2 % 2 - 33 ** 2";
        String[] split = plans.split("\n+");
        for (String s : split) {
            String[] strings = s.stripLeading().split("\s+");
            for (int i = 0; i < strings.length; i++) {
                if ((i & 1) == 0) {
                    Integer integer = Integer.valueOf(strings[i]);
                    val2cnt.compute(integer, (k, v) -> v - 1);
                } else
                    op2cnt.compute(strings[i], (k, v) -> v - 1);
            }
        }
        System.out.println("\n手里可用的牌：");
        remove(val2cnt).forEach((k, v) -> System.out.println(k + " : " + v + "张"));
        remove(op2cnt).forEach((k, v) -> System.out.println(k + " : " + v + "张"));

        System.out.println(((2 ^ 2) >> 35) | 1024);

    }

    private static <T> Map<T, Integer> remove(Map<T, Integer> map) {
        Map<T, Integer> ret = new TreeMap<>();
        for (Map.Entry<T, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value != 0) {
                ret.put(e.getKey(), value);
            }
        }
        return ret;
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


    public static void max1024s2(List<Integer> nums, List<Character> operators) {
        int m = nums.size();
        int n = operators.size();
        limit = Math.min(m / 4, n / 3) * 7;
        dfs(nums, operators, 0);
    }

    List<String> res = new ArrayList<>();
    static int limit;

    private static void dfs(List<Integer> nums, List<Character> operators, int i) {
        //新一轮计算
        if (i % 7 == 0) {

            //数字
        } else if (i % 2 == 0) {

            //运算符
        } else {

        }
    }

    public static void max1024s(List<Integer> nums, List<Character> operators) {
        int m = nums.size();
        int n = operators.size();
        int cnt = Math.min(m / 4, n / 3);
        int success = 0;
        ArrayList<String> plans = new ArrayList<>();
        for (int ordinal = 0; ordinal < cnt; ordinal++) {
            List<Integer> copy = new ArrayList<>(nums);
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
                                        Integer num1 = copy.get(i1);
                                        Integer num2 = copy.get(i2);
                                        Integer num3 = copy.get(i3);
                                        Integer num4 = copy.get(i4);
                                        Character op1 = operators.get(j1);
                                        Character op2 = operators.get(j2);
                                        Character op3 = operators.get(j3);
                                        int result = getResult(op1, num1, num2);
                                        result = getResult(op2, result, num3);
                                        result = getResult(op3, result, num4);
                                        if (result == 1024) {
                                            String format = String.format("%d %s %d %s %d %s %d = %d", num1, op1, num2, op2, num3, op3, num4, result);
                                            System.out.println(format);
//                                            plans.add(format);
//                                            if (++success == cnt) {
//                                                plans.forEach(System.out::println);
//                                                return;
//                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }


//        for (int i = 0; i < m - 1; i++) {
//            for (int j = i + 1; j < m; j++) {
//                for (int k = 0; k < n; k++) {
//                    //todo 运算结果的取值范围为 32 位有符号整数，超过此范围将提示报错。
//                    int result = getResult(operators.get(k), copy.get(i), copy.get(j));
//
//                    LinkedList<Integer> numsNew = new LinkedList<>(copy);
//                    numsNew.remove(i);
//                    numsNew.remove(j);
//                    LinkedList<Character> operatorsNew = new LinkedList<>(operators);
//                    operatorsNew.remove(k);
//                    result = calculate(numsNew, operatorsNew, result);
//                }
//            }
//        }
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
