//<p>有 <code>n</code> 个盒子。给你一个长度为 <code>n</code> 的二进制字符串 <code>boxes</code> ，其中 <code>boxes[i]</code> 的值为 <code>'0'</code> 表示第 <code>i</code> 个盒子是 <strong>空</strong> 的，而 <code>boxes[i]</code> 的值为 <code>'1'</code> 表示盒子里有 <strong>一个</strong> 小球。</p>
//
//<p>在一步操作中，你可以将 <strong>一个</strong> 小球从某个盒子移动到一个与之相邻的盒子中。第 <code>i</code> 个盒子和第 <code>j</code> 个盒子相邻需满足 <code>abs(i - j) == 1</code> 。注意，操作执行后，某些盒子中可能会存在不止一个小球。</p>
//
//<p>返回一个长度为 <code>n</code> 的数组 <code>answer</code> ，其中 <code>answer[i]</code> 是将所有小球移动到第 <code>i</code> 个盒子所需的 <strong>最小</strong> 操作数。</p>
//
//<p>每个 <code>answer[i]</code> 都需要根据盒子的 <strong>初始状态</strong> 进行计算。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>boxes = "110"
//<strong>输出：</strong>[1,1,3]
//<strong>解释：</strong>每个盒子对应的最小操作数如下：
// 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
// 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
// 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>boxes = "001011"
//<strong>输出：</strong>[11,8,5,4,3,4]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == boxes.length</code></li> 
// <li><code>1 &lt;= n &lt;= 2000</code></li> 
// <li><code>boxes[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li> 
//</ul>
//
//<div><li>👍 40</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

// 1769.移动所有球到每个盒子所需的最小操作数
// 开题时间：2022-12-02 08:46:40
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfOperationsToMoveAllBallsToEachBox().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] minOperations9(String boxes) {
      char[] cs = boxes.toCharArray();
      int n = cs.length;
      int[] ans = new int[n];
      
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          if (cs[j] == '1')
            ans[i] += Math.abs(i - j);
      
      return ans;
    }
    
    // 根据前一个盒子的操作数得到下一个盒子的操作数
    public int[] minOperations(String boxes) {
      char[] cs = boxes.toCharArray();
      int n = cs.length;
      int[] ans = new int[n];
      int l = cs[0] - '0', r = 0;
      
      for (int i = 1; i < n; i++) {
        if (cs[i] == '1') {
          r++;
          ans[0] += i;
        }
      }
      
      for (int i = 1; i < n; i++) {
        ans[i] = ans[i - 1] + l - r;
        if (cs[i] == '1') {
          l++;
          r--;
        }
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}