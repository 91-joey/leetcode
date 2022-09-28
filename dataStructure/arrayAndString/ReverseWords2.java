package org.example.leetcode.problems.dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//557. 反转字符串中的单词 III
public class ReverseWords2 {
//        1.语言特效  n   n
    public String reverseWords(String s) {
        int length = s.length();
        List<Character> chars = new ArrayList<>(length);
        for (char c : s.toCharArray()) {
            chars.add(c);
        }
        Collections.reverse(chars);
        StringBuilder ans = new StringBuilder(length);
        for (Character aChar : chars) {
            ans.append(aChar);
        }

        List<String> strings = Arrays.asList(ans.toString().split("\\s+"));
        Collections.reverse(strings);
        return String.join(" ", strings);
    }

//        2.字符数组  n   n
    public String reverseWords2(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int l = 0;
        for (int idx = 0; idx <= length; idx++) {
            if (idx == length || chars[idx] == ' ') {
                int r = idx - 1;
                while (l < r) {
                    char tmp = chars[l];
                    chars[l++] = chars[r];
                    chars[r--] = tmp;
                }
                l = idx + 1;
            }
        }
        return new String(chars);
    }

    //    3.字符串拼接  n   n
    public String reverseWords3(String s) {
        int length = s.length();
        StringBuilder ans = new StringBuilder(length + 1);
        int l = 0;
        char space = ' ';
        for (int idx = 0; idx <= length; idx++) {
            if (idx == length || s.charAt(idx) == space) {
                for (int i = idx - 1; i >= l; i--) {
                    ans.append(s.charAt(i));
                }
                ans.append(space);
                l = idx + 1;
            }
        }
        return ans.substring(0, length);
    }

    public static void main(String[] args) {
        System.out.println(new ReverseWords2().reverseWords3("Let's take LeetCode contest"));
    }
}
