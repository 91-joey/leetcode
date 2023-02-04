//<p>给你一个整数数组&nbsp;<code>salary</code>&nbsp;，数组里每个数都是 <strong>唯一</strong>&nbsp;的，其中&nbsp;<code>salary[i]</code> 是第&nbsp;<code>i</code>&nbsp;个员工的工资。</p>
//
//<p>请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>salary = [4000,3000,1000,2000]
//<strong>输出：</strong>2500.00000
//<strong>解释：</strong>最低工资和最高工资分别是 1000 和 4000 。
// 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>salary = [1000,2000,3000]
//<strong>输出：</strong>2000.00000
//<strong>解释：</strong>最低工资和最高工资分别是 1000 和 3000 。
// 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>salary = [6000,5000,4000,3000,2000,1000]
//<strong>输出：</strong>3500.00000
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>salary = [8000,9000,2000,3000,6000,1000]
//<strong>输出：</strong>4750.00000
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= salary.length &lt;= 100</code></li> 
// <li><code>10^3&nbsp;&lt;= salary[i] &lt;= 10^6</code></li> 
// <li><code>salary[i]</code>&nbsp;是唯一的。</li> 
// <li>与真实值误差在&nbsp;<code>10^-5</code> 以内的结果都将视为正确答案。</li> 
//</ul>
//
//<div><li>👍 47</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort;

import java.util.Arrays;

// 1491.去掉最低工资和最高工资后的工资平均值
// 开题时间：2022-12-01 17:40:25
public class AverageSalaryExcludingTheMinimumAndMaximumSalary {
  public static void main(String[] args) {
    Solution solution = new AverageSalaryExcludingTheMinimumAndMaximumSalary().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public double average9(int[] salary) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      int sum = 0;
      for (int e : salary) {
        min = Math.min(min, e);
        max = Math.max(max, e);
        sum += e;
      }
      return (double) (sum - min - max) / (salary.length - 2);
    }
    
    public double average(int[] salary) {
      return (double) (
          Arrays.stream(salary).sum()
              - Arrays.stream(salary).min().getAsInt()
              - Arrays.stream(salary).max().getAsInt()
      ) / (salary.length - 2);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}