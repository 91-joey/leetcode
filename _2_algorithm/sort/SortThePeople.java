package _2_algorithm.sort;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * 2418.按身高排序 <br>
 * 开题时间：2023-02-12 08:50:21
 */
public class SortThePeople {
  public static void main(String[] args) {
    Solution solution = new SortThePeople().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
      return Stream.iterate(0, i -> i + 1)
          .limit(names.length)
          .sorted(Comparator.<Integer>comparingInt(i -> heights[i]).reversed())
          .map(i -> names[i])
          .toArray(String[]::new);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}