//<p>给你一个排序后的字符列表 <code>letters</code> ，列表中只包含小写英文字母。另给出一个目标字母&nbsp;<code>target</code>，请你寻找在这一有序列表里比目标字母大的最小字母。</p>
//
//<p>在比较时，字母是依序循环出现的。举个例子：</p>
//
//<ul> 
// <li>如果目标字母 <code>target = 'z'</code> 并且字符列表为&nbsp;<code>letters = ['a', 'b']</code>，则答案返回&nbsp;<code>'a'</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入: </strong>letters = ["c", "f", "j"]，target = "a"
//<strong>输出:</strong> "c"
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> letters = ["c","f","j"], target = "c"
//<strong>输出:</strong> "f"
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> letters = ["c","f","j"], target = "d"
//<strong>输出:</strong> "f"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= letters.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>letters[i]</code>&nbsp;是一个小写字母</li> 
// <li><code>letters</code> 按非递减顺序排序</li> 
// <li><code>letters</code> 最少包含两个不同的字母</li> 
// <li><code>target</code> 是一个小写字母</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li></div></div><br><div><li>👍 247</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;

//744.寻找比目标字母大的最小字母
//开题时间：2022-11-02 16:38:51
public class FindSmallestLetterGreaterThanTarget {
    public static void main(String[] args) {
        Solution solution = new FindSmallestLetterGreaterThanTarget().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //w  xyz abc...w
        //手撕二分
        public char nextGreatestLetter(char[] letters, char target) {
            int l = 0, r = letters.length;
            while (l < r) {
                int mid = l + r >> 1;
                if (target < letters[mid])
                    r = mid;
                else
                    l = mid + 1;
            }
//            return r == letters.length ? letters[0] : letters[r];
            return letters[r % letters.length];
        }

        //无情API选手
        public char nextGreatestLetter2(char[] letters, char target) {
            int search = Arrays.binarySearch(letters, (char) (target + 1));
            return search >= 0 ? letters[search] : letters[(-search - 1) % letters.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}