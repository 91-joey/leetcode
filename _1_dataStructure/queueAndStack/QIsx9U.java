//<p>给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。</p>
//
//<p>实现 <code>MovingAverage</code> 类：</p>
//
//<ul> 
// <li><code>MovingAverage(int size)</code> 用窗口大小 <code>size</code> 初始化对象。</li> 
// <li><code>double next(int val)</code>&nbsp;成员函数 <code>next</code>&nbsp;每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 <code>size</code> 个值的移动平均值，即滑动窗口里所有数字的平均值。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入：</strong>
// inputs = ["MovingAverage", "next", "next", "next", "next"]
// inputs = [[3], [1], [10], [3], [5]]
//<strong>输出：</strong>
//[null, 1.0, 5.5, 4.66667, 6.0]
//
//<strong>解释：</strong>
// MovingAverage movingAverage = new MovingAverage(3);
// movingAverage.next(1); // 返回 1.0 = 1 / 1
// movingAverage.next(10); // 返回 5.5 = (1 + 10) / 2
// movingAverage.next(3); // 返回 4.66667 = (1 + 10 + 3) / 3
// movingAverage.next(5); // 返回 6.0 = (10 + 3 + 5) / 3
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
//<p>&nbsp;</p>
//
//<p>
// <meta charset="UTF-8" />注意：本题与主站 346&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/moving-average-from-data-stream/">https://leetcode-cn.com/problems/moving-average-from-data-stream/</a></p>
//
//<div><li>👍 84</li><li>👎 0</li></div>
package _1_dataStructure.queueAndStack;

import java.util.LinkedList;
import java.util.Queue;

// 剑指 Offer II 041.滑动窗口的平均值
// 开题时间：2022-12-31 12:08:00
public class QIsx9U {
  public static void main(String[] args) {
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  //☆☆☆☆☆ 队列
  class MovingAverage9 {
    int sum = 0;
    int size;
    Queue<Integer> q = new LinkedList<>();
    
    /**
     * Initialize your data structure here.
     */
    public MovingAverage9(int size) {
      this.size = size;
    }
    
    public double next(int val) {
      q.offer(val);
      sum += val;
      if (q.size() > size)
        sum -= q.poll();
      return (double) sum / q.size();
    }
  }
  
  // 数组模拟队列
  class MovingAverage {
    int sum, i, j;
    int size;
    int[] arr = new int[10010];
    
    /**
     * Initialize your data structure here.
     */
    public MovingAverage(int size) {
      this.size = size;
    }
    
    public double next(int val) {
      sum += arr[j++] = val;
      if (j - i > size)
        sum -= arr[i++];
      return (double) sum / (j - i);
    }
  }
  /**
   * Your MovingAverage object will be instantiated and called as such:
   * MovingAverage obj = new MovingAverage(size);
   * double param_1 = obj.next(val);
   */
  // leetcode submit region end(Prohibit modification and deletion)
}