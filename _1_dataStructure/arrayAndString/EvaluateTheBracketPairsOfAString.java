//<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它包含一些括号对，每个括号中包含一个 <strong>非空</strong>&nbsp;的键。</p>
//
//<ul> 
// <li>比方说，字符串&nbsp;<code>"(name)is(age)yearsold"</code>&nbsp;中，有&nbsp;<strong>两个</strong>&nbsp;括号对，分别包含键&nbsp;<code>"name"</code> 和&nbsp;<code>"age"</code>&nbsp;。</li> 
//</ul>
//
//<p>你知道许多键对应的值，这些关系由二维字符串数组&nbsp;<code>knowledge</code>&nbsp;表示，其中&nbsp;<code>knowledge[i] = [key<sub>i</sub>, value<sub>i</sub>]</code>&nbsp;，表示键&nbsp;<code>key<sub>i</sub></code>&nbsp;对应的值为&nbsp;<code>value<sub>i</sub></code><sub>&nbsp;</sub>。</p>
//
//<p>你需要替换 <strong>所有</strong>&nbsp;的括号对。当你替换一个括号对，且它包含的键为&nbsp;<code>key<sub>i</sub></code>&nbsp;时，你需要：</p>
//
//<ul> 
// <li>将&nbsp;<code>key<sub>i</sub></code>&nbsp;和括号用对应的值&nbsp;<code>value<sub>i</sub></code>&nbsp;替换。</li> 
// <li>如果从 <code>knowledge</code>&nbsp;中无法得知某个键对应的值，你需要将&nbsp;<code>key<sub>i</sub></code>&nbsp;和括号用问号&nbsp;<code>"?"</code>&nbsp;替换（不需要引号）。</li> 
//</ul>
//
//<p><code>knowledge</code>&nbsp;中每个键最多只会出现一次。<code>s</code>&nbsp;中不会有嵌套的括号。</p>
//
//<p>请你返回替换 <strong>所有</strong>&nbsp;括号对后的结果字符串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
//<b>输出：</b>"bobistwoyearsold"
//<strong>解释：</strong>
//键 "name" 对应的值为 "bob" ，所以将 "(name)" 替换为 "bob" 。
//键 "age" 对应的值为 "two" ，所以将 "(age)" 替换为 "two" 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>s = "hi(name)", knowledge = [["a","b"]]
//<b>输出：</b>"hi?"
//<b>解释：</b>由于不知道键 "name" 对应的值，所以用 "?" 替换 "(name)" 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
//<b>输出：</b>"yesyesyesaaa"
//<b>解释：</b>相同的键在 s 中可能会出现多次。
//键 "a" 对应的值为 "yes" ，所以将所有的 "(a)" 替换为 "yes" 。
//注意，不在括号里的 "a" 不需要被替换。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= knowledge.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>knowledge[i].length == 2</code></li> 
// <li><code>1 &lt;= key<sub>i</sub>.length, value<sub>i</sub>.length &lt;= 10</code></li> 
// <li><code>s</code>&nbsp;只包含小写英文字母和圆括号&nbsp;<code>'('</code>&nbsp;和&nbsp;<code>')'</code>&nbsp;。</li> 
// <li><code>s</code>&nbsp;中每一个左圆括号&nbsp;<code>'('</code>&nbsp;都有对应的右圆括号&nbsp;<code>')'</code>&nbsp;。</li> 
// <li><code>s</code>&nbsp;中每对括号内的键都不会为空。</li> 
// <li><code>s</code>&nbsp;中不会有嵌套括号对。</li> 
// <li><code>key<sub>i</sub></code>&nbsp;和&nbsp;<code>value<sub>i</sub></code>&nbsp;只包含小写英文字母。</li> 
// <li><code>knowledge</code>&nbsp;中的&nbsp;<code>key<sub>i</sub></code>&nbsp;不会重复。</li> 
//</ul>
//
//<div><li>👍 24</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//1807.替换字符串中的括号内容
//开题时间：2023-01-12 09:50:11
public class EvaluateTheBracketPairsOfAString {
    public static void main(String[] args) {
        Solution solution = new EvaluateTheBracketPairsOfAString().new Solution();
        System.out.println(solution.evaluate("(name)is(age)yearsold", List.of(List.of("name", "bob"), List.of("age", "two"))));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    import java.util.regex.Matcher;
//    import java.util.regex.Pattern;
    class Solution {
        //indexOf方法
        public String evaluate9(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>();
            for (List<String> entry : knowledge)
                map.put(entry.get(0), entry.get(1));

            StringBuilder sb = new StringBuilder();
            int pre = 0;
            for (int i = s.indexOf("("); i != -1; i = s.indexOf("(", pre)) {
                sb.append(s, pre, i);
                pre = s.indexOf(")", i + 1);
                sb.append(map.getOrDefault(s.substring(i + 1, pre++), "?"));
            }
            sb.append(s, pre, s.length());

            return sb.toString();
        }

        //手动遍历 + key标记
        public String evaluate8(String s, List<List<String>> knowledge) {
            HashMap<String, String> map = new HashMap<>();
            for (List<String> entry : knowledge)
                map.put(entry.get(0), entry.get(1));

            StringBuilder sb = new StringBuilder();
            StringBuilder key = new StringBuilder();
            boolean isKey = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(')
                    isKey = true;
                else if (c == ')') {
                    sb.append(map.getOrDefault(key.toString(), "?"));
                    key.setLength(0);
                    isKey = false;
                } else {
                    if (isKey)
                        key.append(c);
                    else
                        sb.append(c);
                }
            }

            return sb.toString();
        }

        //☆☆☆☆☆ 正则表达式之分组
        public String evaluate(String s, List<List<String>> knowledge) {
            Map<String, String> map = new HashMap<>();
            for (List<String> entry : knowledge)
                map.put(entry.get(0), entry.get(1));

            StringBuilder sb = new StringBuilder();
            Matcher matcher = Pattern.compile("\\((\\w+)\\)").matcher(s);
            while (matcher.find())
                matcher.appendReplacement(sb, map.getOrDefault(matcher.group(1), "?"));
            matcher.appendTail(sb);

            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}