//<p>这里有一个非负整数数组&nbsp;<code>arr</code>，你最开始位于该数组的起始下标&nbsp;<code>start</code>&nbsp;处。当你位于下标&nbsp;<code>i</code>&nbsp;处时，你可以跳到&nbsp;<code>i + arr[i]</code> 或者 <code>i - arr[i]</code>。</p>
//
//<p>请你判断自己是否能够跳到对应元素值为 0 的 <strong>任一</strong> 下标处。</p>
//
//<p>注意，不管是什么情况下，你都无法跳到数组之外。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [4,2,3,0,3,1,2], start = 5
//<strong>输出：</strong>true
//<strong>解释：</strong>
// 到达值为 0 的下标 3 有以下可能方案：
// 下标 5 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3
// 下标 5 -&gt; 下标 6 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [4,2,3,0,3,1,2], start = 0
//<strong>输出：</strong>true 
//<strong>解释：
//</strong>到达值为 0 的下标 3 有以下可能方案： 
// 下标 0 -&gt; 下标 4 -&gt; 下标 1 -&gt; 下标 3
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr = [3,0,2,1,2], start = 2
//<strong>输出：</strong>false
//<strong>解释：</strong>无法到达值为 0 的下标 1 处。 
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 5 * 10^4</code></li> 
// <li><code>0 &lt;= arr[i] &lt;&nbsp;arr.length</code></li> 
// <li><code>0 &lt;= start &lt; arr.length</code></li> 
//</ul>
//
//<div><li>👍 136</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import java.util.LinkedList;
import java.util.Queue;

// 1306.跳跃游戏 III
// 开题时间：2023-01-11 12:17:35
public class JumpGameIii {
  public static void main(String[] args) {
    Solution solution = new JumpGameIii().new Solution();
    System.out.println(solution.canReach(new int[]{0, 1}, 1));
    //        System.out.println(solution.canReach(new int[]{3, 0, 2, 1, 2}, 2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
  
    private int[] arr;
    boolean[] vis;
  
    // ☆☆☆☆☆ dfs
    public boolean canReach9(int[] arr, int start) {
      this.arr = arr;
      vis = new boolean[arr.length];
    
      return dfs(start);
    }
  
    // bfs
    public boolean canReach(int[] arr, int start) {
      this.arr = arr;
      vis = new boolean[arr.length];
    
      LinkedList<Integer> q = new LinkedList<>();
      q.offer(start);
      vis[start] = true;
    
      while (!q.isEmpty()) {
        int cur = q.poll();
      
        if (arr[cur] == 0) {
          return true;
        }
      
        for (int next : new int[]{cur + arr[cur], cur - arr[cur]}) {
          if (0 <= next && next < arr.length && !vis[next]) {
            q.offer(next);
            vis[next] = true;
          }
        }
      }
    
      return false;
    }
  
    private boolean dfs(int start) {
      if (start < 0 || start >= arr.length || vis[start]) {
        return false;
      }
    
      if (arr[start] == 0) {
        return true;
      }
      vis[start] = true;
    
      return dfs(start + arr[start]) || dfs(start - arr[start]);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}