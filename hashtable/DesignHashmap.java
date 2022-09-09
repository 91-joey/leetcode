//<p>不使用任何内建的哈希表库设计一个哈希映射（HashMap）。</p>
//
//<p>实现 <code>MyHashMap</code> 类：</p>
//
//<ul> 
// <li><code>MyHashMap()</code> 用空映射初始化对象</li> 
// <li><code>void put(int key, int value)</code> 向 HashMap 插入一个键值对 <code>(key, value)</code> 。如果 <code>key</code> 已经存在于映射中，则更新其对应的值 <code>value</code> 。</li> 
// <li><code>int get(int key)</code> 返回特定的 <code>key</code> 所映射的 <code>value</code> ；如果映射中不包含 <code>key</code> 的映射，返回 <code>-1</code> 。</li> 
// <li><code>void remove(key)</code> 如果映射中存在 <code>key</code> 的映射，则移除 <code>key</code> 和它所对应的 <code>value</code> 。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入</strong>：
//["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
//[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
//<strong>输出</strong>：
//[null, null, null, 1, -1, null, 1, null, -1]
//
//<strong>解释</strong>：
//MyHashMap myHashMap = new MyHashMap();
//myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
//myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
//myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
//myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
//myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
//myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= key, value &lt;= 10<sup>6</sup></code></li> 
// <li>最多调用 <code>10<sup>4</sup></code> 次 <code>put</code>、<code>get</code> 和 <code>remove</code> 方法</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>链表</li><li>哈希函数</li></div></div><br><div><li>👍 320</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import org.example.leetcode.problems.common.Entry;

import java.util.Arrays;
import java.util.LinkedList;

//706.设计哈希映射
//开题时间：2022-09-03 14:10:57
public class DesignHashmap {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MyHashMap {
        private final int base = 769;
        private final LinkedList[] table = new LinkedList[base];

        public MyHashMap() {
            Arrays.fill(table, new LinkedList<Entry<Integer,Integer>>());
        }

        public void put(int key, int value) {
            Entry<Integer, Integer> entry = getEntry(key);
            if (entry != null) {
                entry.value = value;
            } else {
                table[hash(key)].offer(new Entry<>(key, value));
            }
        }

        public int get(int key) {
            Entry<Integer,Integer> entry = getEntry(key);
            if (entry != null) {
                return entry.value;
            } else {
                return -1;
            }
        }

        public void remove(int key) {
            Entry<Integer, Integer> entry = getEntry(key);
            if (entry != null) {
                table[hash(key)].remove(entry);
            }
        }

        private Entry<Integer,Integer> getEntry(int key) {
            LinkedList<Entry<Integer,Integer>> bucket = table[hash(key)];
            for (Entry<Integer,Integer> entry : bucket) {
                if (entry.key == key) {
                    return entry;
                }
            }
            return null;
        }

        private int hash(int key) {
            return key % base;
        }
    }

    /**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
//leetcode submit region end(Prohibit modification and deletion)
}