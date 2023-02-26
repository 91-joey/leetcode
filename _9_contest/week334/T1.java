package _9_contest.week334;

// 6369. Left and Right Sum Differences
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 暴力
    public int[] leftRigthDifference9(int[] nums) {
      int n = nums.length;
      int[] ans = new int[n];
      for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = 0; j < i; j++) {
          sum += nums[j];
        }
        for (int j = i + 1; j < n; j++) {
          sum -= nums[j];
        }
        ans[i] = Math.abs(sum);
      }
      return ans;
    }
    
    // 前后缀和
    public int[] leftRigthDifference8(int[] nums) {
      int n = nums.length;
      int sum = 0;
      for (int num : nums) {
        sum -= num;
      }
      
      int[] ans = new int[n];
      for (int i = 0; i < n; i++) {
        sum += nums[i] + (i == 0 ? 0 : nums[i - 1]);
        ans[i] = Math.abs(sum);
      }
      
      return ans;
    }
  
  
    // 前后缀和（再优化）
    public int[] leftRigthDifference(int[] nums) {
      int n = nums.length;
      int sum = 0;
      for (int i = 1; i < n; i++) {
        sum += nums[i];
      }
      
      int[] ans = new int[n];
      ans[0] = sum;
      sum = -sum;
      
      for (int i = 1; i < n; i++) {
        ans[i] = Math.abs((sum += nums[i] + nums[i - 1]));
      }
      
      return ans;
    }
  }
}
