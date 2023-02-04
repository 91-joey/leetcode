//<p>给定字符串 <code>s</code>&nbsp;和字符串数组&nbsp;<code>words</code>, 返回&nbsp;&nbsp;<em><code>words[i]</code>&nbsp;中是<code>s</code>的子序列的单词个数</em>&nbsp;。</p>
//
//<p>字符串的 <strong>子序列</strong> 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。</p>
//
//<ul> 
// <li>例如， <code>“ace”</code> 是 <code>“abcde”</code> 的子序列。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "abcde", words = ["a","bb","acd","ace"]
//<strong>输出:</strong> 3
//<strong>解释:</strong> 有三个是&nbsp;s 的子序列的单词: "a", "acd", "ace"。
//</pre>
//
//<p><strong>Example 2:</strong></p>
//
//<pre>
//<strong>输入: </strong>s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
//<strong>输出:</strong> 2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= words.length &lt;= 5000</code></li> 
// <li><code>1 &lt;= words[i].length &lt;= 50</code></li> 
// <li><code>words[i]</code>和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font>&nbsp;都只由小写字母组成。</li> 
//</ul> 
//<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​</span></span></span>
//
//<div><li>👍 243</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 792.匹配子序列的单词数
// 开题时间：2022-11-17 08:32:24
public class NumberOfMatchingSubsequences {
  public static void main(String[] args) {
    Solution solution = new NumberOfMatchingSubsequences().new Solution();
    System.out.println(solution.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
    System.out.println(-1 >> 1);
    System.out.println(-7 >>> 1);
    System.out.println(-1 / 2);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 二分
    public int numMatchingSubseq9(String s, String[] words) {
      Map<Integer, List<Integer>> char2indices = new HashMap<>();
      for (int i = 'a'; i <= 'z'; i++)
        char2indices.put(i, new ArrayList<>());
      
      for (int i = 0; i < s.length(); i++)
        char2indices.get((int) s.charAt(i)).add(i);
      
      int cnt = 0;
      out:
      for (String word : words) {
        for (int i = 0, idx = -1; i < word.length(); i++) {
          List<Integer> list = char2indices.get((int) word.charAt(i));
          if (list.isEmpty())
            continue out;
          int l = 0, r = list.size() - 1;
          while (l < r) {
            int mid = l + r >> 1;
            if (idx < list.get(mid))
              r = mid;
            else
              l = mid + 1;
          }
          if (idx >= list.get(r))
            continue out;
          idx = list.get(r);
        }
        cnt++;
      }
      return cnt;
    }
    
    // 二分优化
    public int numMatchingSubseq8(String s, String[] words) {
      List<Integer>[] char2indices = new List[27];
      for (int i = 0; i < 27; i++)
        char2indices[i] = new ArrayList<>();
      
      for (int i = 0; i < s.length(); i++)
        char2indices[s.charAt(i) & 31].add(i);
      
      int cnt = 0;
      out:
      for (String word : words) {
        if (word.length() > s.length()) continue;
        for (int i = 0, idx = -1; i < word.length(); i++) {
          List<Integer> list = char2indices[word.charAt(i) & 31];
          if (list.isEmpty() || list.get(list.size() - 1) <= idx)
            continue out;
          idx = binarySearch(list, idx);
        }
        cnt++;
      }
      return cnt;
    }
    
    // TLE
    public int numMatchingSubseq7(String s, String[] words) {
      int len = s.length();
      int idx = 0;
      for (int i = 0; i < words.length; i++) {
        if (words[i].length() <= len)
          words[idx++] = words[i];
      }
      
      int[] pointers = new int[idx];
      int cnt = 0;
      for (int i = 0; i < len; i++) {
        char c = s.charAt(i);
        for (int j = 0; j < idx; j++) {
          if (pointers[j] >= words[j].length())
            continue;
          if (c == words[j].charAt(pointers[j])) {
            if (++pointers[j] == words[j].length()) {
              cnt++;
            }
          }
        }
      }
      return cnt;
    }
    
    // 分桶
    public int numMatchingSubseq6(String s, String[] words) {
      LinkedList<int[]>[] char2idxidx = new LinkedList[27];
      for (int i = 0; i < 27; i++)
        char2idxidx[i] = new LinkedList<>();
      
      for (int i = 0; i < words.length; i++)
        char2idxidx[words[i].charAt(0) & 31].add(new int[]{i, 0});
      
      int cnt = 0;
      for (int i = 0; i < s.length(); i++) {
        LinkedList<int[]> list = char2idxidx[s.charAt(i) & 31];
        for (int j = list.size() - 1; j >= 0; j--) {
          int[] idxidx = list.get(j);
          list.remove(j);
          if (idxidx[1] >= words[idxidx[0]].length() - 1)
            cnt++;
          else
            char2idxidx[words[idxidx[0]].charAt(idxidx[1] + 1) & 31].add(new int[]{idxidx[0], idxidx[1] + 1});
        }
      }
      return cnt;
    }
    
    // 分桶（优化）
    public int numMatchingSubseq(String s, String[] words) {
      Queue<int[]>[] char2idxidx = new Queue[26];
      for (int i = 0; i < 26; i++)
        char2idxidx[i] = new LinkedList<>();
      
      for (int i = 0; i < words.length; i++)
        char2idxidx[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
      
      int cnt = 0;
      for (int i = 0; i < s.length(); i++) {
        Queue<int[]> q = char2idxidx[s.charAt(i) - 'a'];
        for (int j = q.size(); j > 0; j--) {
          int[] idxidx = q.poll();
          int idx1 = idxidx[0];
          int idx2 = idxidx[1] + 1;
          if (idx2 >= words[idx1].length())
            cnt++;
          else
            char2idxidx[words[idx1].charAt(idx2) - 'a'].offer(new int[]{idx1, idx2});
        }
      }
      return cnt;
    }
    
    private int binarySearch(List<Integer> list, int target) {
      int l = 0, r = list.size() - 1;
      while (l < r) {
        int mid = l + r >> 1;
        if (target < list.get(mid))
          r = mid;
        else
          l = mid + 1;
      }
      return list.get(r);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}