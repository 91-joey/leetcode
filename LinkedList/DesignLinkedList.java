//<p>设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：<code>val</code>&nbsp;和&nbsp;<code>next</code>。<code>val</code>&nbsp;是当前节点的值，<code>next</code>&nbsp;是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性&nbsp;<code>prev</code>&nbsp;以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。</p>
//
//<p>在链表类中实现这些功能：</p>
//
//<ul> 
// <li>get(index)：获取链表中第&nbsp;<code>index</code>&nbsp;个节点的值。如果索引无效，则返回<code>-1</code>。</li> 
// <li>addAtHead(val)：在链表的第一个元素之前添加一个值为&nbsp;<code>val</code>&nbsp;的节点。插入后，新节点将成为链表的第一个节点。</li> 
// <li>addAtTail(val)：将值为&nbsp;<code>val</code> 的节点追加到链表的最后一个元素。</li> 
// <li>addAtIndex(index,val)：在链表中的第&nbsp;<code>index</code>&nbsp;个节点之前添加值为&nbsp;<code>val</code>&nbsp; 的节点。如果&nbsp;<code>index</code>&nbsp;等于链表的长度，则该节点将附加到链表的末尾。如果 <code>index</code> 大于链表长度，则不会插入节点。如果<code>index</code>小于0，则在头部插入节点。</li> 
// <li>deleteAtIndex(index)：如果索引&nbsp;<code>index</code> 有效，则删除链表中的第&nbsp;<code>index</code> 个节点。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>MyLinkedList linkedList = new MyLinkedList();
//linkedList.addAtHead(1);
//linkedList.addAtTail(3);
//linkedList.addAtIndex(1,2);   //链表变为1-&gt; 2-&gt; 3
//linkedList.get(1);            //返回2
//linkedList.deleteAtIndex(1);  //现在链表是1-&gt; 3
//linkedList.get(1);            //返回3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>所有<code>val</code>值都在&nbsp;<code>[1, 1000]</code>&nbsp;之内。</li> 
// <li>操作次数将在&nbsp;&nbsp;<code>[1, 1000]</code>&nbsp;之内。</li> 
// <li>请不要使用内置的 LinkedList 库。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>链表</li></div></div><br><div><li>👍 539</li><li>👎 0</li></div>
package org.example.leetcode.problems.LinkedList;

//707.设计链表
//开题时间：2022-08-26 12:21:24
public class DesignLinkedList {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        linkedList.addAtTail(3);
//        linkedList.addAtIndex(3, 0);
//        linkedList.deleteAtIndex(2);
//        linkedList.addAtHead(6);
//        linkedList.addAtTail(4);
//        linkedList.get(4);
//        linkedList.addAtHead(4);
//        linkedList.addAtIndex(5, 0);
//        linkedList.addAtHead(6);
        linkedList.addAtTail(1);
        linkedList.addAtTail(3);
        System.out.println(linkedList.get(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class MyLinkedList {
        private int val;
        private MyLinkedList head;
        private MyLinkedList next;
        private int size = 0;

        public MyLinkedList() {
        }

        public MyLinkedList(int val) {
            this.val = val;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            MyLinkedList target = head;

            for (int i = 0; i < index; i++) {
                target = target.next;
            }

            return target.val;
        }

        public void addAtHead(int val) {
            MyLinkedList newItem = new MyLinkedList(val);
            newItem.next = head;
            head = newItem;
            size++;
        }

        public void addAtTail(int val) {
            if (size == 0) {
                addAtHead(val);
            } else {
                MyLinkedList newItem = new MyLinkedList(val);
                MyLinkedList tail = head;
                for (int i = 0; i < size - 1; i++) {
                    tail = tail.next;
                }
                tail.next = newItem;
                size++;
            }
        }

        public void addAtIndex(int index, int val) {
            if (index <= 0) {
                addAtHead(val);
            } else if (index == size) {
                addAtTail(val);
            } else if (index < size) {
                MyLinkedList newItem = new MyLinkedList(val);
                MyLinkedList prevNewItem = head;

                for (int i = 0; i < index - 1; i++) {
                    prevNewItem = prevNewItem.next;
                }
                //1.新元素指向下一个元素
                newItem.next = prevNewItem.next;
                //2.上一个元素指向新元素
                prevNewItem.next = newItem;

                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (index == 0) {
                head = head.next;
                size--;
            } else if (0 < index && index < size) {
                MyLinkedList prevNewItem = head;

                for (int i = 0; i < index - 1; i++) {
                    prevNewItem = prevNewItem.next;
                }
                prevNewItem.next = prevNewItem.next.next;
                size--;
            }
        }
    }


    //    总结两点：
//        链表是链表节点的集合，所以链表和链表节点应该分开定义，我之前给定义在一块了。
//        用哨兵节点作为伪头，简化插入和删除真头时真头指针的变化
    class MyLinkedList2 {
        private int size;
        private final MyLinkedListNode head;

        public MyLinkedList2() {
            size = 0;
            head = new MyLinkedListNode();
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            } else {
                MyLinkedListNode target = head;
                for (int i = 0; i <= index; i++) {
                    target = target.next;
                }
                return target.val;
            }
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index <= size) {
                if (index < 0) {
                    index = 0;
                }
                MyLinkedListNode newNode = new MyLinkedListNode(val);
                MyLinkedListNode prevNewNode = head;
                for (int i = 0; i < index; i++) {
                    prevNewNode = prevNewNode.next;
                }
                newNode.next = prevNewNode.next;
                prevNewNode.next = newNode;
                size++;
            }
        }

        public void deleteAtIndex(int index) {
            if (0 <= index && index < size) {
                MyLinkedListNode prevDeletedNode = head;
                for (int i = 0; i < index; i++) {
                    prevDeletedNode = prevDeletedNode.next;
                }
                prevDeletedNode.next = prevDeletedNode.next.next;
                size--;
            }
        }
    }

    class MyLinkedListNode {
        int val;
        MyLinkedListNode next;

        public MyLinkedListNode() {
        }

        public MyLinkedListNode(int val) {
            this.val = val;
        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
//leetcode submit region end(Prohibit modification and deletion)
}