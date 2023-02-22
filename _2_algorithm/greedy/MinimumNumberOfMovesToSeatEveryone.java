//<p>一个房间里有 <code>n</code>&nbsp;个座位和 <code>n</code>&nbsp;名学生，房间用一个数轴表示。给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>seats</code>&nbsp;，其中&nbsp;<code>seats[i]</code> 是第 <code>i</code>&nbsp;个座位的位置。同时给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>students</code>&nbsp;，其中&nbsp;<code>students[j]</code>&nbsp;是第 <code>j</code>&nbsp;位学生的位置。</p>
//
//<p>你可以执行以下操作任意次：</p>
//
//<ul> 
// <li>增加或者减少第&nbsp;<code>i</code>&nbsp;位学生的位置，每次变化量为 <code>1</code>&nbsp;（也就是将第 <code>i</code>&nbsp;位学生从位置 <code>x</code>&nbsp;移动到 <code>x + 1</code>&nbsp;或者 <code>x - 1</code>）</li> 
//</ul>
//
//<p>请你返回使所有学生都有座位坐的 <strong>最少移动次数</strong>&nbsp;，并确保没有两位学生的座位相同。</p>
//
//<p>请注意，初始时有可能有多个座位或者多位学生在 <strong>同一</strong>&nbsp;位置。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>seats = [3,1,5], students = [2,7,4]
//<b>输出：</b>4
//<b>解释：</b>学生移动方式如下：
//- 第一位学生从位置 2 移动到位置 1 ，移动 1 次。
//- 第二位学生从位置 7 移动到位置 5 ，移动 2 次。
//- 第三位学生从位置 4 移动到位置 3 ，移动 1 次。
// 总共 1 + 2 + 1 = 4 次移动。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>seats = [4,1,5,9], students = [1,3,2,6]
//<b>输出：</b>7
//<strong>解释：</strong>学生移动方式如下：
//- 第一位学生不移动。
//- 第二位学生从位置 3 移动到位置 4 ，移动 1 次。
//- 第三位学生从位置 2 移动到位置 5 ，移动 3 次。
//- 第四位学生从位置 6 移动到位置 9 ，移动 3 次。
// 总共 0 + 1 + 3 + 3 = 7 次移动。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>seats = [2,2,6,6], students = [1,3,2,6]
//<b>输出：</b>4
//<b>解释：</b>学生移动方式如下：
//- 第一位学生从位置 1 移动到位置 2 ，移动 1 次。
//- 第二位学生从位置 3 移动到位置 6 ，移动 3 次。
//- 第三位学生不移动。
//- 第四位学生不移动。
// 总共 1 + 3 + 0 + 0 = 4 次移动。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == seats.length == students.length</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= seats[i], students[j] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 26</li><li>👎 0</li></div>
package _2_algorithm.greedy;


import java.util.Arrays;
import java.util.PrimitiveIterator;
import java.util.TreeMap;

// 2037.使每位学生都有座位的最少移动次数
// 开题时间：2022-12-31 09:07:18
public class MinimumNumberOfMovesToSeatEveryone {
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfMovesToSeatEveryone().new Solution();
    System.out.println(solution.minMovesToSeat(new int[]{3, 1, 5}, new int[]{2, 7, 4}));
    //        System.out.println(solution.minMovesToSeat(new int[]{3, 20, 17, 2, 12, 15, 17, 4, 15, 20}, new int[]{10, 13, 14, 15, 5, 2, 3, 14, 3, 18}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  //    import java.util.*;
  class Solution {
    public int minMovesToSeatX(int[] seats, int[] students) {
      //            Map<Integer, Long> val2cnt = Arrays.stream(seats).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      TreeMap<Integer, Integer> val2cnt = new TreeMap<>();
      for (int x : seats)
        val2cnt.merge(x, 1, Integer::sum);
      int ans = 0;
      for (int x : students) {
        Integer high = val2cnt.ceilingKey(x);
        Integer low = val2cnt.floorKey(x);
        int seat = high == null || (low != null && x - low >= high - x) ? low : high;
        if (val2cnt.get(seat) == 1)
          val2cnt.remove(seat);
        else
          val2cnt.merge(seat, -1, Integer::sum);
        ans += Math.abs(x - seat);
      }
      return ans;
    }
    
    //☆☆☆☆☆ 排序+贪心
    public int minMovesToSeat9(int[] seats, int[] students) {
      Arrays.sort(seats);
      Arrays.sort(students);
      int ans = 0;
      for (int i = 0; i < seats.length; i++)
        ans += Math.abs(seats[i] - students[i]);
      return ans;
    }
    
    public int minMovesToSeat(int[] seats, int[] students) {
      PrimitiveIterator.OfInt iterator = Arrays.stream(students).sorted().iterator();
      return Arrays.stream(seats).sorted().map(x -> Math.abs(x - iterator.nextInt())).sum();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}