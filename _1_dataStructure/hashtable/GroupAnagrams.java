//<p>给你一个字符串数组，请你将 <strong>字母异位词</strong> 组合在一起。可以按任意顺序返回结果列表。</p>
//
//<p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> strs = <span><code>["eat", "tea", "tan", "ate", "nat", "bat"]</code></span>
//<strong>输出: </strong>[["bat"],["nat","tan"],["ate","eat","tea"]]</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> strs = <span><code>[""]</code></span>
//<strong>输出: </strong>[[""]]
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> strs = <span><code>["a"]</code></span>
//<strong>输出: </strong>[["a"]]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= strs[i].length &lt;= 100</code></li> 
// <li><code>strs[i]</code>&nbsp;仅包含小写字母</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 1254</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.*;

//49.字母异位词分组
//开题时间：2022-09-08 08:27:49
public class GroupAnagrams {
    public static void main(String[] args) {
        Solution solution = new GroupAnagrams().new Solution();
        System.out.println(solution.groupAnagrams2(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}));
        int product = 1;
        int product2 = 1;
        for (int i = 0; i < 34; i++) {
            product *= 2;
            System.out.println("product = " + product);
        }
        for (int i = 0; i < 34; i++) {
            product2 *= 3;
            System.out.println("product2 = " + product2);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //自解（排序为键）
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                map.merge(new String(chars), new ArrayList<>(List.of(str)), (strings, strings2) -> {
                    strings.addAll(strings2);
                    return strings;
                });
            }

            return new ArrayList<>(map.values());
        }

        //官解1（排序为键）
        public List<List<String>> groupAnagramsGJ1(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);

                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }

            return new ArrayList<>(map.values());
        }

        //官解2（计数为键）
        public List<List<String>> groupAnagramsGJ2(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                int[] cnts = new int[26];
                for (char c : chars) {
                    cnts[c - 'a']++;
                }

                StringBuilder keyBuilder = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    if (cnts[i] != 0) {
                        keyBuilder.append((char) ('a' + i));
                        keyBuilder.append(cnts[i]);
                    }
                }
                String key = keyBuilder.toString();

                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }

            return new ArrayList<>(map.values());
        }

        //高分解：（质数乘法为键）
        //备注：此方法仍有hash冲突: 2^32=0 2^64=0 2^96=0
        public List<List<String>> groupAnagrams2(String[] strs) {
            Map<Integer, List<String>> map = new HashMap<>();

            for (String str : strs) {
                char[] chars = str.toCharArray();
                int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                        31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
                int product = 1;
                for (char c : chars) {
                    product *= primes[c - 'a'];
                    //2^32=0
                    if (product == 0) { // 处理溢出
                        product = 103;
                    }
                }

                List<String> list = map.getOrDefault(product, new ArrayList<>());
                list.add(str);
                map.put(product, list);
            }

            return new ArrayList<>(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}