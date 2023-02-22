//<p><strong>全字母句</strong> 指包含英语字母表中每个字母至少一次的句子。</p>
//
//<p>给你一个仅由小写英文字母组成的字符串 <code>sentence</code> ，请你判断&nbsp;<code>sentence</code> 是否为 <strong>全字母句</strong> 。</p>
//
//<p>如果是，返回<em> </em><code>true</code> ；否则，返回<em> </em><code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>sentence = "thequickbrownfoxjumpsoverthelazydog"
//<strong>输出：</strong>true
//<strong>解释：</strong><span><code>sentence</code></span> 包含英语字母表中每个字母至少一次。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>sentence = "leetcode"
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= sentence.length &lt;= 1000</code></li> 
// <li><code>sentence</code> 由小写英语字母组成</li> 
//</ul>
//
//<div><li>👍 42</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.HashSet;

// 1832.判断句子是否为全字母句
// 开题时间：2022-12-13 10:07:26
public class CheckIfTheSentenceIsPangram {
  public static void main(String[] args) {
    Solution solution = new CheckIfTheSentenceIsPangram().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean checkIfPangram9(String sentence) {
      HashSet<Character> set = new HashSet<>();
      for (int i = 0; i < sentence.length(); i++) {
        set.add(sentence.charAt(i));
        if (set.size() == 26)
          return true;
      }
      return false;
    }
    
    public boolean checkIfPangram8(String sentence) {
      boolean[] exists = new boolean[123];
      int cnt = 0;
      for (int i = 0; i < sentence.length(); i++) {
        char c = sentence.charAt(i);
        if (!exists[c]) {
          exists[c] = true;
          cnt++;
        }
        if (cnt == 26)
          return true;
      }
      return false;
    }
    
    public boolean checkIfPangram(String sentence) {
      for (int i = 0, cnt = 0, target = (1 << 26) - 1; i < sentence.length(); i++) {
        cnt |= 1 << (sentence.charAt(i) - 'a');
        if (cnt == target)
          return true;
      }
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}