//<p>不使用任何内建的哈希表库设计一个哈希集合（HashSet）。</p>
//
//<p>实现 <code>MyHashSet</code> 类：</p>
//
//<ul> 
// <li><code>void add(key)</code> 向哈希集合中插入值 <code>key</code> 。</li> 
// <li><code>bool contains(key)</code> 返回哈希集合中是否存在这个值 <code>key</code> 。</li> 
// <li><code>void remove(key)</code> 将给定值 <code>key</code> 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。</li> 
//</ul> &nbsp;
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入：</strong>
//["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
//[[], [1], [2], [1], [3], [2], [2], [2], [2]]
//<strong>输出：</strong>
//[null, null, null, true, false, null, true, null, false]
//
//<strong>解释：</strong>
//MyHashSet myHashSet = new MyHashSet();
//myHashSet.add(1);      // set = [1]
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(1); // 返回 True
//myHashSet.contains(3); // 返回 False ，（未找到）
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(2); // 返回 True
//myHashSet.remove(2);   // set = [1]
//myHashSet.contains(2); // 返回 False ，（已移除）</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= key &lt;= 10<sup>6</sup></code></li> 
// <li>最多调用 <code>10<sup>4</sup></code> 次 <code>add</code>、<code>remove</code> 和 <code>contains</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>链表</li><li>哈希函数</li></div></div><br><div><li>👍 256</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.hashtable;

import java.util.Arrays;
import java.util.LinkedList;

//705.设计哈希集合
//开题时间：2022-09-03 10:34:49
public class DesignHashset {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //1.自解（自定义链表）
    class MyHashSet {
        private final int size = 10000;
        private final Node[] table = new Node[size];

        public MyHashSet() {
            Arrays.fill(table, new Node(-1));
        }

        public void add(int key) {
            Node n = table[hash(key)];
            for (; n.next != null; n = n.next) {
                if (n.key == key) {
                    return;
                }
            }
            if (n.key == key) {
                return;
            }
            n.next = new Node(key);
        }

        public void remove(int key) {
            for (Node n = table[hash(key)]; n.next != null; n = n.next) {
                if (n.next.key == key) {
                    n.next = n.next.next;
                    return;
                }
            }
        }

        public boolean contains(int key) {
            for (Node n = table[hash(key)]; n != null; n = n.next) {
                if (n.key == key) {
                    return true;
                }
            }
            return false;
        }

        public int hash(int key) {
            return key % size;
        }
    }


    //2.自解（库链表）
    class MyHashSet2 {
        private final int base = 769;
        private final LinkedList[] table = new LinkedList[base];

        public MyHashSet2() {
            Arrays.fill(table, new LinkedList<Integer>());
        }

        public void add(int key) {
            if (!contains(key)) {
                table[hash(key)].offer(key);
            }
        }

        public void remove(int key) {
            table[hash(key)].remove(Integer.valueOf(key));
        }

        public boolean contains(int key) {
            return table[hash(key)].contains(key);
        }

        public int hash(int key) {
            return key % base;
        }
    }

    public class Node {
        public int key;
        public Node next;

        public Node(int key) {
            this.key = key;
        }
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
//leetcode submit region end(Prohibit modification and deletion)
}