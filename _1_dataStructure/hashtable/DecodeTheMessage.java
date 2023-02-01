package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;

/**
 * 2325.解密消息 <br>
 * 开题时间：2023-02-01 09:53:01
 */
public class DecodeTheMessage {
  public static void main(String[] args) {
    Solution solution = new DecodeTheMessage().new Solution();
    System.out.println(solution);
    char[] cs = new char[2];
    System.out.println(Arrays.toString(cs));
    System.out.println((int) cs[0]);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String decodeMessage9(String key, String message) {
      char[] map = new char[26];
      char v = 'a';
      for (int i = 0; v <= 'z'; i++) {
        char c = key.charAt(i);
        if (c != ' ' && map[c - 'a'] == 0) {
          map[c - 'a'] = v;
          v++;
        }
      }
      
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < message.length(); i++) {
        char c = message.charAt(i);
        if (c != ' ') {
          c = map[c - 'a'];
        }
        sb.append(c);
      }
      
      return sb.toString();
    }
    
    // 空格->空格 预先加入哈希表
    public String decodeMessage(String key, String message) {
      char[] map = new char[123];
      map[' '] = ' ';
      char v = 'a';
      for (int i = 0; v <= 'z'; i++) {
        char c = key.charAt(i);
        if (map[c] == 0) {
          map[c] = v++;
        }
      }
      
      return message.chars()
          .map(k -> map[k])
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}