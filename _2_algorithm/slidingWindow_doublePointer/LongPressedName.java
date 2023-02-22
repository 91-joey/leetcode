//<p>你的朋友正在使用键盘输入他的名字&nbsp;<code>name</code>。偶尔，在键入字符&nbsp;<code>c</code>&nbsp;时，按键可能会被<em>长按</em>，而字符可能被输入 1 次或多次。</p>
//
//<p>你将会检查键盘输入的字符&nbsp;<code>typed</code>。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回&nbsp;<code>True</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>name = "alex", typed = "aaleex"
//<strong>输出：</strong>true
//<strong>解释：</strong>'alex' 中的 'a' 和 'e' 被长按。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>name = "saeed", typed = "ssaaedd"
//<strong>输出：</strong>false
//<strong>解释：</strong>'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= name.length, typed.length &lt;= 1000</code></li> 
// <li><code>name</code> 和&nbsp;<code>typed</code>&nbsp;的字符都是小写字母</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 250</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 925.长按键入
// 开题时间：2022-10-27 15:02:46
public class LongPressedName {
  public static void main(String[] args) {
    Solution solution = new LongPressedName().new Solution();
    System.out.println(solution.isLongPressedName("pyplrz", "ppyypllr"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isLongPressedName(String name, String typed) {
      int len1 = name.length();
      int len2 = typed.length();
      if (len1 > len2)
        return false;
      
      char[] chars1 = name.toCharArray();
      char[] chars2 = typed.toCharArray();
      int i = 0, j = 0;
      for (; i < len1 && j < len2; i++, j++) {
        if (chars1[i] != chars2[j]) {
          while (0 < j && j < len2 && chars2[j - 1] == chars2[j])
            j++;
          if (j == len2 || chars1[i] != chars2[j])
            return false;
        }
      }
      
      if (i < len1)
        return false;
      while (j < len2) {
        if (chars2[j - 1] != chars2[j])
          return false;
        j++;
      }
      return true;
    }
    
    //☆☆☆☆☆ 双指针（优雅精简）
    public boolean isLongPressedName2(String name, String typed) {
      int len1 = name.length();
      int len2 = typed.length();
      if (len1 > len2)
        return false;
      
      char[] chars1 = name.toCharArray();
      char[] chars2 = typed.toCharArray();
      int i = 0, j = 0;
      
      while (j < len2) {
        if (i < len1 && chars1[i] == chars2[j]) {
          i++;
          j++;
        } else if (0 < j && chars2[j - 1] == chars2[j]) {
          j++;
        } else {
          return false;
        }
      }
      
      return i == len1;
    }
    
    
    // 计数
    public boolean isLongPressedName3(String name, String typed) {
      int len1 = name.length();
      int len2 = typed.length();
      if (len1 > len2)
        return false;
      
      char[] chars1 = name.toCharArray();
      char[] chars2 = typed.toCharArray();
      int i = 0, j = 0;
      
      while (i < len1) {
        char c = chars1[i];
        int cnt = 0;
        do {
          i++;
          cnt++;
        } while (i < len1 && chars1[i] == c);
        
        while (j < len2 && chars2[j] == c) {
          j++;
          cnt--;
        }
        
        if (cnt > 0)
          return false;
      }
      
      return j == len2;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}