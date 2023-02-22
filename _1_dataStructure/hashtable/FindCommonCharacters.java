// 给你一个字符串数组 <code>words</code> ，请你找出所有在 <code>words</code> 的每个字符串中都出现的共用字符（ <strong>包括重复字符</strong>），并以数组形式返回。你可以按 <strong>任意顺序</strong> 返回答案。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>words = ["bella","label","roller"]
//<strong>输出：</strong>["e","l","l"]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>words = ["cool","lock","cook"]
//<strong>输出：</strong>["c","o"]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= words.length &lt;= 100</code></li> 
// <li><code>1 &lt;= words[i].length &lt;= 100</code></li> 
// <li><code>words[i]</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 306</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.ArrayList;
import java.util.List;

// 1002.查找共用字符
// 开题时间：2022-12-25 09:37:30
public class FindCommonCharacters {
  public static void main(String[] args) {
    Solution solution = new FindCommonCharacters().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<String> commonChars9(String[] words) {
      int[] freq = new int[123];
      for (int i = 0; i < words[0].length(); i++)
        freq[words[0].charAt(i)]++;
      
      for (int i = 1; i < words.length; i++) {
        int[] tmp = new int[123];
        for (int j = 0; j < words[i].length(); j++) {
          char c = words[i].charAt(j);
          if (--freq[c] >= 0)
            tmp[c]++;
        }
        freq = tmp;
      }
      
      ArrayList<String> ans = new ArrayList<>();
      for (int i = 97; i < freq.length; i++)
        for (int j = 0; j < freq[i]; j++)
          ans.add(String.valueOf((char) i));
      
      return ans;
    }
    
    //☆☆☆☆☆ 哈希计数：两计数数组、元素取最小值
    public List<String> commonChars(String[] words) {
      int[] freq = new int[123];
      for (int i = 0; i < words[0].length(); i++)
        freq[words[0].charAt(i)]++;
      
      for (int i = 1; i < words.length; i++) {
        int[] tmp = new int[123];
        for (int j = 0; j < words[i].length(); j++)
          tmp[words[i].charAt(j)]++;
        for (int j = 97; j < freq.length; j++)
          freq[j] = Math.min(freq[j], tmp[j]);
      }
      
      ArrayList<String> ans = new ArrayList<>();
      for (int i = 97; i < freq.length; i++)
        for (int j = 0; j < freq[i]; j++)
          ans.add(String.valueOf((char) i));
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}