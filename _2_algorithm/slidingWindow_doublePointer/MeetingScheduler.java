//<p>给定两个人的空闲时间表：<code>slots1</code> 和 <code>slots2</code>，以及会议的预计持续时间&nbsp;<code>duration</code>，请你为他们安排&nbsp;<strong>时间段最早&nbsp;且</strong>合适的会议时间。</p>
//
//<p>如果没有满足要求的会议时间，就请返回一个 <strong>空数组</strong>。</p>
//
//<p>「空闲时间」的格式是&nbsp;<code>[start, end]</code>，由开始时间&nbsp;<code>start</code>&nbsp;和结束时间&nbsp;<code>end</code>&nbsp;组成，表示从&nbsp;<code>start</code>&nbsp;开始，到 <code>end</code>&nbsp;结束。&nbsp;</p>
//
//<p>题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间&nbsp;<code>[start1, end1]</code>&nbsp;和&nbsp;<code>[start2, end2]</code>，要么&nbsp;<code>start1 &gt; end2</code>，要么&nbsp;<code>start2 &gt; end1</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
//<strong>输出：</strong>[60,68]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
//<strong>输出：</strong>[]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= slots1.length, slots2.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>slots1[i].length, slots2[i].length == 2</code></li> 
// <li><code>slots1[i][0] &lt; slots1[i][1]</code></li> 
// <li><code>slots2[i][0] &lt; slots2[i][1]</code></li> 
// <li><code>0 &lt;= slots1[i][j], slots2[i][j] &lt;= 10<sup>9</sup></code></li> 
// <li><code>1 &lt;= duration &lt;= 10<sup>6</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 60</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.stream.Stream;

// 1229.安排会议日程
// 开题时间：2022-10-28 09:26:17
public class MeetingScheduler {
  public static void main(String[] args) {
    Solution solution = new MeetingScheduler().new Solution();
    //        System.out.println(solution.minAvailableDuration(new int[][]{{10, 50}, {60, 120}, {140, 210}}, new int[][]{{0, 15}, {60, 70}}, 8));
    System.out.println(solution.minAvailableDuration(new int[][]{{0, 1}, {100, 1000100}}, new int[][]{{90, 1000100}, {0, 2}}, 1000000));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 时间段最早 -> 按「空闲时间」起始排序，正序遍历
     * 合适的会议：
     *  - 空闲时间 a 和 空闲时间 b 相交
     *  - 相交区间 >= target
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
      ArrayList<Integer> ans = new ArrayList<>();
      
      ArrayList<int[]> list1 = new ArrayList<>();
      for (int[] dur : slots1)
        if (dur[1] - dur[0] >= duration)
          list1.add(dur);
      list1.sort(Comparator.comparingInt(o -> o[0]));
      
      ArrayList<int[]> list2 = new ArrayList<>();
      for (int[] dur : slots2)
        if (dur[1] - dur[0] >= duration)
          list2.add(dur);
      list2.sort(Comparator.comparingInt(o -> o[0]));
      
      int len1 = list1.size();
      int len2 = list2.size();
      if (list1.isEmpty() || list2.isEmpty() ||
          list1.get(len1 - 1)[1] - list2.get(0)[0] < duration ||
          list2.get(len2 - 1)[1] - list1.get(0)[0] < duration
      )
        return ans;
      
      for (int i = 0, j = 0; i < len1 && j < len2; ) {
        if (list1.get(i)[1] - list2.get(j)[0] < duration)
          i++;
        else if (list2.get(j)[1] - list1.get(i)[0] < duration)
          j++;
        else {
          int start = Math.max(list1.get(i)[0], list2.get(j)[0]);
          ans.add(start);
          ans.add(ans.get(0) + duration);
          return ans;
        }
      }
      
      return ans;
    }
    
    public List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
      ArrayList<Integer> ans = new ArrayList<>();
      
      PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
      for (int[] dur : slots1)
        if (dur[1] - dur[0] >= duration)
          q.offer(dur);
      for (int[] dur : slots2)
        if (dur[1] - dur[0] >= duration)
          q.offer(dur);
      
      while (q.size() >= 2) {
        int[] l = q.poll();
        int[] r = q.peek();
        if (l[1] - r[0] >= duration) {
          ans.add(r[0]);
          ans.add(r[0] + duration);
          return ans;
        }
      }
      
      return ans;
    }
    
    public List<Integer> minAvailableDuration3(int[][] slots1, int[][] slots2, int duration) {
      ArrayList<int[]> list = new ArrayList<>();
      for (int[] dur : slots1)
        if (dur[1] - dur[0] >= duration)
          list.add(dur);
      for (int[] dur : slots2)
        if (dur[1] - dur[0] >= duration)
          list.add(dur);
      list.sort(Comparator.comparingInt(o -> o[0]));
      
      for (int i = 0; i < list.size() - 1; i++) {
        int[] l = list.get(i);
        int[] r = list.get(i + 1);
        if (l[1] - r[0] >= duration)
          return List.of(r[0], r[0] + duration);
      }
      
      return new ArrayList<>();
    }
    
    public List<Integer> minAvailableDuration4(int[][] slots1, int[][] slots2, int duration) {
      Predicate<int[]> predicate = dur -> dur[1] - dur[0] >= duration;
      int[][] slots = Stream.concat(Arrays.stream(slots1).filter(predicate), Arrays.stream(slots2).filter(predicate)).
          sorted(Comparator.comparingInt(o -> o[0])).
          toArray(int[][]::new);
      
      for (int i = 0; i < slots.length - 1; i++)
        if (slots[i][1] - slots[i + 1][0] >= duration)
          return List.of(slots[i + 1][0], slots[i + 1][0] + duration);
      
      return new ArrayList<>();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}