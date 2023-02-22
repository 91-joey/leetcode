//<p>字典&nbsp;<code>wordList</code> 中从单词 <code>beginWord</code><em>&nbsp;</em>和 <code>endWord</code> 的 <strong>转换序列 </strong>是一个按下述规格形成的序列
// <meta charset="UTF-8" />&nbsp;<code>beginWord -&gt; s<sub>1</sub>&nbsp;-&gt; s<sub>2</sub>&nbsp;-&gt; ... -&gt; s<sub>k</sub></code>：</p>
//
//<ul> 
// <li>每一对相邻的单词只差一个字母。</li> 
// <li>
//  <meta charset="UTF-8" />&nbsp;对于&nbsp;<code>1 &lt;= i &lt;= k</code>&nbsp;时，每个
//  <meta charset="UTF-8" />&nbsp;<code>s<sub>i</sub></code>&nbsp;都在
//  <meta charset="UTF-8" />&nbsp;<code>wordList</code>&nbsp;中。注意， <code>beginWord</code><em>&nbsp;</em>不需要在
//  <meta charset="UTF-8" />&nbsp;<code>wordList</code>&nbsp;中。
//  <meta charset="UTF-8" /></li> 
// <li><code>s<sub>k</sub>&nbsp;== endWord</code></li> 
//</ul>
//
//<p>给你两个单词<em> </em><code>beginWord</code><em>&nbsp;</em>和 <code>endWord</code> 和一个字典 <code>wordList</code> ，返回 <em>从&nbsp;<code>beginWord</code> 到&nbsp;<code>endWord</code> 的 <strong>最短转换序列</strong> 中的 <strong>单词数目</strong></em> 。如果不存在这样的转换序列，返回 <code>0</code> 。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//<strong>输出：</strong>5
//<strong>解释：</strong>一个最短转换序列是 "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog", 返回它的长度 5。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//<strong>输出：</strong>0
//<strong>解释：</strong>endWord "cog" 不在字典中，所以无法进行转换。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= beginWord.length &lt;= 10</code></li> 
// <li><code>endWord.length == beginWord.length</code></li> 
// <li><code>1 &lt;= wordList.length &lt;= 5000</code></li> 
// <li><code>wordList[i].length == beginWord.length</code></li> 
// <li><code>beginWord</code>、<code>endWord</code> 和 <code>wordList[i]</code> 由小写英文字母组成</li> 
// <li><code>beginWord != endWord</code></li> 
// <li><code>wordList</code> 中的所有字符串 <strong>互不相同</strong></li> 
//</ul>
//
//<div><li>👍 1188</li><li>👎 0</li></div>
package _1_dataStructure.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// 127.单词接龙
// 开题时间：2023-01-13 11:03:33
public class WordLadder {
  public static void main(String[] args) {
    Solution solution = new WordLadder().new Solution();
    System.out.println(solution.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int ladderLength9(String beginWord, String endWord, List<String> wordList) {
      HashSet<String> set = new HashSet<>(wordList);
      if (!set.contains(endWord))
        return 0;
      
      Queue<String> q = new LinkedList<>();
      q.offer(beginWord);
      int step = 1;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          String s = q.poll();
          
          if (s.equals(endWord))
            return step;
          
          Iterator<String> it = set.iterator();
          while (it.hasNext()) {
            String next = it.next();
            if (isSinglyDiff(s, next)) {
              it.remove();
              q.offer(next);
            }
          }
        }
        step++;
      }
      
      return 0;
    }
    
    HashSet<String> set1;
    HashSet<String> set2;
    
    public int ladderLength8(String beginWord, String endWord, List<String> wordList) {
      set1 = new HashSet<>(wordList);
      set2 = new HashSet<>(wordList);
      if (!set1.contains(endWord))
        return 0;
      
      return doubleBfs(beginWord, endWord, -1) + 1;
    }
    
    private boolean isSinglyDiff(String s1, String s2) {
      int diff = 0;
      for (int i = 0; i < s1.length(); i++)
        if (s1.charAt(i) != s2.charAt(i))
          if (++diff > 1)
            return false;
      return diff == 1;
    }
    
