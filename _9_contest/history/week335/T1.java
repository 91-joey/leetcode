package _9_contest.history.week335;

// 6307. Pass the Pillow
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 模拟
    public int passThePillow9(int n, int time) {
      int ans = 1;
      int add = 1;
      for (int i = 0; i < time; i++) {
        ans += add;
        if (ans == n + 1) {
          ans = n - 1;
          add = -add;
        } else if (ans == 0) {
          ans = 2;
          add = -add;
        }
      }
      return ans;
    }
    
    // 模拟优化：到达队首或队尾，转换方向
    public int passThePillow7(int n, int time) {
      int ans = 1;
      for (int i = 0, add = -1; i < time; i++) {
        if (ans == n || ans == 1) {
          add = -add;
        }
        ans += add;
      }
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 公式
     * 1 -> n -> 1 ，为一个循环
     * 按 time / (n - 1) 的奇偶性分类讨论：
     *  - 偶数：1 -> n，答案为 1 + time % (n - 1)
     *  - 奇数：n -> 1，答案为 n - time % (n - 1)
     */
    public int passThePillow(int n, int time) {
      return time / (n - 1) % 2 == 0 ?
          1 + time % (n - 1) :
          n - time % (n - 1);
    }
  }
}
