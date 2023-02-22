//<p>一个句子是由一些单词与它们之间的单个空格组成，且句子的开头和结尾没有多余空格。比方说，<code>"Hello World"</code>&nbsp;，<code>"HELLO"</code>&nbsp;，<code>"hello world hello world"</code>&nbsp;都是句子。每个单词都 <strong>只</strong>&nbsp;包含大写和小写英文字母。</p>
//
//<p>如果两个句子&nbsp;<code>sentence1</code> 和&nbsp;<code>sentence2</code>&nbsp;，可以通过往其中一个句子插入一个任意的句子（<strong>可以是空句子</strong>）而得到另一个句子，那么我们称这两个句子是 <strong>相似的</strong>&nbsp;。比方说，<code>sentence1 = "Hello my name is Jane"</code> 且&nbsp;<code>sentence2 = "Hello Jane"</code>&nbsp;，我们可以往 <code>sentence2</code>&nbsp;中&nbsp;<code>"Hello"</code> 和&nbsp;<code>"Jane"</code>&nbsp;之间插入&nbsp;<code>"my name is"</code>&nbsp;得到 <code>sentence1</code>&nbsp;。</p>
//
//<p>给你两个句子&nbsp;<code>sentence1</code> 和&nbsp;<code>sentence2</code>&nbsp;，如果<em>&nbsp;</em><code>sentence1</code> 和<em>&nbsp;</em><code>sentence2</code> 是相似的，请你返回&nbsp;<code>true</code>&nbsp;，否则返回&nbsp;<code>false</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>sentence1 = "My name is Haley", sentence2 = "My Haley"
//<b>输出：</b>true
//<b>解释：</b>可以往 sentence2 中 "My" 和 "Haley" 之间插入 "name is" ，得到 sentence1 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>sentence1 = "of", sentence2 = "A lot of words"
//<b>输出：</b>false
//<strong>解释：</strong>没法往这两个句子中的一个句子只插入一个句子就得到另一个句子。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><b>输入：</b>sentence1 = "Eating right now", sentence2 = "Eating"
//<b>输出：</b>true
//<b>解释：</b>可以往 sentence2 的结尾插入 "right now" 得到 sentence1 。
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><b>输入：</b>sentence1 = "Luky", sentence2 = "Lucccky"
//<b>输出：</b>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= sentence1.length, sentence2.length &lt;= 100</code></li> 
// <li><code>sentence1</code>&nbsp;和&nbsp;<code>sentence2</code>&nbsp;都只包含大小写英文字母和空格。</li> 
// <li><code>sentence1</code>&nbsp;和&nbsp;<code>sentence2</code>&nbsp;中的单词都只由单个空格隔开。</li> 
//</ul>
//
//<div><li>👍 32</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 1813.句子相似性 III
// 开题时间：2023-01-16 08:20:24
public class SentenceSimilarityIii {
  public static void main(String[] args) {
    Solution solution = new SentenceSimilarityIii().new Solution();
    //        System.out.println(solution.areSentencesSimilar("Ogn WtWj HneS", "Ogn WtWj HneS"));
    //        System.out.println(solution.areSentencesSimilar("Eating right now", "Eating"));
    System.out.println(solution.areSentencesSimilar("My name is Haley", "My Haley"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // minLen(s1,s2)^2
    public boolean areSentencesSimilar9(String sentence1, String sentence2) {
      if (sentence1.equals(sentence2))
        return true;
      String[] split1 = sentence1.split(" ");
      String[] split2 = sentence2.split(" ");
      if (split1.length <= split2.length)
        return helper2(split1, split2);
      else
        return helper2(split2, split1);
    }
    
    private boolean helper2(String[] split1, String[] split2) {
      out:
      for (int i = 0; i <= split1.length; i++) {
        for (int j = 0; j < i; j++)
          if (!split1[j].equals(split2[j]))
            continue out;
        for (int j = split1.length - 1, k = split2.length - 1; j >= i; j--, k--)
          if (!split1[j].equals(split2[k]))
            continue out;
        return true;
      }
      return false;
    }
    
    //☆☆☆☆☆ 双指针对撞 minLen(s1,s2)
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
      String[] split1 = sentence1.split(" ");
      String[] split2 = sentence2.split(" ");
      if (split1.length <= split2.length)
        return helper(split1, split2);
      else
        return helper(split2, split1);
    }
    
    private boolean helper(String[] split1, String[] split2) {
      int m = split1.length, n = split2.length;
      int l = 0, r1 = m - 1, r2 = n - 1;
      while (l < m && split1[l].equals(split2[l])) l++;
      while (l <= r1 && split1[r1].equals(split2[r2])) {
        r1--;
        r2--;
      }
      return l == r1 + 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}