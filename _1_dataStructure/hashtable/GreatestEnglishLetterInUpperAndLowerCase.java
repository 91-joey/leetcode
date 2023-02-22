package _1_dataStructure.hashtable;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2309.兼具大小写的最好英文字母 <br>
 * 开题时间：2023-01-27 09:56:53
 */
public class GreatestEnglishLetterInUpperAndLowerCase {
  public static void main(String[] args) {
    Solution solution = new GreatestEnglishLetterInUpperAndLowerCase().new Solution();
    System.out.println(solution.greatestLetter("lEeTcOdE"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // hashtable
    public String greatestLetter9(String s) {
      boolean[] exists = new boolean[123];
      for (int i = 0; i < s.length(); i++) {
        exists[s.charAt(i)] = true;
      }
      
      for (int i = 90; i >= 65; i--) {
        if (exists[i] && exists[i + 32]) {
          return String.valueOf((char) i);
        }
      }
      
      return "";
    }
    
    public String greatestLetter(String s) {
      return s.chars()
          .distinct()
          .map(c -> c & 31)
          .boxed()
          .collect(Collectors.groupingBy(Function.identity()
              , () -> new TreeMap<Integer, Long>(Comparator.reverseOrder())
              , Collectors.counting()))
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue() > 1)
          .map(entry -> String.valueOf((char) (entry.getKey() + 64)))
          .findFirst()
          .orElse("");
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}