package _9_contest.week336;

import java.util.Arrays;

// 6315. Count the Number of Vowel Strings in Range
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // A constant string of vowels
    public static final String VOWELS = "aeiou";
    
    // 遍历 + 字符串查找
    public int vowelStrings9(String[] words, int left, int right) {
      // Initialize a counter variable
      int count = 0;
      
      // Loop through the array from left to right index
      for (int i = left; i <= right; i++) {
        // Get the current word
        String word = words[i];
        
        // Check if it starts and ends with a vowel
        if (VOWELS.indexOf(word.charAt(0)) != -1 &&
            VOWELS.indexOf(word.charAt(word.length() - 1)) != -1) {
          // Increment the counter by one
          count++;
        }
      }
      
      // Return the final answer
      return count;
    }
    
    // 流 + 正则匹配
    public int vowelStrings(String[] words, int left, int right) {
      return (int) Arrays.stream(words, left, right + 1)
          .filter(t -> t.matches("[aeiou](.*[aeiou])?"))
          .count();
    }
  }
}
