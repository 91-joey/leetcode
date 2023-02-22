//<p>小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？</p>
//
//<p>输入的<code>guess</code>数组为 小A 每次的猜测，<code>answer</code>数组为 小B 每次的选择。<code>guess</code>和<code>answer</code>的长度都等于3。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>guess = [1,2,3], answer = [1,2,3]
//<strong>输出：</strong>3
//<strong>解释：</strong>小A 每次都猜对了。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>guess = [2,2,3], answer = [3,2,1]
//<strong>输出：</strong>1
//<strong>解释：</strong>小A 只猜对了第二次。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>限制：</strong></p>
//
//<ol> 
// <li><code>guess</code> 的长度 = 3</li> 
// <li><code>answer</code> 的长度 = 3</li> 
// <li><code>guess</code> 的元素取值为 <code>{1, 2, 3}</code> 之一。</li> 
// <li><code>answer</code> 的元素取值为 <code>{1, 2, 3}</code> 之一。</li> 
//</ol>
//
//<div><li>👍 148</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

// LCP 01.猜数字
// 开题时间：2022-11-10 20:57:12
public class GuessNumbers {
  public static void main(String[] args) {
    Solution solution = new GuessNumbers().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int game(int[] guess, int[] answer) {
      int cnt = 0;
      for (int i = 0; i < 3; i++)
        if (guess[i] == answer[i])
          cnt++;
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}