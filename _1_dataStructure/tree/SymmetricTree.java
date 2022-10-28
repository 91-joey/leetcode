//<p>给你一个二叉树的根节点 <code>root</code> ， 检查它是否轴对称。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/symtree1.jpg" style="width: 354px; height: 291px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2,2,3,4,4,3]
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p> 
//<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/symtree2.jpg" style="width: 308px; height: 258px;" /> 
//<pre>
//<strong>输入：</strong>root = [1,2,2,null,3,null,3]
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>树中节点数目在范围 <code>[1, 1000]</code> 内</li> 
// <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你可以运用递归和迭代两种方法解决这个问题吗？</p>
//
//<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 2102</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree;

import org.example.leetcode.problems._3_common.tree.TreeNode;

import java.util.*;

//101.对称二叉树
//开题时间：2022-09-14 11:36:58
public class SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new SymmetricTree().new Solution();
//        System.out.println(solution.isSymmetric3(
//                new TreeNode(1,
//                        new TreeNode(2,
//                                new TreeNode(3),
//                                new TreeNode(4)),
//                        new TreeNode(2,
//                                new TreeNode(4),
//                                new TreeNode(3)))));

//        [2,3,3,4,5,null,4]
        System.out.println(solution.isSymmetric_BFS(
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(5)),
                        new TreeNode(3,
                                null,
                                new TreeNode(4)))));
    }
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //DFS+递归
        public boolean isSymmetric(TreeNode root) {
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode l, TreeNode r) {
            if ((l == null && r == null)) return true;
            if ((l == null || r == null)) return false;
            return l.val == r.val &&
                    isSymmetric(l.left, r.right) &&
                    isSymmetric(l.right, r.left);
        }

        //DFS+栈
        public boolean isSymmetric_stack(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root.right);
            stack.push(root.left);

            while (!stack.isEmpty()) {
                TreeNode l = stack.pop();
                TreeNode r = stack.pop();
                if (l == null ^ r == null) {
                    return false;
                } else if (l != null) {
                    if ((l.val != r.val))
                        return false;
                    stack.push(r.left);
                    stack.push(l.right);
                    stack.push(r.right);
                    stack.push(l.left);
                }
            }

            return true;
        }

        //DFS+queue
        public boolean isSymmetric_queue(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root.left);
            queue.offer(root.right);

            while (!queue.isEmpty()) {
                TreeNode l = queue.poll();
                TreeNode r = queue.poll();
                if (l == null ^ r == null) {
                    return false;
                } else if (l != null) {
                    if ((l.val != r.val))
                        return false;
                    queue.offer(l.left);
                    queue.offer(r.right);
                    queue.offer(l.right);
                    queue.offer(r.left);
                }
            }

            return true;
        }

        //Morris
        public boolean isSymmetric_morris(TreeNode root) {
            TreeNode curL = root.left;
            TreeNode curR = root.right;

            while (curL != null && curR != null) {
                TreeNode l = curL.left;
                TreeNode r = curR.right;
                if (l != null && r != null) {
                    while (l.right != null && l.right != curL &&
                            r.left != null && r.left != curR) {
                        l = l.right;
                        r = r.left;
                    }
                    if (l.right == null && r.left == null) {
                        //节点值不同
                        if (curL.val != curR.val)
                            return false;
                        l.right = curL;
                        r.left = curR;
                        curL = curL.left;
                        curR = curR.right;
                    } else if (l.right == curL && r.left == curR) {
                        l.right = null;
                        r.left = null;
                        curL = curL.right;
                        curR = curR.left;
                    //结构不同
                    } else {
                        return false;
                    }
                } else if (l == null && r == null) {
                    //节点值不同
                    if (curL.val != curR.val)
                        return false;
                    curL = curL.right;
                    curR = curR.left;
                //结构不同
                } else {
                    return false;
                }
            }

            //根节点的左右子树全空：true  -> root.left == null && root.right == null
            //根节点的左右子树单空：false -> root.left == null ^  root.right == null
            //遍历完成：          true  -> curL == null && curR == null
            return curL == null && curR == null;
        }

        //BFS+queue
        public boolean isSymmetric_BFS(TreeNode root) {
            Queue<TreeNode> queueL = new LinkedList<>();
            Queue<TreeNode> queueR = new LinkedList<>();
            queueL.offer(root.left);
            queueR.offer(root.right);

            while (!queueL.isEmpty()) {
                int size = queueL.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pollL = queueL.poll();
                    TreeNode pollR = queueR.poll();

                    if (pollL == null ^ pollR == null) {
                        return false;
                    } else if (pollL != null) {
                        if ((pollL.val != pollR.val))
                            return false;
                        queueL.offer(pollL.left);
                        queueL.offer(pollL.right);
                        queueR.offer(pollR.right);
                        queueR.offer(pollR.left);
                    }
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}