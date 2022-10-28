package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//151. 颠倒字符串中的单词
public class ReverseWords {
//    1.自解  n   n
    public String reverseWords1(String s) {
        StringBuilder ans = new StringBuilder();
        int end = -1;
        char space = ' ';
        for (int i = s.length() - 1; i >= 0; i--) {
            if (end < 0 && s.charAt(i) != space) {
                end = i;
            } else if (end >= 0 && (s.charAt(i) == space || i == 0)) {
                int start = (i == 0 && s.charAt(i) != space) ? i : i + 1;
                ans.append(s, start, end + 1);
                ans.append(space);
                end = -1;
            }
        }
        if (end >= 0) {
            ans.append(s.charAt(end));
            ans.append(space);
        }
        return ans.deleteCharAt(ans.length() - 1).toString();
    }
    //    2.使用语言特性  n   n
    public String reverseWords2(String s) {
        List<String> words = new ArrayList<>(Arrays.asList(s.split(" +")));
        Collections.reverse(words);
        int last = words.size() - 1;
        if ("".equals(words.get(last))) {
            words.remove(last);
        }
        return String.join(" ", words);
    }
    //    3.使用语言特性_优化   n   n
    public String reverseWords3(String s) {
        s=s.trim();
        List<String> words = Arrays.asList(s.split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    public static void main(String[] args) {
//        System.out.println(new ReverseWords().reverseWords("  hello world  "));
//        System.out.println(new ReverseWords().reverseWords("the sky is blue"));
        System.out.println(new ReverseWords().reverseWords2("a good   example"));
        System.out.println(new ReverseWords().reverseWords2(" asdasd df f"));
        System.out.println(new ReverseWords().reverseWords2("  hello world  "));
    }
}
