//<p>给定数组
// <meta charset="UTF-8" />&nbsp;<code>people</code>&nbsp;。<code>people[i]</code>表示第 <code>i</code><sup>&nbsp;</sup>个人的体重&nbsp;，<strong>船的数量不限</strong>，每艘船可以承载的最大重量为&nbsp;<code>limit</code>。</p>
//
//<p>每艘船最多可同时载两人，但条件是这些人的重量之和最多为&nbsp;<code>limit</code>。</p>
//
//<p>返回 <em>承载所有人所需的最小船数</em>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>people = [1,2], limit = 3
//<strong>输出：</strong>1
//<strong>解释：</strong>1 艘船载 (1, 2)
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>people = [3,2,2,1], limit = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>3 艘船分别载 (1, 2), (2) 和 (3)
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>people = [3,5,3,4], limit = 5
//<strong>输出：</strong>4
//<strong>解释：</strong>4 艘船分别载 (3), (3), (4), (5)</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= people.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= people[i] &lt;= limit &lt;= 3 * 10<sup>4</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>贪心</li><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 252</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

// 881.救生艇
// 开题时间：2022-10-27 12:10:08
public class BoatsToSavePeople {
  public static void main(String[] args) {
    Solution solution = new BoatsToSavePeople().new Solution();
    //        System.out.println(solution.numRescueBoats(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 13));
    System.out.println(solution.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int numRescueBoats(int[] people, int limit) {
      int cnt = 0;
      Arrays.sort(people);
      
      
      for (int l = 0, r = people.length - 1; l < r; ) {
        while (l < r && people[l] + people[r] > limit) {
          r--;
          cnt++;
        }
        
        if (l != r) {
          l++;
          r--;
          if (l == r)
            cnt++;
        }
        
        cnt++;
      }
      
      return cnt;
    }
    
    public int numRescueBoats2(int[] people, int limit) {
      int cnt = 0;
      Arrays.sort(people);
      
      for (int l = 0, r = people.length - 1; l < r; ) {
        int t = limit - people[l];
        int tmp = r;
        while (l < r && people[r] > t)
          r--;
        cnt += tmp - r;
        
        if (l != r) {
          tmp = r;
          do {
            l++;
            r--;
          } while (l < r && people[l] + people[r] <= limit);
          cnt += tmp - r;
          if (l == r)
            cnt++;
        } else
          cnt++;
      }
      
      return cnt;
    }
    
    // 贪心
    public int numRescueBoats3(int[] people, int limit) {
      int cnt = 0;
      Arrays.sort(people);
      
      for (int l = 0, r = people.length - 1; l <= r; ) {
        if (people[l] + people[r] <= limit)
          l++;
        r--;
        cnt++;
      }
      
      return cnt;
    }
    
    
    // 贪心（优化）
    public int numRescueBoats4(int[] people, int limit) {
      Arrays.sort(people);
      
      int lst = people.length - 1;
      int r = lst;
      for (int l = 0; l <= r; r--)
        if (people[l] + people[r] <= limit)
          l++;
      
      return lst - r;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}