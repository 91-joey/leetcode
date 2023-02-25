package _2_algorithm.greedy;

import _3_common.tool.Tools;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 630.课程表 III <br>
 * 开题时间：2023-02-25 11:03:07
 */
public class CourseScheduleIii {
  public static void main(String[] args) {
    Solution solution = new CourseScheduleIii().new Solution();
    System.out.println(solution.scheduleCourse(Tools.to2DIntArray("[[5,15],[3,19],[6,7],[2,10],[5,16],[8,14],[10,11],[2,19]]")));
    // System.out.println(solution.scheduleCourse(Tools.to2DIntArray("[[100,200],[200,1300],[1000,1250],[2000,3200]]")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int scheduleCourseX(int[][] courses) {
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(a -> a[0]).thenComparingInt(a -> a[1]));
      for (int[] course : courses) {
        pq.offer(course);
      }
      
      int ans = 0;
      int cur = 0;
      while (!pq.isEmpty()) {
        int[] course = pq.poll();
        if (cur + course[0] <= course[1]) {
          cur = cur + course[0];
          ans++;
        }
      }
      return ans;
    }
  
    /**
     * ☆☆☆☆☆ 贪心 + 排序 + 优先队列（一句话总结：优先修读更紧急（course[i][1]更小）的课程，选修当前课程赶不上DDL时、替换之前修读课程中持续时间最长的课程）
     * 先按课程的截止时间由小到大排序，再遍历课程，依次考察是否可选：
     *  1）用一个变量维持当前时间，选择后更新当前时间；
     *  2）用一个大根堆维持已被选中的课程，大根堆根据课程的持续时间组织；
     * 课程可选的条件是：
     *  1）当前时间+该课程持续时间 <= 该课程截止时间 ：选修该课程；
     *  2）当前时间+该课程持续时间 > 该课程截止时间 && 该课程持续时间 < 堆顶课程的持续时间 ： 选择该课程，淘汰堆顶课程
     */
    public int scheduleCourse(int[][] courses) {
      Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
      PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
      int cur = 0;
      
      for (int[] course : courses) {
        int dur = course[0];
        int ddl = course[1];
        pq.offer(dur);
        cur += dur;
        if (cur > ddl) {
          cur -= pq.poll();
        }
      }
      
      return pq.size();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}