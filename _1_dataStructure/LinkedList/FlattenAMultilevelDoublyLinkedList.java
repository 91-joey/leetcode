//<p>你会得到一个双链表，其中包含的节点有一个下一个指针、一个前一个指针和一个额外的 <strong>子指针</strong> 。这个子指针可能指向一个单独的双向链表，也包含这些特殊的节点。这些子列表可以有一个或多个自己的子列表，以此类推，以生成如下面的示例所示的 <strong>多层数据结构</strong> 。</p>
//
//<p>给定链表的头节点&nbsp;<font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">head</span></span></font></font>&nbsp;，将链表 <strong>扁平化</strong> ，以便所有节点都出现在单层双链表中。让 <code>curr</code> 是一个带有子列表的节点。子列表中的节点应该出现在<strong>扁平化列表</strong>中的&nbsp;<code>curr</code> <strong>之后</strong> 和&nbsp;<code>curr.next</code>&nbsp;<strong>之前</strong> 。</p>
//
//<p>返回 <em>扁平列表的 <code>head</code>&nbsp;。列表中的节点必须将其 <strong>所有</strong> 子指针设置为&nbsp;<code>null</code>&nbsp;。</em></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/11/09/flatten11.jpg" style="height:339px; width:700px" /></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//<strong>输出：</strong>[1,2,3,7,8,11,12,9,10,4,5,6]
//<strong>解释：</strong>输入的多级列表如上图所示。
//扁平化后的链表如下图：
//<img src="https://assets.leetcode.com/uploads/2021/11/09/flatten12.jpg" style="height:69px; width:1000px" />
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/11/09/flatten2.1jpg" style="height:200px; width:200px" /></p>
//
//<pre>
//<strong>输入：</strong>head = [1,2,null,3]
//<strong>输出：</strong>[1,3,2]
//<strong>解释：</strong>输入的多级列表如上图所示。
//扁平化后的链表如下图：
//<img src="https://assets.leetcode.com/uploads/2021/11/24/list.jpg" style="height:87px; width:300px" />
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>head = []
//<strong>输出：</strong>[]
//<strong>说明：</strong>输入中可能存在空列表。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>节点数目不超过 <code>1000</code></li> 
// <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>如何表示测试用例中的多级链表？</strong></p>
//
//<p>以 <strong>示例 1</strong> 为例：</p>
//
//<pre>
// 1---2---3---4---5---6--NULL
//         |
//         7---8---9---10--NULL
//             |
//             11--12--NULL</pre>
//
//<p>序列化其中的每一级之后：</p>
//
//<pre>
//[1,2,3,4,5,6,null]
//[7,8,9,10,null]
//[11,12,null]
//</pre>
//
//<p>为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。</p>
//
//<pre>
//[1,2,3,4,5,6,null]
//[null,null,7,8,9,10,null]
//[null,11,12,null]
//</pre>
//
//<p>合并所有序列化结果，并去除末尾的 null 。</p>
//
//<pre>
//[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//</pre>
//
//<ul> 
//</ul>
//
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍 371</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.LinkedList;

import org.example.leetcode.problems._3_common.linkedlist.Node;

//430.扁平化多级双向链表
//开题时间：2022-09-01 10:43:50
public class FlattenAMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        Solution solution = new FlattenAMultilevelDoublyLinkedList().new Solution();
        Node grandChild = new Node(11, new Node(12));
        Node child = new Node(7, new Node(8, new Node(9, new Node(10)), grandChild));
        Node root = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))), child)));
        System.out.println(solution.flatten(root));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //1.DFS+递归  n   n
        public Node flatten(Node head) {
            flattenAndGetTail(head);
            return head;
        }

        public Node flattenAndGetTail(Node head) {
            while (head != null) {
                Node child = head.child;
                Node succ = head.next;
                if (child != null) {
                    //head⇋head.child
                    head.next = child;
                    child.prev = head;
                    head.child = null;
                    Node childTail = flattenAndGetTail(child);
                    //head.child.childTail⇋head.next
                    if (succ != null) {
                        childTail.next = succ;
                        succ.prev = childTail;
                    }
                    head = childTail;
                }
                //返回的是最后一个节点，即null的前节点
                if (succ == null) {
                    break;
                }
                head = succ;
            }
            return head;
        }

        //2.迭代  n   1
        public Node flatten2(Node head) {
            for (Node p = head; p != null; p = p.next) {
                Node child = p.child;
                if (child != null) {
                    Node succ = p.next;
                    //1.head⇋head.child
                    p.next = child;
                    child.prev = p;
                    p.child = null;

                    //2.head.child.childTail⇋head.next
                    Node childTail = child;
                    while (childTail.next != null) {
                        childTail = childTail.next;
                    }
                    if (succ != null) {
                        childTail.next = succ;
                        succ.prev = childTail;
                    }
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}