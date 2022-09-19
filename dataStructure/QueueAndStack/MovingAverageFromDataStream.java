//<p>给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。</p>
//
//<p>实现 <code>MovingAverage</code> 类：</p>
//
//<ul> 
// <li><code>MovingAverage(int size)</code> 用窗口大小 <code>size</code> 初始化对象。</li> 
// <li><code>double next(int val)</code> 计算并返回数据流中最后 <code>size</code> 个值的移动平均值。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入：</strong>
//["MovingAverage", "next", "next", "next", "next"]
//[[3], [1], [10], [3], [5]]
//<strong>输出：</strong>
//[null, 1.0, 5.5, 4.66667, 6.0]
//
//<strong>解释：</strong>
//MovingAverage movingAverage = new MovingAverage(3);
//movingAverage.next(1); // 返回 1.0 = 1 / 1
//movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
//movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
//movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= size &lt;= 1000</code></li> 
// <li><code>-10<sup>5</sup> &lt;= val &lt;= 10<sup>5</sup></code></li> 
// <li>最多调用 <code>next</code> 方法 <code>10<sup>4</sup></code> 次</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>数组</li><li>数据流</li></div></div><br><div><li>👍 89</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;

//346.数据流中的移动平均值
//开题时间：2022-08-12 21:06:42
public class MovingAverageFromDataStream {
    public static void main(String[] args) {
//        MovingAverage movingAverage = new MovingAverage(3);
//        movingAverage.next(1); // 返回 1.0 = 1 / 1
//        movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
//        movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
//        movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MovingAverage {

        private final Queue<Integer> queue;
        private final int maxSize;
        private int sum;

        public MovingAverage(int size) {
            queue = new LinkedList<>();
            this.maxSize = size;
        }

        public double next(int val) {
            queue.offer(val);
            sum += val;

            if (queue.size() > maxSize) {
                sum -= queue.poll();
            }
            return (double) sum / queue.size();
        }
    }

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
}