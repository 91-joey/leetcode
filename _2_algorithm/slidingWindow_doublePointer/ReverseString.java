//<p>编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 <code>s</code> 的形式给出。</p>
//
//<p>不要给另外的数组分配额外的空间，你必须<strong><a href="https://baike.baidu.com/item/原地算法" target="_blank">原地</a>修改输入数组</strong>、使用 O(1) 的额外空间解决这一问题。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ["h","e","l","l","o"]
//<strong>输出：</strong>["o","l","l","e","h"]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ["H","a","n","n","a","h"]
//<strong>输出：</strong>["h","a","n","n","a","H"]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s[i]</code> 都是 <a href="https://baike.baidu.com/item/ASCII" target="_blank">ASCII</a> 码表中的可打印字符</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 677</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 344.反转字符串
// 开题时间：2022-10-23 18:18:42
public class ReverseString {
  public static void main(String[] args) {
    Solution solution = new ReverseString().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public void reverseString(char[] s) {
      for (int l = 0, r = s.length - 1; l < r; ) {
        char tmp = s[l];
        s[l++] = s[r];
        s[r--] = tmp;
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}