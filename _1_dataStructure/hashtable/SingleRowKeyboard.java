//<p>我们定制了一款特殊的键盘，所有的键都 <strong>排列在一行上</strong>&nbsp;。</p>
//
//<p>给定一个长度为 <code>26</code> 的字符串&nbsp;<code>keyboard</code>&nbsp;，来表示键盘的布局(索引从 <code>0</code> 到 <code>25</code> )。一开始，你的手指在索引 <code>0</code> 处。要输入一个字符，你必须把你的手指移动到所需字符的索引处。手指从索引&nbsp;<code>i</code>&nbsp;移动到索引&nbsp;<code>j</code>&nbsp;所需要的时间是&nbsp;<code>|i - j|</code>。</p>
//
//<p>您需要输入一个字符串&nbsp;<code>word</code>&nbsp;。写一个函数来计算用一个手指输入需要多少时间。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
//<strong>输出：</strong>4
//<strong>解释：</strong>从 0 号键移动到 2 号键来输出 'c'，又移动到 1 号键来输出 'b'，接着移动到 0 号键来输出 'a'。
// 总用时 = 2 + 1 + 1 = 4.
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
//<strong>输出：</strong>73
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>keyboard.length == 26</code></li> 
// <li><code>keyboard</code>&nbsp;按某种特定顺序排列，并包含每个小写英文字母一次。</li> 
// <li><code>1 &lt;= word.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>word[i]</code>&nbsp;为小写英文字母</li> 
//</ul>
//
//<div><li>👍 23</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

// 1165.单行键盘
// 开题时间：2022-11-17 15:57:39
public class SingleRowKeyboard {
  public static void main(String[] args) {
    Solution solution = new SingleRowKeyboard().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // hashmap
    public int calculateTime(String keyboard, String word) {
      int[] indices = new int[123];
      for (int i = 0; i < keyboard.length(); i++)
        indices[keyboard.charAt(i)] = i;
      
      int time = 0, i = 0;
      for (char c : word.toCharArray())
        time += Math.abs(i - (i = indices[c]));
      
      return time;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}