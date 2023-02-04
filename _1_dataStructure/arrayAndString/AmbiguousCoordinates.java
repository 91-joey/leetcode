//<p>我们有一些二维坐标，如&nbsp;<code>"(1, 3)"</code>&nbsp;或&nbsp;<code>"(2, 0.5)"</code>，然后我们移除所有逗号，小数点和空格，得到一个字符串<code>S</code>。返回所有可能的原始字符串到一个列表中。</p>
//
//<p>原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。</p>
//
//<p>最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。</p>
//
//<p>&nbsp;</p>
//
//<pre>
//<strong>示例 1:</strong>
//<strong>输入:</strong> "(123)"
//<strong>输出:</strong> ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
//</pre>
//
//<pre>
//<strong>示例 2:</strong>
//<strong>输入:</strong> "(00011)"
//<strong>输出:</strong> &nbsp;["(0.001, 1)", "(0, 0.011)"]
//<strong>解释:</strong> 
// 0.0, 00, 0001 或 00.01 是不被允许的。
//</pre>
//
//<pre>
//<strong>示例 3:</strong>
//<strong>输入:</strong> "(0123)"
//<strong>输出:</strong> ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
//</pre>
//
//<pre>
//<strong>示例 4:</strong>
//<strong>输入:</strong> "(100)"
//<strong>输出:</strong> [(10, 0)]
//<strong>解释:</strong> 
// 1.0 是不被允许的。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示: </strong></p>
//
//<ul> 
// <li><code>4 &lt;= S.length &lt;= 12</code>.</li> 
// <li><code>S[0]</code> = "(", <code>S[S.length - 1]</code> = ")", 且字符串&nbsp;<code>S</code>&nbsp;中的其他元素都是数字。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><li>👍 98</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.ArrayList;
import java.util.List;

// 816.模糊坐标
// 开题时间：2022-11-07 14:04:32
public class AmbiguousCoordinates {
  
  public static void main(String[] args) {
    Solution solution = new AmbiguousCoordinates().new Solution();
    System.out.println(solution.ambiguousCoordinates("(1010)"));
    //        System.out.println(solution.ambiguousCoordinates("(0101)"));
    //        System.out.println(solution.ambiguousCoordinates("(100)"));
    //        System.out.println(solution.ambiguousCoordinates("(00011)"));
    //        System.out.println(solution.ambiguousCoordinates("(123)"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<String> ambiguousCoordinates8(String s) {
      //[1,i) [i,len-1]
      ArrayList<String> ans = new ArrayList<>();
      ArrayList<String> left = new ArrayList<>();
      ArrayList<String> right = new ArrayList<>();
      
      int len = s.length();
      int lst = len - 1;
      for (int i = 2; i < lst; i++) {
        if (i == lst - 1 || (s.charAt(i) != '0'))
          right.add(s.substring(i));
        for (int j = i + 1; j < lst; j++)
          if (!(s.substring(i, j).length() > 1 && s.substring(i, j).startsWith("0")) && !s.substring(j, lst).endsWith("0"))
            right.add(s.substring(i, j) + "." + s.substring(j));
        
        if (i == 2 || (s.charAt(1) != '0'))
          left.add(s.substring(0, i) + ", ");
        for (int j = 2; j < i; j++)
          if (!(s.substring(1, j).length() > 1 && s.substring(1, j).startsWith("0")) && !s.substring(j, i).endsWith("0"))
            left.add(s.substring(0, j) + "." + s.substring(j, i) + ", ");
        
        for (String l : left)
          for (String r : right)
            ans.add(l.concat(r));
        
        left.clear();
        right.clear();
      }
      
      return ans;
    }
    
    /*
     * 1.去掉首尾的括号字符
     * 2.枚举逗号分隔左右坐标
     *       嵌套枚举小数点位置（也可无小数点），过滤条件
     *           - 整数：以下情况之一
     *               - 1位数
     *               - 2位数及以上时，首位不为0
     *           - 小数：以下情况全部满足
     *               - 整数部分：同整数
     *               - 小数部分：末位不为0
     * 3.将所有符合条件的二维坐标加入结果集
     */
    
    public static final char ZERO = '0';
    
    public List<String> ambiguousCoordinates(String s) {
      //[1,i) [i,len-1]
      ArrayList<String> ans = new ArrayList<>();
      ArrayList<String> left = new ArrayList<>();
      ArrayList<String> right = new ArrayList<>();
      
      char[] chars = s.toCharArray();
      int lst = s.length() - 1;
      for (int i = 2; i < lst; i++) {
        add(left, s, chars, 1, i);
        add(right, s, chars, i, lst);
        
        for (String l : left)
          for (String r : right)
            ans.add("(" + l + ", " + r + ")");
        
        left.clear();
        right.clear();
      }
      
      return ans;
    }
    
    private void add(ArrayList<String> list, String s, char[] chars, int start, int end) {
      if (end - start == 1 || (chars[start] != ZERO))
        list.add(s.substring(start, end));
      for (int j = start + 1; j < end; j++)
        if ((j - start == 1 || chars[start] != ZERO) && chars[end - 1] != ZERO)
          list.add(s.substring(start, j) + "." + s.substring(j, end));
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}