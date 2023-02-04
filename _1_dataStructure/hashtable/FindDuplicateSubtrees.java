//<p>给定一棵二叉树 <code>root</code>，返回所有<strong>重复的子树</strong>。</p>
//
//<p>对于同一类的重复子树，你只需要返回其中任意<strong>一棵</strong>的根结点即可。</p>
//
//<p>如果两棵树具有<strong>相同的结构</strong>和<strong>相同的结点值</strong>，则它们是<strong>重复</strong>的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/16/e1.jpg" style="height: 236px; width: 300px;" /></p>
//
//<pre>
//<strong>输入：</strong>root = [1,2,3,4,null,2,4,null,null,4]
//<strong>输出：</strong>[[2,4],[4]]</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/16/e2.jpg" style="height: 125px; width: 200px;" /></p>
//
//<pre>
//<strong>输入：</strong>root = [2,1,1]
//<strong>输出：</strong>[[1]]</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2020/08/16/e33.jpg" style="height: 202px; width: 300px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>root = [2,2,2,3,null,3,null]
//<strong>输出：</strong>[[2,3],[3]]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中的结点数在<code>[1,10^4]</code>范围内。</li> 
// <li><code>-200 &lt;= Node.val &lt;= 200</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>哈希表</li><li>二叉树</li></div></div><br><div><li>👍 625</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import org.example.leetcode.problems._3_common.entity.Entry;
import org.example.leetcode.problems._3_common.entity.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 652.寻找重复的子树
// 开题时间：2022-09-09 09:04:28
public class FindDuplicateSubtrees {
  public static void main(String[] args) {
    Solution solution = new FindDuplicateSubtrees().new Solution();
    System.out.println(Arrays.toString(new int[]{1, 2, 3}));
  }
  // leetcode submit region begin(Prohibit modification and deletion)
  
  /**
   * Definition for a binary tree node.
   * public class TreeNode {
   * int val;
   * TreeNode left;
   * TreeNode right;
   * TreeNode() {}
   * TreeNode(int val) { this.val = val; }
   * TreeNode(int val, TreeNode left, TreeNode right) {
   * this.val = val;
   * this.left = left;
   * this.right = right;
   * }
   * }
   */
  class Solution {
    Set<TreeNode> ans = new HashSet<>();
    Map<String, TreeNode> map = new HashMap<>();
    
    // 1.自解：序列化
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
      DFS(root);
      return new ArrayList<>(ans);
    }
    
    public String DFS(TreeNode root) {
      if (root == null) {
        return " ";
      }
      
      StringBuilder serial = new StringBuilder();
      serial.append(root.val).
          append(">").
          append(DFS(root.left)).
          append(DFS(root.right));
      
      String key = serial.toString();
      TreeNode value = map.get(key);
      if (value == null) {
        map.put(key, root);
      } else {
        ans.add(value);
      }
      
      return key;
    }
    
    Map<String, Entry<TreeNode, Integer>> map2 = new HashMap<>();
    Set<TreeNode> ans2 = new HashSet<>();
    int idx;
    
    // 2.官解（序列化之三元组）
    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
      DFS2(root);
      return new ArrayList<>(ans2);
    }
    
    public int DFS2(TreeNode root) {
      if (root == null) {
        return 0;
      }
      
      String key = String.join(",",
          String.valueOf(root.val),
          String.valueOf(DFS2(root.left)),
          String.valueOf(DFS2(root.right)));
      
      Entry<TreeNode, Integer> value = map2.get(key);
      if (value == null) {
        map2.put(key, new Entry<>(root, ++idx));
        return idx;
      } else {
        ans2.add(value.key);
        return value.value;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}