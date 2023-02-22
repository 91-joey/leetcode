//<p>给定一个整数数组&nbsp;<code>temperatures</code>&nbsp;，表示每天的温度，返回一个数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是指对于第 <code>i</code> 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用&nbsp;<code>0</code> 来代替。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>temperatures</code></span> = [73,74,75,71,69,72,76,73]
//<strong>输出:</strong>&nbsp;[1,1,4,2,1,1,0,0]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> temperatures = [30,40,50,60]
//<strong>输出:</strong>&nbsp;[1,1,1,0]
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> temperatures = [30,60,90]
//<strong>输出: </strong>[1,1,0]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;=&nbsp;temperatures.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>30 &lt;=&nbsp;temperatures[i]&nbsp;&lt;= 100</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>单调栈</li></div></div><br><div><li>👍 1259</li><li>👎 0</li></div>
package _1_dataStructure.queueAndStack;

import java.util.Arrays;
import java.util.Stack;

// 739.每日温度
// 开题时间：2022-08-18 11:00:27
public class DailyTemperatures {
  public static void main(String[] args) {
    Solution solution = new DailyTemperatures().new Solution();
    System.out.println(Arrays.toString(solution.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 1.暴力法   n^2 1
    public int[] dailyTemperatures(int[] temperatures) {
      int length = temperatures.length;
      outer:
      for (int i = 0; i < length; i++) {
        for (int j = i + 1; j < length; j++) {
          if (temperatures[i] < temperatures[j]) {
            temperatures[i] = j - i;
            continue outer;
          }
        }
        temperatures[i] = 0;
      }
      return temperatures;
    }
    
    // 2.官解一：暴力    m*n m
    public int[] dailyTemperatures2(int[] temperatures) {
      int length = temperatures.length;
      int[] ans = new int[length];
      int[] next = new int[101];
      Arrays.fill(next, Integer.MAX_VALUE);
      for (int i = length - 1; i >= 0; --i) {
        int warmerIndex = Integer.MAX_VALUE;
        for (int t = temperatures[i] + 1; t <= 100; ++t) {
          if (next[t] < warmerIndex) {
            warmerIndex = next[t];
          }
        }
        if (warmerIndex < Integer.MAX_VALUE) {
          ans[i] = warmerIndex - i;
        }
        next[temperatures[i]] = i;
      }
      return ans;
    }
    
    // 3.官解一（空间优化）：暴力    m*n m
    public int[] dailyTemperatures3(int[] temperatures) {
      int length = temperatures.length;
      // 30 <= temperatures[i] <= 100
      // 记录每个温度第一次出现的下标
      int[] next = new int[71];
      Arrays.fill(next, Integer.MAX_VALUE);
      for (int i = length - 1; i >= 0; --i) {
        int warmerIndex = Integer.MAX_VALUE;
        for (int t = temperatures[i] + 1 - 30; t <= next.length - 1; t++) {
          if (next[t] < warmerIndex) {
            warmerIndex = next[t];
          }
        }
        next[temperatures[i] - 30] = i;
        if (warmerIndex < Integer.MAX_VALUE) {
          temperatures[i] = warmerIndex - i;
        } else {
          temperatures[i] = 0;
        }
      }
      return temperatures;
    }
    
    // 4.官解二：单调栈 n   n
    public int[] dailyTemperatures4(int[] temperatures) {
      Stack<Integer> idxes = new Stack<>();
      for (int i = 0; i < temperatures.length; i++) {
        while (!idxes.empty() && temperatures[idxes.peek()] < temperatures[i]) {
          int peek = idxes.peek();
          temperatures[peek] = i - peek;
          idxes.pop();
        }
        idxes.push(i);
      }
      
      for (int idxAndTemperature : idxes) {
        temperatures[idxAndTemperature] = 0;
      }
      
      return temperatures;
    }
    
    // 5.高分解
    public int[] dailyTemperatures5(int[] temperatures) {
      int length = temperatures.length;
      int[] ans = new int[length];
      ans[length - 1] = 0;
      for (int i = length - 2; i >= 0; i--) {
        // key1: j += ans[j]
        for (int j = i + 1; j < length; j += ans[j]) {
          if (temperatures[i] < temperatures[j]) {
            ans[i] = j - i;
            break;
            // key2: if (ans[j] == 0)
          } else if (ans[j] == 0) {
            ans[i] = 0;
            break;
          }
        }
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}