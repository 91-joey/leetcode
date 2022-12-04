//<p>给你一个字符串 <code>s</code>，请你将 <code>s</code> 分割成一些子串，使每个子串都是回文。</p>
//
//<p>返回符合要求的 <strong>最少分割次数</strong> 。</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <p>&nbsp;</p> 
// </div>
//</div>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aab"
//<strong>输出：</strong>1
//<strong>解释：</strong>只需一次分割就可将&nbsp;<em>s </em>分割成 ["aa","b"] 这样两个回文子串。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "a"
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ab"
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 2000</code></li> 
// <li><code>s</code> 仅由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 634</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

//132.分割回文串 II
//开题时间：2022-12-03 17:06:17
public class PalindromePartitioningIi {
    public static void main(String[] args) {
        Solution solution = new PalindromePartitioningIi().new Solution();
//        System.out.println(solution.minCut("aaabaa"));
//        System.out.println(solution.minCut("ccaacabacb"));
        System.out.println(solution.minCut("fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi"));
//        System.out.println(solution.minCut("cabababcbc"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minCut(String s) {
            List<String> list = new ArrayList<>();
            list.add(s.substring(0, 1));

            for (int i = 1; i < s.length(); i++) {
                int size = list.size();
                String lst = list.get(size - 1);
                char c = s.charAt(i);
                if (lst.charAt(0) == c && isUnique(lst))
                    list.set(size - 1, lst + c);
                else if (size >= 2 && list.get(size - 2).charAt(list.get(size - 2).length() - 1) == c) {
                    String secondLst = list.get(size - 2);
                    String sWithoutLst = secondLst.substring(0, secondLst.length() - 1);
                    if (isUnique(secondLst)) {
                        list.set(size - 1, c + lst + c);
                        if (sWithoutLst.isEmpty())
                            list.remove(size - 2);
                        else
                            list.set(size - 2, sWithoutLst);
                    } else {
                        String a = secondLst + lst + c;
                        if (canFormPalin(a)) {
                            list.set(size - 2, a);
                            list.remove(size - 1);
                        } else
                            list.add(String.valueOf(c));
                    }
                } else
                    list.add(String.valueOf(c));
            }

//            for (String s1 : list) {
//                System.out.print(s1);
//                if (!canFormPalin(s1))
//                    System.out.println(s1);
//            }
            return list.size() - 1;
        }

        private boolean canFormPalin(String s) {
            for (int l = 0, r = s.length() - 1; l < r; l++, r--)
                if (s.charAt(l) != s.charAt(r))
                    return false;

            return true;
        }

        private boolean isUnique(String s) {
            char c = s.charAt(0);
            for (int i = 1; i < s.length(); i++)
                if (c != s.charAt(i))
                    return false;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}