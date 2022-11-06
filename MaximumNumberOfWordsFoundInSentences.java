//<p>一个 <strong>句子</strong>&nbsp;由一些 <strong>单词</strong>&nbsp;以及它们之间的单个空格组成，句子的开头和结尾不会有多余空格。</p>
//
//<p>给你一个字符串数组&nbsp;<code>sentences</code>&nbsp;，其中&nbsp;<code>sentences[i]</code>&nbsp;表示单个 <strong>句子</strong>&nbsp;。</p>
//
//<p>请你返回单个句子里 <strong>单词的最多数目</strong>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>sentences = ["alice and bob love leetcode", "i think so too", <em><strong>"this is great thanks very much"</strong></em>]
//<b>输出：</b>6
//<b>解释：</b>
//- 第一个句子 "alice and bob love leetcode" 总共有 5 个单词。
//- 第二个句子 "i think so too" 总共有 4 个单词。
//- 第三个句子 "this is great thanks very much" 总共有 6 个单词。
//所以，单个句子中有最多单词数的是第三个句子，总共有 6 个单词。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>sentences = ["please wait", <em><strong>"continue to fight"</strong></em>, <em><strong>"continue to win"</strong></em>]
//<b>输出：</b>3
//<b>解释：</b>可能有多个句子有相同单词数。
//这个例子中，第二个句子和第三个句子（加粗斜体）有相同数目的单词数。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= sentences.length &lt;= 100</code></li> 
// <li><code>1 &lt;= sentences[i].length &lt;= 100</code></li> 
// <li><code>sentences[i]</code>&nbsp;只包含小写英文字母和&nbsp;<code>' '</code>&nbsp;。</li> 
// <li><code>sentences[i]</code>&nbsp;的开头和结尾都没有空格。</li> 
// <li><code>sentences[i]</code>&nbsp;中所有单词由单个空格隔开。</li> 
//</ul>
//
//<div><li>👍 16</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//2114.句子中的最多单词数
//开题时间：2022-11-06 09:14:34
public class MaximumNumberOfWordsFoundInSentences {
    public static void main(String[] args) {
        Solution solution = new MaximumNumberOfWordsFoundInSentences().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mostWordsFound2(String[] sentences) {
            int max = 0;
            for (String sentence : sentences) {
                int cur = 1;
                for (int i = 0; i < sentence.length(); i++)
                    if (sentence.charAt(i) == ' ')
                        cur++;
                max = Math.max(max, cur);
            }
            return max;
        }

        public int mostWordsFound(String[] sentences) {
            int max = 0;
            for (String sentence : sentences) {
                int cur = 1;
                for (int i = 0; (i = sentence.indexOf(' ', i + 1)) != -1; )
                    cur++;
                max = Math.max(max, cur);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}