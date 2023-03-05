package _9_contest.week335;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public int passThePillow(int n, int time) {
      int ans = 1;
      int add = 1;
      for (int i = 0; i < time; i++) {
        ans += add;
        if (ans == n + 1) {
          ans = n-1;
          add = -add;
        } else if (ans == 0) {
          ans = 2;
          add = -add;
        }
      }
      return ans;
    }
  }
}
