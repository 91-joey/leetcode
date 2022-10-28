package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//412. Fizz Buzz
public class FizzBuzz {
    //    1.模拟法
    public List<String> fizzBuzz1(int n) {
        List<String> list = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                if (i % 5 == 0) {
                    list.add("FizzBuzz");
                } else {
                    list.add("Fizz");
                }
            } else {
                if (i % 5 == 0) {
                    list.add("Buzz");
                } else {
                    list.add(String.valueOf(i));
                }
            }
        }

        return list;
    }

    //    2.字符串拼接
    public List<String> fizzBuzz2(int n) {
        List<String> list = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            String s = "";
            if (i % 3 == 0) s += "Fizz";
            if (i % 5 == 0) s += "Buzz";
            if (s.equals("")) s += i;
            list.add(s);
        }

        return list;
    }

    //    3.字符串拼接 + 哈希表
    public List<String> fizzBuzz3(int n) {
        List<String> list = new ArrayList<>(n);

        Map<Integer, String> map = new HashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");

        for (int i = 1; i <= n; i++) {
            String s = "";
            for (Integer num : map.keySet()) {
                if (i % num == 0) s += map.get(num);
            }
            if (s.equals("")) s += i;
            list.add(s);
        }

        return list;
    }
}
