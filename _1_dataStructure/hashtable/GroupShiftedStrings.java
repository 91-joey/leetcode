//<p>给定一个字符串，对该字符串可以进行 “移位” 的操作，也就是将字符串中每个字母都变为其在字母表中后续的字母，比如：<code>"abc" -&gt; "bcd"</code>。这样，我们可以持续进行 “移位” 操作，从而生成如下移位序列：</p>
//
//<pre>"abc" -&gt; "bcd" -&gt; ... -&gt; "xyz"</pre>
//
//<p>给定一个包含仅小写字母字符串的列表，将该列表中所有满足&nbsp;“移位” 操作规律的组合进行分组并返回。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：</strong><span><code>["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"]</code></span>
//<strong>输出：</strong>
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]
//<strong>解释：</strong>可以认为字母表首尾相接，所以 'z' 的后续为 'a'，所以 ["az","ba"] 也满足 “移位” 操作规律。</pre>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 93</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 249.移位字符串分组
// 开题时间：2022-09-08 11:11:10
public class GroupShiftedStrings {
  public static void main(String[] args) {
    Solution solution = new GroupShiftedStrings().new Solution();
    System.out.println(solution.groupStrings2(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 自解
    public List<List<String>> groupStrings(String[] strings) {
      Map<String, List<String>> map = new HashMap<>();
      
      for (String s : strings) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        
        // 计算偏移量
        int offset = 0;
        if (length > 0) {
          offset = chars[0] - 'a';
          chars[0] = 'a';
        }
        
        // 移位至首字母为a
        for (int i = 1; i < length; i++) {
          int newC = chars[i] - offset;
          chars[i] = (char) (newC < 'a' ? newC + 26 : newC);
        }
        
        String key = new String(chars);
        List<String> list = map.getOrDefault(key, new ArrayList<>());
        list.add(s);
        map.put(key, list);
      }
      
      return new ArrayList<>(map.values());
    }
    
    // 简化
    public List<List<String>> groupStrings2(String[] strings) {
      Map<String, List<String>> map = new HashMap<>();
      
      for (String s : strings) {
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
          chars[i] = (char) ((chars[i] - chars[0] + 26) % 26 + 'a');
        }
        
        String key = new String(chars);
        List<String> list = map.getOrDefault(key, new ArrayList<>());
        list.add(s);
        map.put(key, list);
      }
      
      return new ArrayList<>(map.values());
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}