//<p>给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。</p>
//
//<p><a href="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin" target="_blank">百度百科</a>中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。”</p>
//
//<p>例如，给定如下二叉搜索树:&nbsp; root =&nbsp;[6,2,8,0,4,7,9,null,null,3,5]</p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png" style="height: 190px; width: 200px;" /></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//<strong>输出:</strong> 6 
//<strong>解释: </strong>节点 <span><code>2 </code></span>和节点 <span><code>8 </code></span>的最近公共祖先是 <span><code>6。</code></span>
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre><strong>输入:</strong> root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//<strong>输出:</strong> 2
//<strong>解释: </strong>节点 <span><code>2</code></span> 和节点 <span><code>4</code></span> 的最近公共祖先是 <span><code>2</code></span>, 因为根据定义最近公共祖先节点可以为节点本身。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>说明:</strong></p>
//
//<ul> 
// <li>所有节点的值都是唯一的。</li> 
// <li>p、q 为不同节点且均存在于给定的二叉搜索树中。</li> 
//</ul>
//
//<div><li>👍 975</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.tree.BST;

import org.example.leetcode.problems._3_common.entity.tree.TreeNode;
import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.LinkedList;
import java.util.Queue;

//235.二叉搜索树的最近公共祖先
//开题时间：2022-12-11 16:12:38
public class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinarySearchTree().new Solution();
        TreeNode root = Tools.buildTree("[6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println(solution.lowestCommonAncestor(root, root.left, root.right));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode lowestCommonAncestor9(TreeNode root, TreeNode p, TreeNode q) {
            Queue<TreeNode> qP = getPath(root, p);
            Queue<TreeNode> qQ = getPath(root, q);

            TreeNode a = root;
            while (qP.peek() == qQ.peek()) {
                a = qP.poll();
                qQ.poll();
            }

            return a;
        }

        public TreeNode lowestCommonAncestor8(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root, a = root, b = root;
            while (a == b) {
                ancestor = a;
                a = a.val == p.val ? a : a.val < p.val ? a.right : a.left;
                b = b.val == q.val ? b : b.val < q.val ? b.right : b.left;
            }
            return ancestor;
        }


        public TreeNode lowestCommonAncestor7(TreeNode root, TreeNode p, TreeNode q) {
            if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
            if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
            return root;
        }

        public TreeNode lowestCommonAncestor6(TreeNode root, TreeNode p, TreeNode q) {
            while (true) {
                if (root.val > p.val && root.val > q.val)
                    root = root.left;
                else if (root.val < p.val && root.val < q.val)
                    root = root.right;
                else
                    return root;
            }
        }

        /*
         * ☆☆☆☆☆
         * 如果两个节点值都小于根节点，说明他们都在根节点的左子树上，我们往左子树上找
         * 如果两个节点值都大于根节点，说明他们都在根节点的右子树上，我们往右子树上找
         * 如果一个节点值大于根节点，一个节点值小于根节点，说明他们他们一个在根节点的左子树上一个在根节点的右子树上，那么根节点就是他们的最近公共祖先节点。
         */
        public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
            while ((long) (root.val - p.val) * (root.val - q.val) > 0)
                root = root.val > p.val ? root.left : root.right;
            return root;
        }


        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if ((long) (root.val - p.val) * (root.val - q.val) > 0)
                return lowestCommonAncestor(root.val > p.val ? root.left : root.right, p, q);
            return root;
        }

        private Queue<TreeNode> getPath(TreeNode root, TreeNode t) {
            Queue<TreeNode> dq = new LinkedList<>();
            dq.offer(root);

            while (root.val != t.val) {
                root = root.val < t.val ? root.right : root.left;
                dq.offer(root);
            }

            return dq;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}