//<p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>
//
//<p>构造这个链表的&nbsp;<strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。&nbsp;深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code> 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>
//
//<p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --&gt; Y</code> 。那么在复制链表中对应的两个节点 <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --&gt; y</code> 。</p>
//
//<p>返回复制链表的头节点。</p>
//
//<p>用一个由&nbsp;<code>n</code>&nbsp;个节点组成的链表来表示输入/输出中的链表。每个节点用一个&nbsp;<code>[val, random_index]</code>&nbsp;表示：</p>
//
//<ul> 
// <li><code>val</code>：一个表示&nbsp;<code>Node.val</code>&nbsp;的整数。</li> 
// <li><code>random_index</code>：随机指针指向的节点索引（范围从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>）；如果不指向任何节点，则为&nbsp;&nbsp;<code>null</code>&nbsp;。</li> 
//</ul>
//
//<p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png" style="height: 142px; width: 700px;" /></p>
//
//<pre>
//<strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png" style="height: 114px; width: 700px;" /></p>
//
//<pre>
//<strong>输入：</strong>head = [[1,1],[2,1]]
//<strong>输出：</strong>[[1,1],[2,1]]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png" style="height: 122px; width: 700px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
//<strong>输出：</strong>[[3,null],[3,0],[3,null]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= n &lt;= 1000</code>
//  <meta charset="UTF-8" /></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
// <li><code>Node.random</code>&nbsp;为&nbsp;<code>null</code> 或指向链表中的节点。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>链表</li></div></div><br><div><li>👍 978</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

import org.example.leetcode.problems.common.Node;

import java.util.*;

//138.复制带随机指针的链表
//开题时间：2022-09-02 11:06:49
public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new CopyListWithRandomPointer().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {
        //1.迭代 n^2  n
        public Node copyRandomList(Node head) {
            Node dummy = new Node(-1);
            Node p = dummy;

            Node pHead = head;
            int size = 0;
            while (pHead != null) {
                pHead = pHead.next;
                size++;
            }

            List<NodeAndRandomIdx> copies = new ArrayList<>(size);
            for (pHead = head; pHead != null; pHead = pHead.next) {
                p.next = new Node(pHead.val);
                p = p.next;
                //record random pointer
                Node random = pHead.random;
                int randomIdx = -1;
                if (random != null) {
                    int step = 0;
                    for (Node r = random.next; r != null; r = r.next) {
                        step++;
                    }
                    randomIdx = size - step - 1;
                }
                copies.add(new NodeAndRandomIdx(p, randomIdx));
            }

            for (NodeAndRandomIdx nodeAndRandomIdx : copies) {
                if (nodeAndRandomIdx.randomIdx != -1) {
                    nodeAndRandomIdx.node.random = copies.get(nodeAndRandomIdx.randomIdx).node;
                }
            }

            return dummy.next;
        }

        //GJ2.迭代 n  1
        public Node copyRandomListGJ1(Node head) {
            if (head == null) {
                return null;
            }

            //1.元节点后追加拷贝节点，即A → A' → B → B′ → C → C′
            for (Node p = head; p != null; p = p.next.next) {
                Node copy = new Node(p.val);
                copy.next = p.next;
                p.next = copy;
            }
            //2.拷贝节点的random域赋值
            for (Node p = head; p != null; p = p.next.next) {
                p.next.random = p.random == null ? null : p.random.next;
            }
            //3.复原原链表，连接拷贝节点，返回新头节点
            Node headNew = head.next;
            for (Node p = head; p != null; p = p.next) {
                Node copy = p.next;
                p.next = copy.next;
                copy.next = copy.next == null ? null : copy.next.next;
            }

            return headNew;
        }

        //2.秀解.哈希表    n   n
        public Node copyRandomList2(Node head) {
            Map<Node, Node> map = new HashMap<>();

            for (Node p = head; p != null; p = p.next) {
                map.put(p, new Node(p.val));
            }
            for (Node p = head; p != null; p = p.next) {
                Node copy = map.get(p);
                copy.next = map.get(p.next);
                copy.random = map.get(p.random);
            }

            return map.get(head);
        }

        private class NodeAndRandomIdx {
            public Node node;
            public int randomIdx;

            public NodeAndRandomIdx(Node node, int randomIdx) {
                this.node = node;
                this.randomIdx = randomIdx;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}