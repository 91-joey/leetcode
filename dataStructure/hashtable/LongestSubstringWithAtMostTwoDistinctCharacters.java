//给你一个字符串 <code>s</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含 <strong>两个不同字符</strong> 的最长子串，并返回该子串的长度。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "eceba"
//<strong>输出：</strong>3
//<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ccaabbb"
//<strong>输出：</strong>5
//<strong>解释：</strong>满足题目要求的子串是 "aabbb" ，长度为 5 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code> 由英文字母组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 182</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.hashtable;

import java.util.*;

//159.至多包含两个不同字符的最长子串
//开题时间：2022-09-07 13:11:11
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithAtMostTwoDistinctCharacters().new Solution();
//        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct4("cdaba"));
//        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("ababffzzeee"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //滑动窗口（hashmap） n   1
        public int lengthOfLongestSubstringTwoDistinct2(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int l = 0, r = 0;
            while (r < s.length()) {
                map.merge(s.charAt(r++), 1, Integer::sum);
                if (map.size() > 2)
                    map.compute(s.charAt(l++), (character, integer) -> integer == 1 ? null : integer - 1);
            }
            return r - l;
        }


        //自解：滑动窗口（hashmap） n   1
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int l = 0, r = 0, idx = Integer.MAX_VALUE;

            int length = s.length();
            while (r < length) {
                char cr = s.charAt(r);
                map.put(cr, r);
                if (map.size() > 2) {
                    Character minChar = null;
                    //优化：可用Collections.min()代替
                    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                        Integer value = entry.getValue();
                        if (value < idx) {
                            idx = value;
                            //多余: minChar=s.charAt(idx)
                            minChar = entry.getKey();
                        }
                    }
                    map.remove(minChar);
                    int limit = r + idx - l;
                    for (int i = r + 1; i <= Math.min(length - 1, limit); i++) {
                        map.put(s.charAt(i), i);
                    }
                    r = limit;
                    l = idx + 1;
                    idx = l;
                }
                r++;
            }

            return r - l;
        }

        //官解：滑动窗口（hashmap） n   1
        public int lengthOfLongestSubstringTwoDistinct3(String s) {
            Map<Character, Integer> map = new HashMap<>();
            int length = s.length();
            int l = 0, r = 0, max = 1;

            while (r < length) {
                map.put(s.charAt(r), r);
                if (map.size() > 2) {
                    max = Math.max(max, r - l);

                    int del_idx = Collections.min(map.values());
                    map.remove(s.charAt(del_idx));
                    l = del_idx + 1;
                }
                r++;
            }

            max = Math.max(max, length - l);
            return max;
        }

        //高分解：滑动窗口（数组） n   1
        public int lengthOfLongestSubstringTwoDistinct4(String s) {
            int[] map = new int[123];
            char[] chars = s.toCharArray();
            int length = s.length();
            int l = 0, r = 0, cnt = 0, max = 1;

            while (r < length) {
                int i = chars[r];
                if (map[i] == 0) {
                    cnt++;
                }
                map[i]++;

                if (cnt > 2) {
                    max = Math.max(max, r - l);

                    while (cnt > 2) {
                        if (map[chars[l]] == 1) {
                            cnt--;
                        }
                        map[chars[l]]--;
                        l++;
                    }
                }
                r++;
            }

            max = Math.max(max, length - l);
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}