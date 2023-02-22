//<p>假设你有一个长度为&nbsp;<em><strong>n</strong></em>&nbsp;的数组，初始情况下所有的数字均为&nbsp;<strong>0</strong>，你将会被给出&nbsp;<em><strong>k</strong></em>​​​​​​<em>​</em> 个更新的操作。</p>
//
//<p>其中，每个操作会被表示为一个三元组：<strong>[startIndex, endIndex, inc]</strong>，你需要将子数组&nbsp;<strong>A[startIndex ... endIndex]</strong>（包括 startIndex 和 endIndex）增加&nbsp;<strong>inc</strong>。</p>
//
//<p>请你返回&nbsp;<strong><em>k</em></strong>&nbsp;次操作后的数组。</p>
//
//<p><strong>示例:</strong></p>
//
//<pre><strong>输入: </strong>length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
//<strong>输出: </strong>[-2,0,3,5,3]
//</pre>
//
//<p><strong>解释:</strong></p>
//
//<pre>初始状态:
//[0,0,0,0,0]
//
// 进行了操作 [1,3,2] 后的状态:
//[0,2,2,2,0]
//
// 进行了操作 [2,4,3] 后的状态:
//[0,2,5,5,3]
//
// 进行了操作 [0,2,-2] 后的状态:
//[-2,0,3,5,3]
//</pre>
//
//<div><li>👍 148</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 370.区间加法
// 开题时间：2022-12-20 11:31:26
public class RangeAddition {
  public static void main(String[] args) {
    Solution solution = new RangeAddition().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力
    public int[] getModifiedArray9(int length, int[][] updates) {
      int[] ans = new int[length];
      for (int[] update : updates)
        for (int i = update[0]; i <= update[1]; i++)
          ans[i] += update[2];
      return ans;
    }
    
    // 差分数组
    public int[] getModifiedArray8(int length, int[][] updates) {
      int[] ans = new int[length];
      int[] differ = new int[length + 1];
      
      for (int[] update : updates) {
        differ[update[0]] += update[2];
        differ[update[1] + 1] -= update[2];
      }
      
      ans[0] = differ[0];
      for (int i = 1; i < length; i++)
        ans[i] = ans[i - 1] + differ[i];
      
      return ans;
    }
    
    //☆☆☆☆☆ 差分数组（空间优化）
    public int[] getModifiedArray(int length, int[][] updates) {
      int[] ans = new int[length];
      
      for (int[] update : updates) {
        ans[update[0]] += update[2];
        if (update[1] + 1 < length)
          ans[update[1] + 1] -= update[2];
      }
      
      for (int i = 1; i < length; i++)
        ans[i] += ans[i - 1];
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}