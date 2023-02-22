package _2_algorithm.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 135.分发糖果 <br>
 * 开题时间：2023-01-28 11:48:39
 */
public class Candy {
  public static void main(String[] args) {
    Solution solution = new Candy().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int candyX(int[] ratings) {
      int n = ratings.length;
      int[] ans = new int[n];
      
      int min = Arrays.stream(ratings).min().getAsInt();
      Queue<Integer> q = new LinkedList<>();
      for (int i = 0; i < n; i++) {
        if (ratings[i] == min) {
          ans[i] = 1;
          q.offer(i);
        }
      }
      
      while (!q.isEmpty()) {
        int i = q.poll();
        for (int j = -1; j <= 1; j++) {
          if (j == 0) {
            continue;
          }
          int ni = i + j;
          if (0 <= ni && ni < n && ans[ni] == 0) {
            ans[ni] = ans[i] + 1;
            q.offer(ni);
          }
        }
      }
      
      return Arrays.stream(ans).sum();
    }
    
    /*
     * ☆☆☆☆☆ 贪心
     * 我们可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
     *  左规则：当 ratings[i−1]<ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
     *  右规则：当 ratings[i]>ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多。
     * 我们遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。每个人最终分得的糖果数量即为这两个数量的最大值。
     */
    public int candy9(int[] ratings) {
      int n = ratings.length;
      int[] l2r = new int[n];
      Arrays.fill(l2r, 1);
      for (int i = 1; i < n; i++) {
        if (ratings[i - 1] < ratings[i]) {
          l2r[i] = l2r[i - 1] + 1;
        }
      }
      
      int[] r2l = new int[n];
      Arrays.fill(r2l, 1);
      for (int i = n - 2; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
          r2l[i] = r2l[i + 1] + 1;
        }
      }
      
      int ans = 0;
      for (int i = 0; i < n; i++) {
        ans += Math.max(l2r[i], r2l[i]);
      }
      return ans;
    }
    
    // 贪心（精简版）
    public int candy(int[] ratings) {
      int n = ratings.length;
      int[] l2r = new int[n];
      for (int i = 1; i < n; i++) {
        if (ratings[i - 1] < ratings[i]) {
          l2r[i] = l2r[i - 1] + 1;
        }
      }
      
      int ans = n + l2r[n - 1];
      for (int i = n - 2, r2l = 0; i >= 0; i--) {
        if (ratings[i] > ratings[i + 1]) {
          r2l++;
        } else {
          r2l = 0;
        }
        ans += Math.max(l2r[i], r2l);
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}