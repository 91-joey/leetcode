
/**
 * 2379.得到 K 个黑块的最少涂色次数 <br>
 * 开题时间：2023-03-09 09:16:30
 */
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {
  public static void main(String[] args) {
    Solution solution = new MinimumRecolorsToGetKConsecutiveBlackBlocks().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 固长滑窗
    public int minimumRecolors9(String blocks, int k) {
      int ans = 0;
      char[] cs = blocks.toCharArray();
      for (int i = 0; i < k; i++) {
        ans += cs[i] == 'W' ? 1 : 0;
      }
      
      for (int i = k, cur = ans; i < cs.length; i++) {
        cur += cs[i] == 'W' ? 1 : 0;
        cur -= cs[i - k] == 'W' ? 1 : 0;
        ans = Math.min(ans, cur);
      }
      
      return ans;
    }
    
    // ☆☆☆☆☆ 固长滑窗（位运算代替条件语句）
    public int minimumRecolors(String blocks, int k) {
      int cntW = 0; // 窗口中白色块的数量
      char[] cs = blocks.toCharArray();
      // 初始化窗口
      for (int i = 0; i < k; i++) {
        cntW += cs[i] & 1;
      }
      
      int ans = cntW;
      // 滑动窗口
      for (int i = k; i < cs.length; i++) {
        cntW += (cs[i] & 1) - (cs[i - k] & 1);
        ans = Math.min(ans, cntW);
      }
      
      return cntW;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}