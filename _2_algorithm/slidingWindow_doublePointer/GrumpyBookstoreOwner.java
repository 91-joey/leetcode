//<p>有一个书店老板，他的书店开了&nbsp;<code>n</code>&nbsp;分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 <code>n</code> 的整数数组 <code>customers</code> ，其中 <code>customers[i]</code> 是在第 <code>i</code> 分钟开始时进入商店的顾客数量，所有这些顾客在第 <code>i</code> 分钟结束后离开。</p>
//
//<p>在某些时候，书店老板会生气。 如果书店老板在第 <code>i</code> 分钟生气，那么 <code>grumpy[i] = 1</code>，否则 <code>grumpy[i] = 0</code>。</p>
//
//<p>当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。</p>
//
//<p>书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续&nbsp;<code>minutes</code>&nbsp;分钟不生气，但却只能使用一次。</p>
//
//<p>请你返回 <em>这一天营业下来，最多有多少客户能够感到满意</em> 。<br /> &nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
//<strong>输出：</strong>16
//<strong>解释：</strong>书店老板在最后 3 分钟保持冷静。
// 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>customers = [1], grumpy = [0], minutes = 1
//<strong>输出：</strong>1</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == customers.length == grumpy.length</code></li> 
// <li><code>1 &lt;= minutes &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= customers[i] &lt;= 1000</code></li> 
// <li><code>grumpy[i] == 0 or 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>滑动窗口</li></div></div><br><div><li>👍 238</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 1052.爱生气的书店老板
// 开题时间：2022-10-02 17:33:40
public class GrumpyBookstoreOwner {
  public static void main(String[] args) {
    Solution solution = new GrumpyBookstoreOwner().new Solution();
    System.out.println(solution.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
      int length = customers.length;
      int sumMax = 0;
      for (int i = 0; i < minutes; i++)
        sumMax += customers[i];
      for (int i = minutes; i < length; i++)
        if (grumpy[i] == 0)
          sumMax += customers[i];
      
      for (int i = minutes, sumCur = sumMax; i < length; i++) {
        sumCur += (grumpy[i] == 1 ? customers[i] : 0) -
            (grumpy[i - minutes] == 1 ? customers[i - minutes] : 0);
        sumMax = Math.max(sumMax, sumCur);
      }
      
      return sumMax;
    }
    
    public int maxSatisfiedGJ(int[] customers, int[] grumpy, int minutes) {
      int length = customers.length;
      int sumMax = 0;
      for (int i = 0; i < minutes; i++)
        sumMax += customers[i];
      for (int i = minutes; i < length; i++)
        //                if (grumpy[i] == 0)
        //                    sumMax += customers[i];
        sumMax += (1 - grumpy[i]) * customers[i];
      
      for (int i = minutes, sumCur = sumMax; i < length; i++) {
        sumCur += grumpy[i] * customers[i] -
            grumpy[i - minutes] * customers[i - minutes];
        sumMax = Math.max(sumMax, sumCur);
      }
      
      return sumMax;
    }
    
    // 三叶姐
    public int maxSatisfiedMTH(int[] customers, int[] grumpy, int minutes) {
      int length = customers.length;
      int sumMax = 0;
      // 前 minutes 分钟，「发动秘技！！」
      for (int i = 0; i < minutes; i++) {
        sumMax += customers[i];
        if (grumpy[i] == 0)
          customers[i] = 0;
      }
      for (int i = minutes; i < length; i++)
        if (grumpy[i] == 0) {
          sumMax += customers[i];
          customers[i] = 0;
        }
      
      for (int i = minutes, sumCur = sumMax; i < length; i++) {
        sumCur += customers[i] - customers[i - minutes];
        sumMax = Math.max(sumMax, sumCur);
      }
      
      return sumMax;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}