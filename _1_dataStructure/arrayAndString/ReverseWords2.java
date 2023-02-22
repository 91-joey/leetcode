package _1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 557. 反转字符串中的单词 III
public class ReverseWords2 {
  public static void main(String[] args) {
    System.out.println(new ReverseWords2().reverseWords3("Let's take LeetCode contest"));
  }
  
  //        1.语言特效  n   n
  public String reverseWords1(String s) {
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
  
  //☆☆☆☆☆　分割 + 每个单词反转 + 拼接
  public String reverseWords9(String s) {
    String[] split = s.split(" ");
    for (int i = 0; i < split.length; i++)
      split[i] = new StringBuilder(split[i]).reverse().toString();
    return String.join(" ", split);
  }
  
  // 字符数组遍历 + 区域反转
  public String reverseWords8(String s) {
    char[] cs = s.toCharArray();
    int n = cs.length;
    for (int l = 0; l < n; ) {
      int idx = s.indexOf(" ", l + 1);
      idx = idx < 0 ? n : idx;
      reverse(cs, l, idx - 1);
      l = idx + 1;
    }
    return new String(cs);
  }
  
  // 分割 + 反转（分割数组） + 拼接 + 反转（字符串）
  public String reverseWords7(String s) {
    String[] split = s.split(" ");
    reverse(split, 0, split.length - 1);
    return new StringBuilder(String.join(" ", split))
        .reverse().toString();
  }
  
  // 反转（字符串） +　分割 + 反转（分割数组） + 拼接
  public String reverseWords(String s) {
    String[] split = new StringBuilder(s).reverse().toString().split(" ");
    reverse(split, 0, split.length - 1);
    return String.join(" ", split);
  }
  
  public static void reverse(char[] cs, int l, int r) {
    while (l < r) {
      char tmp = cs[l];
      cs[l++] = cs[r];
      cs[r--] = tmp;
    }
  }
  
  public static <T> void reverse(T[] arr, int l, int r) {
    while (l < r) {
      T tmp = arr[l];
      arr[l++] = arr[r];
      arr[r--] = tmp;
    }
  }
  
  public static <T> void reverse(T[] arr) {
    reverse(arr, 0, arr.length - 1);
  }
}
