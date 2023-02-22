//<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你统计并返回 <code>nums</code> 的 <strong>子数组</strong> 中满足 <em>元素最小公倍数为 <code>k</code> </em>的子数组数目。</p>
//
//<p><strong>子数组</strong> 是数组中一个连续非空的元素序列。</p>
//
//<p><strong>数组的最小公倍数</strong> 是可被所有数组元素整除的最小正整数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1 ：</strong></p>
//
//<pre><strong>输入：</strong>nums = [3,6,2,7,1], k = 6
//<strong>输出：</strong>4
//<strong>解释：</strong>以 6 为最小公倍数的子数组是：
//- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,2,7,1]
//- [<em><strong>3</strong></em>,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
//- [3,<em><strong>6</strong></em>,2,7,1]
//- [3,<em><strong>6</strong></em>,<em><strong>2</strong></em>,7,1]
//</pre>
//
//<p><strong>示例 2 ：</strong></p>
//
//<pre><strong>输入：</strong>nums = [3], k = 2
//<strong>输出：</strong>0
//<strong>解释：</strong>不存在以 2 为最小公倍数的子数组。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= nums[i], k &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 10</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

// 6234.最小公倍数为 K 的子数组数目
// 开题时间：2022-11-14 08:53:37
public class NumberOfSubarraysWithLcmEqualToK {
  public static void main(String[] args) {
    Solution solution = new NumberOfSubarraysWithLcmEqualToK().new Solution();
    System.out.println(solution.subarrayLCM(new int[]{3, 6, 2, 7, 1}, 6));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    
    // 32*2 32*3 32*5    32*2*3*5
    // 3*5 2*5 2*3
    int[][] lcms;
    
    public int subarrayLCM9(int[] nums, int k) {
      int cnt = 0;
      int len = nums.length;
      lcms = new int[len][len];
      for (int i = 0; i < len; i++)
        lcms[i][i] = nums[i];
      
      for (int i = 0; i < len; i++) {
        for (int j = i; j >= 0; j--) {
          if (check(nums, k, j, i))
            cnt++;
        }
      }
      return cnt;
    }
    
    private boolean check(int[] nums, int k, int start, int end) {
      if (start == end)
        return k == nums[start];
      
      lcms[start][end] = lcm(lcms[start][end - 1], nums[end]);
      return lcms[start][end] == k;
    }
    
    // 暴力枚举
    public int subarrayLCM(int[] nums, int k) {
      int cnt = 0;
      int len = nums.length;
      
      for (int i = 0; i < len; i++) {
        int lcm = nums[i];
        for (int j = i; j < len; j++) {
          lcm = lcm(lcm, nums[j]);
          if (k == lcm)
            cnt++;
          else if (k % lcm != 0)
            break;
        }
      }
      
      return cnt;
    }
    
    public static int gcd(int a, int b) {
      return b == 0 ?
          a :
          gcd(b, a % b);
    }
    
    public static int lcm(int a, int b) {
      return a * b / gcd(a, b);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}