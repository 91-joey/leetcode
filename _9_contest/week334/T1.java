package _9_contest.week334;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    
    public int[] leftRigthDifference(int[] nums) {
      int n = nums.length;
      int[] ans = new int[n];
      for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = 0; j < i; j++) {
          sum += nums[j];
        }
        for (int j = i+1; j < n; j++) {
          sum-= nums[j];
        }
        ans[i] = Math.abs(sum);
      }
      return ans;
    }
  }
}
