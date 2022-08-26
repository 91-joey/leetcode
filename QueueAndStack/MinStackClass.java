//<p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>
//
//<p>实现 <code>MinStack</code> 类:</p>
//
//<ul> 
// <li><code>MinStack()</code> 初始化堆栈对象。</li> 
// <li><code>void push(int val)</code> 将元素val推入堆栈。</li> 
// <li><code>void pop()</code> 删除堆栈顶部的元素。</li> 
// <li><code>int top()</code> 获取堆栈顶部的元素。</li> 
// <li><code>int getMin()</code> 获取堆栈中的最小元素。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入：</strong>
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//<strong>输出：</strong>
//[null,null,null,null,-3,null,0,-2]
//
//<strong>解释：</strong>
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --&gt; 返回 -3.
//minStack.pop();
//minStack.top();      --&gt; 返回 0.
//minStack.getMin();   --&gt; 返回 -2.
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li> 
// <li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用</li> 
// <li><code>push</code>,&nbsp;<code>pop</code>,&nbsp;<code>top</code>, and&nbsp;<code>getMin</code>最多被调用&nbsp;<code>3 * 10<sup>4</sup></code>&nbsp;次</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>设计</li></div></div><br><div><li>👍 1380</li><li>👎 0</li></div>
package org.example.leetcode.problems.QueueAndStack;

import java.util.Stack;

//155.最小栈
//开题时间：2022-08-17 12:04:52
public class MinStackClass {
    public static void main(String[] args) {
        //Solution solution = new MinStack().new Solution();
        MinStack3 minStack3 = new MinStack3();
        minStack3.push(512);
        minStack3.push(-1024);
        minStack3.push(-1024);
        minStack3.push(512);

        minStack3.pop();
        System.out.println(minStack3.getMin());
        minStack3.pop();
        System.out.println(minStack3.getMin());
        minStack3.pop();
        System.out.println(minStack3.getMin());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    1.自解（时间换空间） 单元素栈
    class MinStack {
        Stack<Integer> data;

        public MinStack() {
            data = new Stack<>();
        }

        public void push(int val) {
            data.push(val);
        }

        public void pop() {
            data.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            int min = Integer.MAX_VALUE;
            for (Integer ele : data) {
                min = Math.min(min, ele);
            }
            return min;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
//leetcode submit region end(Prohibit modification and deletion)

//    2.他解（空间换时间） 单栈同步存储元素和最小值
    class MinStack2 {
        Stack<int[]> dataAndMins;

        public MinStack2() {
            dataAndMins = new Stack<>();
        }

        public void push(int val) {
            dataAndMins.push(new int[]{val, dataAndMins.empty() ?
                    val :
                    Math.min(val, dataAndMins.peek()[1])});
        }

        public void pop() {
            dataAndMins.pop();
        }

        public int top() {
            return dataAndMins.peek()[0];
        }

        public int getMin() {
            return dataAndMins.peek()[1];
        }
    }

    //    3.他解（空间换时间） 元素栈+辅助栈存储最小值（非同步）：空间稍小，时间稍大
    static class MinStack3 {
        Stack<Integer> data;
        Stack<Integer> mins;

        public MinStack3() {
            data = new Stack<>();
            mins = new Stack<>();
        }

        public void push(int val) {
            data.push(val);
            if (mins.empty() || val <= mins.peek()) {
                mins.push(val);
            }
        }

        public void pop() {
            Integer pop = data.pop();
            if (pop.equals(mins.peek())) {
                mins.pop();
            }
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }

}