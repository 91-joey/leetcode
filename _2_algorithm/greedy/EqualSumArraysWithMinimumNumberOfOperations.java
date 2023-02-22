//<p>给你两个长度可能不等的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;。两个数组中的所有值都在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>6</code>&nbsp;之间（包含&nbsp;<code>1</code>&nbsp;和&nbsp;<code>6</code>）。</p>
//
//<p>每次操作中，你可以选择 <strong>任意</strong>&nbsp;数组中的任意一个整数，将它变成 <code>1</code>&nbsp;到 <code>6</code>&nbsp;之间 <strong>任意</strong>&nbsp;的值（包含 <code>1</code>&nbsp;和 <code><span style="">6</span></code>）。</p>
//
//<p>请你返回使 <code>nums1</code>&nbsp;中所有数的和与&nbsp;<code>nums2</code>&nbsp;中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 <code>-1</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
//<b>输出：</b>3
//<b>解释：</b>你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [<strong>6</strong>,1,2,2,2,2] 。
//- 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,<strong>1</strong>], nums2 = [6,1,2,2,2,2] 。
//- 将 nums1[2] 变为 2 。 nums1 = [1,2,<strong>2</strong>,4,5,1], nums2 = [6,1,2,2,2,2] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums1 = [1,1,1,1,1,1,1], nums2 = [6]
//<b>输出：</b>-1
//<b>解释：</b>没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>nums1 = [6,6], nums2 = [1]
//<b>输出：</b>3
//<b>解释：</b>你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums1[0] 变为 2 。 nums1 = [<strong>2</strong>,6], nums2 = [1] 。
//- 将 nums1[1] 变为 2 。 nums1 = [2,<strong>2</strong>], nums2 = [1] 。
//- 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [<strong>4</strong>] 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= nums1[i], nums2[i] &lt;= 6</code></li> 
//</ul>
//
//<div><li>👍 81</li><li>👎 0</li></div>
package _2_algorithm.greedy;

import java.util.Arrays;

// 1775.通过最少操作次数使数组的和相等
// 开题时间：2022-12-07 10:16:28
public class EqualSumArraysWithMinimumNumberOfOperations {
  public static void main(String[] args) {
    Solution solution = new EqualSumArraysWithMinimumNumberOfOperations().new Solution();
    System.out.println(solution.minOperations(new int[]{5, 6, 4, 3, 1, 2}, new int[]{6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4}));
    //        System.out.println(solution.minOperations(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1, 2, 2, 2, 2}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minOperationsX(int[] nums1, int[] nums2) {
      int m = nums1.length + 1;
      int n = nums2.length + 1;
      
      int[][] f = new int[m][n];
      for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
          f[i][j] = i + j;
          int a = nums1[i - 1];
          int b = nums2[j - 1];
          if (a == b)
            f[i][j] = f[i - 1][j - 1];
          else if (a < b)
            for (int k = i - 1, sum = a; k > 0; k--) {
              sum += nums1[k - 1];
              if (sum <= b)
                f[i][j] = Math.min(f[i][j], f[k - 1][j - 1] + (sum == b ? 0 : 1));
              else
                break;
            }
          else
            for (int k = j - 1, sum = b; k > 0; k--) {
              sum += nums2[k - 1];
              if (sum <= a)
                f[i][j] = Math.min(f[i][j], f[i - 1][k - 1] + (sum == a ? 0 : 1));
              else
                break;
            }
        }
      }
      
      return f[m - 1][n - 1];
    }
    
    // 贪心+哈希表计数
    public int minOperations9(int[] nums1, int[] nums2) {
      int m = nums1.length;
      int n = nums2.length;
      if (6 * n < m || 6 * m < n)
        return -1;
      
      int diff = Arrays.stream(nums1).sum() - Arrays.stream(nums2).sum();
      if (diff == 0)
        return 0;
      else if (diff > 0)
        return helper(nums1, nums2, diff);
      else
        return helper(nums2, nums1, -diff);
    }
    
    private int helper(int[] A, int[] B, int diff) {
      int[] cnts = new int[6];
      for (int e : A) cnts[e - 1]++;
      for (int e : B) cnts[6 - e]++;
      int ans = 0;
      for (int i = cnts.length - 1; i >= 1; i--)
        if (cnts[i] * i == diff) {
          ans += cnts[i];
          break;
        } else if (cnts[i] * i < diff) {
          ans += cnts[i];
          diff -= cnts[i] * i;
        } else {
          ans += Math.ceil((double) diff / i);
          break;
        }
      return ans;
    }
    
    
    // 贪心+哈希表计数（优化）
    public int minOperations(int[] nums1, int[] nums2) {
      int m = nums1.length;
      int n = nums2.length;
      if (6 * n < m || 6 * m < n)
        return -1;
      
      int d = 0;
      for (int x : nums1) d += x;
      for (int x : nums2) d -= x;
      
      if (d == 0)
        return 0;
      else if (d > 0)
        return helper2(nums1, nums2, d);
      else
        return helper2(nums2, nums1, -d);
    }
    
    private int helper2(int[] A, int[] B, int d) {
      int[] cnt = new int[6];
      for (int x : A) cnt[x - 1]++;
      for (int x : B) cnt[6 - x]++;
      int ans = 0;
      for (int i = cnt.length - 1; ; i--) {
        if (cnt[i] * i >= d)
          return ans + (d + i - 1) / i;
        ans += cnt[i];
        d -= cnt[i] * i;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}