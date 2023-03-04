
/**
 * 474.一和零 <br>
 * 开题时间：2023-03-04 18:17:15
 */
public class OnesAndZeroes {
  public static void main(String[] args) {
    Solution solution = new OnesAndZeroes().new Solution();
    System.out.println(solution.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 二维费用 01背包
     * f[i][j][j] = max(f[i-1][j][j], f[i-1][j-cnt0(str[i])][j-cnt1(str[i])]+1)
     */
    public int findMaxForm9(String[] strs, int m, int n) {
      int len = strs.length;
      int[][][] f = new int[len + 1][m + 1][n + 1];
      for (int i = 1; i < len + 1; i++) {
        int cnt0 = (int) strs[i - 1].chars().filter(c -> c == '0').count();
        int cnt1 = strs[i - 1].length() - cnt0;
        for (int j = 0; j < m + 1; j++) {
          for (int k = 0; k < n + 1; k++) {
            f[i][j][k] = f[i - 1][j][k]; // 至少是上一行抄下来
            if (j - cnt0 >= 0 && k - cnt1 >= 0) {
              f[i][j][k] = Math.max(f[i][j][k], f[i - 1][j - cnt0][k - cnt1] + 1);
            }
          }
        }
      }
      return f[len][m][n];
    }
    
    // 二维费用 01背包（空间优化：逆序遍历）
    public int findMaxForm(String[] strs, int m, int n) {
      int[][] f = new int[m + 1][n + 1];
      for (String str : strs) {
        int cnt0 = (int) str.chars().filter(c -> c == '0').count();
        int cnt1 = str.length() - cnt0;
        for (int j = m; j >= cnt0; j--) {
          for (int k = n; k >= cnt1; k--) {
            f[j][k] = Math.max(f[j][k], f[j - cnt0][k - cnt1] + 1);
          }
        }
      }
      return f[m][n];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}