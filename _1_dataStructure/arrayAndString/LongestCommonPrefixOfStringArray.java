package org.example.leetcode.problems._1_dataStructure.arrayAndString;

public class LongestCommonPrefixOfStringArray {
  //    1.自解(横向扫描)
  public String longestCommonPrefix1(String[] strs) {
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      if (prefix.isEmpty()) {
        break;
      }
      int minLength = Math.min(prefix.length(), strs[i].length());
      if (prefix.length() > strs[i].length()) {
        prefix = prefix.substring(0, minLength);
      }
      for (int j = 0; j < minLength; j++) {
        if (prefix.charAt(j) != strs[i].charAt(j)) {
          prefix = prefix.substring(0, j);
          break;
        }
      }
    }
    return prefix;
  }
  
  //    2.横向扫描
  public String longestCommonPrefix2(String[] strs) {
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      if (prefix.isEmpty()) {
        break;
      }
      prefix = longestCommonPrefix(prefix, strs[i]);
    }
    return prefix;
  }
  
  public String longestCommonPrefix(String str1, String str2) {
    int minLength = Math.min(str1.length(), str2.length());
    int i = 0;
    while (i < minLength && str1.charAt(i) == str2.charAt(i)) {
      i++;
    }
    return str1.substring(0, i);
  }
  
  //    3.纵向扫描
  public String longestCommonPrefix3(String[] strs) {
    for (int i = 0; i < strs[0].length(); i++) {
      for (int j = 1; j < strs.length; j++) {
        char c = strs[0].charAt(i);
        if (i == strs[j].length() || strs[j].charAt(i) != c) {
          return strs[0].substring(0, i);
        }
      }
    }
    return strs[0];
  }
  
  public static void main(String[] args) {
    System.out.println(new LongestCommonPrefixOfStringArray().longestCommonPrefix2(new String[]{"abcd", "abc"}));
  }
}
