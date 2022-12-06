//<p>给你一个字符串 <code>word</code> ，该字符串由数字和小写英文字母组成。</p>
//
//<p>请你用空格替换每个不是数字的字符。例如，<code>"a123bc34d8ef34"</code> 将会变成 <code>" 123&nbsp; 34 8&nbsp; 34"</code> 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）：<code>"123"</code>、<code>"34"</code>、<code>"8"</code> 和 <code>"34"</code> 。</p>
//
//<p>返回对 <code>word</code> 完成替换后形成的 <strong>不同</strong> 整数的数目。</p>
//
//<p>只有当两个整数的 <strong>不含前导零</strong> 的十进制表示不同， 才认为这两个整数也不同。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>word = "a<strong>123</strong>bc<strong>34</strong>d<strong>8</strong>ef<strong>34</strong>"
//<strong>输出：</strong>3
//<strong>解释：</strong>不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>word = "leet<strong>1234</strong>code<strong>234</strong>"
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>word = "a<strong>1</strong>b<strong>01</strong>c<strong>001</strong>"
//<strong>输出：</strong>1
//<strong>解释：</strong>"1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= word.length &lt;= 1000</code></li> 
// <li><code>word</code> 由数字和小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 53</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;

//1805.字符串中不同整数的数目
//开题时间：2022-12-06 13:11:03
public class NumberOfDifferentIntegersInAString {
    public static void main(String[] args) {
        Solution solution = new NumberOfDifferentIntegersInAString().new Solution();
        System.out.println(Integer.valueOf("001"));
        System.out.println("new BigInteger(\"001\") = " + new BigInteger("001"));
//        System.out.println(solution.numDifferentIntegers("xtimt5kqkz9osexe56ezwwninlyeeqsq5m99904os3ygs12t31n1et4uwzmt5kvv6teisobuxt10k33v1aaxysg4y8nsivxdp3fo9dr7x58m8uc4ofm41ai77u8cvzr5r3s97f5otns59ubqk57xwl00xsp9w2oodt6yxcbscloyr9c2su8gca1ly6rrjufm25luhxhesxwn7bk1as9na4cbabxk"));
        System.out.println(solution.numDifferentIntegers("a1b01c001"));
//        System.out.println(solution.numDifferentIntegers("a123bc34d8ef34"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    import java.math.BigInteger;

    class Solution {
        public int numDifferentIntegers9(String word) {
            return (int) Arrays.stream(word.split("\\p{Lower}+"))
                    .filter(s -> !s.isEmpty())
                    .map(BigInteger::new)
                    .distinct()
                    .count();
        }

        public int numDifferentIntegers8(String word) {
            String[] split = word.split("\\p{Lower}+");
            HashSet<String> set = new HashSet<>();
            for (String s : split)
                if (!s.isEmpty()) {
                    int i = 0;
                    while (i < s.length() && s.charAt(i) == '0')
                        i++;
                    if (i != s.length())
                        set.add(s.substring(i));
                    else
                        set.add("0");
                }
            return set.size();
        }


        public int numDifferentIntegers(String word) {
            HashSet<String> set = new HashSet<>();
            int n = word.length();
            for (int l = 0, r; l < n; l = r + 1) {
                while (l < n && !Character.isDigit(word.charAt(l)))
                    l++;

                r = l + 1;
                while (r < n && Character.isDigit(word.charAt(r)))
                    r++;

                while (r - l > 1 && word.charAt(l) == '0')
                    l++;

                if (l < n)
                    set.add(word.substring(l, r));
            }
            return set.size();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}