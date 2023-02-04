package org.example.leetcode.problems._9_contest.history.lccup22.team;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// LCP 66. 最小展台数量
public class MinNumBooths {
  public static void main(String[] args) {
    System.out.println(minNumBooths(new String[]{"acd", "bed", "accd"}));
    System.out.println(minNumBooths(new String[]{"abc", "ab", "ac", "b"}));
  }
  
  // 元素范围较小时，就不要用哈希表了，直接用数组好了！！
  public static int minNumBooths(String[] demand) {
    Map<Character, Integer> cnts = new HashMap<>();
    Map<Character, Integer> cntsCur = new HashMap<>(36);
    for (String s : demand) {
      for (int i = 0; i < s.length(); i++)
        cntsCur.merge(s.charAt(i), 1, Integer::sum);
      for (Map.Entry<Character, Integer> entry : cntsCur.entrySet()) {
        Character key = entry.getKey();
        Integer valCur = entry.getValue();
        Integer val = cnts.get(key);
        if (val == null || valCur.compareTo(val) > 0)
          cnts.put(key, valCur);
      }
      cntsCur.clear();
    }
    
    int sum = 0;
    for (Integer value : cnts.values())
      sum += value;
    return sum;
  }
  
  public static int minNumBooths2(String[] demand) {
    int start = 97;
    int len = 123;
    int[] cnts = new int[len];
    int[] cntsCur = new int[len];
    for (String s : demand) {
      // 字符计数
      for (int i = 0; i < s.length(); i++)
        cntsCur[s.charAt(i)]++;
      // 每个字符，取最大计数
      for (int i = start; i < len; i++) {
        if (cntsCur[i] > cnts[i])
          cnts[i] = cntsCur[i];
      }
      // 重新初始化计数数组
      Arrays.fill(cntsCur, start, len, 0);
    }
    
    // 求字符数之和
    for (int i = start + 1; i < len; i++)
      cnts[start] += cnts[i];
    return cnts[start];
  }
}