    public <T> int doubleBfs(T source, T target, int notFound) {
      if (target.equals(source))
        return 0;
      
      Queue<T> q1 = new LinkedList<>(), q2 = new LinkedList<>();
      Set<T> vis1 = new HashSet<>(), vis2 = new HashSet<>();
      q1.offer(source);
      vis1.add(source);
      q2.offer(target);
      vis2.add(target);
      int step1 = 0, step2 = 0;
      while (!q1.isEmpty() && !q2.isEmpty()) {
        boolean reachTarget;
        if (q1.size() <= q2.size()) {
          reachTarget = bfs(q1, vis1, vis2, set1);
          step1++;
        } else {
          reachTarget = bfs(q2, vis2, vis1, set2);
          step2++;
        }
        if (reachTarget)
          return step1 + step2;
      }
      
      return notFound;
    }
    
    public <T> boolean bfs(Queue<T> q, Set<T> cur, Set<T> other, Set<String> set) {
      for (int i = q.size(); i > 0; i--) {
        T poll = q.poll();
        
        for (T next : getNexts(poll, set)) {
          if (!cur.contains(next))
            if (other.contains(next))
              return true;
            else {
              q.offer(next);
              cur.add(next);
            }
        }
      }
      return false;
    }
    
    private <T> List<T> getNexts(T poll, Set<String> set) {
      ArrayList<T> ans = new ArrayList<>();
      Iterator<String> it = set.iterator();
      while (it.hasNext()) {
        String next = it.next();
        if (isSinglyDiff((String) poll, next)) {
          it.remove();
          ans.add((T) next);
        }
      }
      return ans;
    }
    
    // bfs优化（枚举单词的每一种可能变换  26*wordLen）
    public int ladderLength7(String beginWord, String endWord, List<String> wordList) {
      HashSet<String> set = new HashSet<>(wordList);
      if (!set.contains(endWord))
        return 0;
      
      Queue<String> q = new LinkedList<>();
      q.offer(beginWord);
      HashSet<String> vis = new HashSet<>();
      vis.add(beginWord);
      int step = 1;
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          String s = q.poll();
          
          if (s.equals(endWord))
            return step;
          
          for (int j = 0; j < s.length(); j++) {
            for (char c = 'a'; c <= 'z'; c++) {
              if (c == s.charAt(j)) continue;
              char[] cs = s.toCharArray();
              cs[j] = c;
              String next = new String(cs);
              if (set.contains(next) && !vis.contains(next)) {
                q.offer(next);
                vis.add(next);
              }
            }
          }
        }
        step++;
      }
      
      return 0;
    }
    
    
    Set<String> set;
    
    //☆☆☆☆☆ 双向BFS优化（枚举单词的每一种可能变换  26*wordLen）
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      set = new HashSet<>(wordList);
      if (!set.contains(endWord))
        return 0;
      
      return doubleBfs2(beginWord, endWord, -1) + 1;
    }
    
    
    public <T> int doubleBfs2(T source, T target, int notFound) {
      if (target.equals(source))
        return 0;
      
      Queue<T> q1 = new LinkedList<>(), q2 = new LinkedList<>();
      Set<T> vis1 = new HashSet<>(), vis2 = new HashSet<>();
      q1.offer(source);
      vis1.add(source);
      q2.offer(target);
      vis2.add(target);
      int step1 = 0, step2 = 0;
      while (!q1.isEmpty() && !q2.isEmpty()) {
        boolean reachTarget;
        if (q1.size() <= q2.size()) {
          reachTarget = bfs(q1, vis1, vis2);
          step1++;
        } else {
          reachTarget = bfs(q2, vis2, vis1);
          step2++;
        }
        if (reachTarget)
          return step1 + step2;
      }
      
      return notFound;
    }
    
    public <T> boolean bfs(Queue<T> q, Set<T> cur, Set<T> other) {
      for (int i = q.size(); i > 0; i--) {
        T poll = q.poll();
        
        for (T next : getNexts2(poll, cur)) {
          if (other.contains(next))
            return true;
          else {
            q.offer(next);
            cur.add(next);
          }
        }
      }
      return false;
    }
    
    private <T> List<T> getNexts2(T poll, Set<T> cur) {
      ArrayList<T> ans = new ArrayList<>();
      String s = (String) poll;
      for (int j = 0; j < s.length(); j++) {
        for (char c = 'a'; c <= 'z'; c++) {
          if (c == s.charAt(j)) continue;
          char[] cs = s.toCharArray();
          cs[j] = c;
          String next = new String(cs);
          if (set.contains(next) && !cur.contains(next))
            ans.add((T) next);
        }
      }
      return ans;
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}