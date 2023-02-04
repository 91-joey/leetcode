//<p>设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出<strong>出现频率</strong>最高的元素。</p>
//
//<p>实现 <code>FreqStack</code>&nbsp;类:</p>
//
//<ul> 
// <li>
//  <meta charset="UTF-8" /><code>FreqStack()</code>&nbsp;构造一个空的堆栈。</li> 
// <li>
//  <meta charset="UTF-8" /><code>void push(int val)</code>&nbsp;将一个整数&nbsp;<code>val</code>&nbsp;压入栈顶。</li> 
// <li>
//  <meta charset="UTF-8" /><code>int pop()</code>&nbsp;删除并返回堆栈中出现频率最高的元素。 
//  <ul> 
//   <li>如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。</li> 
//  </ul> </li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>
//["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
//[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
//<strong>输出：</strong>[null,null,null,null,null,null,null,5,7,5,4]
//<strong>解释：</strong>
// FreqStack = new FreqStack();
// freqStack.push (5);//堆栈为 [5]
// freqStack.push (7);//堆栈是 [5,7]
// freqStack.push (5);//堆栈是 [5,7,5]
// freqStack.push (7);//堆栈是 [5,7,5,7]
// freqStack.push (4);//堆栈是 [5,7,5,7,4]
// freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
// freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
// freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
// freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
// freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= val &lt;= 10<sup>9</sup></code></li> 
// <li><code>push</code>&nbsp;和 <code>pop</code>&nbsp;的操作数不大于 <code>2 * 10<sup>4</sup></code>。</li> 
// <li>输入保证在调用&nbsp;<code>pop</code>&nbsp;之前堆栈中至少有一个元素。</li> 
//</ul>
//
//<div><li>👍 310</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// 895.最大频率栈
// 开题时间：2022-11-30 13:56:00
public class MaximumFrequencyStack {
  public static void main(String[] args) {
    FreqStack s = new FreqStack();
    //        s.push(5);
    //        s.push(7);
    //        s.push(5);
    //        s.push(7);
    //        s.push(4);
    //        s.push(5);
    //        System.out.println(s.pop());
    //        System.out.println(s.pop());
    //        System.out.println(s.pop());
    //        System.out.println(s.pop());
    s.push(4);
    s.push(0);
    s.push(9);
    s.push(3);
    s.push(4);
    s.push(2);
    System.out.println(s.pop());
    s.push(6);
    System.out.println(s.pop());
    s.push(1);
    System.out.println(s.pop());
    s.push(1);
    System.out.println(s.pop());
    s.push(4);
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
    System.out.println(s.pop());
  }
}
// leetcode submit region begin(Prohibit modification and deletion)
//    class FreqStackX {
//        TreeMap<Integer, TreeMap<Deque<Integer>, Integer>> freq2indices2val = new TreeMap<>();
//        HashMap<Integer, Integer> val2freq = new HashMap<>();
//        int idx = 0;
//
//        public FreqStackX() {
//        }
//
//        public void push(int val) {
//            Integer freq = val2freq.get(val);
//            if (freq == null) {
//                val2freq.put(val, 1);
//                if (!freq2indices2val.containsKey(1)) {
//                    addNewEntry(val, 1);
//                } else {
//                    TreeMap<Deque<Integer>, Integer> indices2val = freq2indices2val.get(freq);
//                    for (Map.Entry<Deque<Integer>, Integer> entry : indices2val.entrySet()) {
//                        if (entry.getValue() == val) {
//                            Deque<Integer> stack = entry.getKey();
//                            stack.push(idx++);
//                            if (!freq2indices2val.containsKey(freq + 1))
//                                addNewEntry(val, freq + 1);
//                            else
//                                freq2indices2val.get(freq + 1).put(stack, val);
//                            break;
//                        }
//                    }
//                    val2freq.compute(val, (k, v) -> v + 1);
//                }
//            } else {
//                for (Map.Entry<Deque<Integer>, Integer> entry : freq2indices2val.get(freq).entrySet()) {
//                    if (entry.getValue() == val) {
//                        Deque<Integer> stack = entry.getKey();
//                        stack.push(idx++);
//                        if (!freq2indices2val.containsKey(freq + 1))
//                            addNewEntry(val, freq + 1);
//                        else
//                            freq2indices2val.get(freq + 1).put(stack, val);
//                        break;
//                    }
//                }
//            }
//        }
//
//        private void addNewEntry(int val, int freq) {
//            TreeMap<Deque<Integer>, Integer> indices2val = new TreeMap<>(Comparator.comparingInt(Deque::peekFirst));
//            Deque<Integer> stack = new LinkedList<>();
//            stack.push(idx++);
//            indices2val.put(stack, val);
//            freq2indices2val.put(freq, indices2val);
//        }
//
//        public int pop() {
//            Integer freq = freq2indices2val.lastEntry().getKey() - 1;
//            TreeMap<Deque<Integer>, Integer> indices2val = freq2indices2val.lastEntry().getValue();
//            Map.Entry<Deque<Integer>, Integer> highest = indices2val.lastEntry();
//            highest.getKey().pop();
//            indices2val.remove(highest.getKey());
//
//            if (!freq2indices2val.containsKey(freq)) {
//                TreeMap<Deque<Integer>, Integer> map = new TreeMap<>(Comparator.comparingInt(Deque::peekFirst));
//                map.put(highest.getKey(), highest.getValue());
//                freq2indices2val.put(freq, map);
//            } else {
//                freq2indices2val.get(freq).put(highest.getKey(), highest.getValue());
//            }
//
//            Integer val = highest.getValue();
//            val2freq.compute(val, (k, v) -> v - 1);
//
//            return val;
//        }
//    }

class FreqStack {
  private final HashMap<Integer, Integer> val2freq = new HashMap<>();
  private final HashMap<Integer, Deque<Integer>> freq2vals = new HashMap<>();
  private int maxFreq = 1;
  
  public void push(int val) {
    val2freq.merge(val, 1, Integer::sum);
    
    Integer freq = val2freq.get(val);
    maxFreq = Math.max(maxFreq, freq);
    
    freq2vals.putIfAbsent(freq, new LinkedList<>());
    freq2vals.get(freq).push(val);
  }
  
  public int pop() {
    Integer pop = freq2vals.get(maxFreq).pop();
    
    if (freq2vals.get(maxFreq).isEmpty())
      maxFreq--;
    val2freq.merge(pop, -1, Integer::sum);
    
    return pop;
  }
}

class FreqStack9 {
  private final Map<Integer, Integer> val2freq = new HashMap<>();
  private final List<Deque<Integer>> freq2stack = new ArrayList<>();
  
  public void push(int val) {
    int freq = val2freq.getOrDefault(val, 0);
    if (freq == freq2stack.size()) // 这个元素的频率已经是目前最多的，现在又出现了一次
      freq2stack.add(new ArrayDeque<>()); // 那么必须创建一个新栈
    freq2stack.get(freq).push(val);
    val2freq.put(val, freq + 1); // 更新频率
  }
  
  public int pop() {
    int lst = freq2stack.size() - 1;
    int val = freq2stack.get(lst).pop(); // 弹出最右侧栈的栈顶
    if (freq2stack.get(lst).isEmpty()) // 栈为空
      freq2stack.remove(lst); // 删除
    val2freq.put(val, val2freq.get(val) - 1); // 更新频率
    return val;
  }
}
// leetcode submit region end(Prohibit modification and deletion)