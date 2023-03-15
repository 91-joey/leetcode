package _9_contest.week336;

import java.util.Arrays;
import java.util.Comparator;

// 6318. Minimum Time to Complete All Tasks
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 排序 + 贪心 + 暴力（按右端点自然排序，贪后缀）
    public int findMinimumTime9(int[][] tasks) {
      // 按右端点排序
      Arrays.sort(tasks, Comparator.comparingInt(o -> o[1]));
      // 某个整数时间点的电脑状态：运行（true）、关闭（false）
      boolean[] run = new boolean[2001];
      int ans = 0;
      for (int[] task : tasks) {
        int start = task[0];
        int end = task[1];
        int duration = task[2];
        
        // 根据区间内的已有的电脑运行时间点，求待新增时间点
        for (int i = start; i <= end; i++) {
          if (run[i]) {
            duration--;
          }
        }
        // 贪心：尽量把新增的时间点安排在区间 [start,end] 的后缀上，这样下一个区间就能统计到更多已有的时间点。
        for (int i = end; duration > 0; i--) {
          if (!run[i]) {
            run[i] = true;
            duration--;
            ans++;
          }
        }
      }
      
      return ans;
    }
  
    // 排序 + 贪心 + 暴力（按左端点逆序排序，贪前缀）
    public int findMinimumTime(int[][] tasks) {
      Arrays.sort(tasks, Comparator.<int[]>comparingInt(o -> o[0]).reversed());
      boolean[] run = new boolean[2001];
      int ans = 0;
      for (int[] task : tasks) {
        int start = task[0];
        int end = task[1];
        int duration = task[2];
        for (int i = start; i <= end; i++) {
          if (run[i]) {
            duration--;
          }
        }
        for (int i = start; duration > 0; i++) {
          if (!run[i]) {
            run[i] = true;
            duration--;
            ans++;
          }
        }
      }
      return ans;
    }
    
    // todo 线段树
  }
}
