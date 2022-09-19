//<p>序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。</p>
//
//<p>请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。</p>
//
//<p><strong>提示: </strong>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅&nbsp;<a href="/faq/#binary-tree">LeetCode 序列化二叉树的格式</a>。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2,3,null,null,4,5]
//<strong>输出：</strong>[1,2,3,null,null,4,5]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = []
//<strong>输出：</strong>[]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1]
//<strong>输出：</strong>[1]
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>root = [1,2]
//<strong>输出：</strong>[1,2]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中结点数在范围 <code>[0, 10<sup>4</sup>]</code> 内</li> 
// <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>设计</li><li>字符串</li><li>二叉树</li></div></div><br><div><li>👍 976</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.tree;

import org.example.leetcode.problems.common.tree.TreeNode;

import java.util.*;

//297.二叉树的序列化与反序列化
//开题时间：2022-09-18 09:00:48
public class SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node15 = new TreeNode(15);
        TreeNode node10 = new TreeNode(10);
        TreeNode node20 = new TreeNode(20);
        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node8 = new TreeNode(8);
        node3.left = node9;
        node3.right = node20;
        node9.left = node15;
        node9.right = node10;
        node20.right = node7;
        node7.left = node5;
        node7.right = node8;
        Codec codec = new SerializeAndDeserializeBinaryTree().new Codec();
        String serialize = codec.serialize2(node3);
        System.out.println(serialize);
        System.out.println(codec.deserialize2(serialize));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Codec {
        /*
                 3
                / \
               9  20
              / \   \
             15 10   7
                    / \
                   5   8
             3, 9,20, 15,10,,7, ,,,,5,8
             3,9,20,15,10,,7,,,,,5,8,,,,,
         */

        public static final char DELI = ',';

        //region BFS
        public String serialize(TreeNode root) {
            if (root == null) return null;
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    sb.append(poll.val);
                    queue.offer(poll.left);
                    queue.offer(poll.right);
                }
                sb.append(DELI);
            }

            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            if (data == null) return null;
            String[] split = data.split(String.valueOf(DELI), -1);
            TreeNode root = new TreeNode(Integer.parseInt(split[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            int idx = 1;
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                for (int j = 0; j < 2; j++) {
                    String s = split[idx++];
                    if (!s.isEmpty()) {
                        TreeNode node = new TreeNode(Integer.parseInt(s));
                        if (j == 0) poll.left = node;
                        else poll.right = node;
                        queue.offer(node);
                    }
                }
            }
            return root;
        }
        //endregion


        //region DFS(pre order)
        public String serialize2(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            preOrderTraverse(root, sb);
            return sb.toString();
        }

        private void preOrderTraverse(TreeNode root, StringBuilder sb) {
            boolean b = root != null;
            if (b) sb.append(root.val);
            sb.append(DELI);
            if (b) {
                preOrderTraverse(root.left, sb);
                preOrderTraverse(root.right, sb);
            }
        }

        int idx = 0;

        public TreeNode deserialize2(String data) {
            if (data == null) return null;
            String[] split = data.split(String.valueOf(DELI), -1);
            return helper(split);
        }

        private TreeNode helper(String[] split) {
            String s = split[idx++];
            if (!s.isEmpty()) {
                TreeNode node = new TreeNode(Integer.parseInt(s));
                node.left = helper(split);
                node.right = helper(split);
                return node;
            } else {
                return null;
            }
        }
        //endregion
    }
//leetcode submit region end(Prohibit modification and deletion)
}