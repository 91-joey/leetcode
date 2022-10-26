//<p>给定 <code>s</code> 和 <code>t</code> 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 <code>true</code> 。<code>#</code> 代表退格字符。</p>
//
//<p><strong>注意：</strong>如果对空文本输入退格字符，文本继续为空。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ab#c", t = "ad#c"
//<strong>输出：</strong>true
//<strong>解释：</strong>s 和 t 都会变成 "ac"。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ab##", t = "c#d#"
//<strong>输出：</strong>true
//<strong>解释：</strong>s 和 t 都会变成 ""。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "a#c", t = "b"
//<strong>输出：</strong>false
//<strong>解释：</strong>s 会变成 "c"，但 t 仍然是 "b"。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length, t.length &lt;= 200</code></li> 
// <li><code>s</code> 和 <code>t</code> 只含有小写字母以及字符 <code>'#'</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>你可以用 <code>O(n)</code> 的时间复杂度和 <code>O(1)</code> 的空间复杂度解决该问题吗？</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>双指针</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 536</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.LinkedList;

//844.比较含退格的字符串
//开题时间：2022-10-26 10:16:50
public class BackspaceStringCompare {
    public static void main(String[] args) {
        Solution solution = new BackspaceStringCompare().new Solution();
//        System.out.println(solution.backspaceCompare("ab##", "c#d#"));
//        System.out.println(solution.backspaceCompare("xywrrmp", "xywrrmu#p"));
//        System.out.println(solution.backspaceCompare2("y#fo##f", "y#f#o##f"));
//        System.out.println(solution.backspaceCompare("bxj##tw", "bxj###tw"));
//        System.out.println(solution.backspaceCompare("a##c", "#a#c"));
//        System.out.println(solution.backspaceCompare("bbbextm", "bbb#extm"));
//        System.out.println(solution.backspaceCompare("nzp#o#g", "b#nzp#o#g"));
        System.out.println(solution.backspaceCompare("rheyggodcclgstf", "#rheyggodcclgstf"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean backspaceCompare(String s, String t) {
            int i = s.length() - 1, j = t.length() - 1;
            for (; i >= 0 && j >= 0; i--, j--) {
                int cntI = -1, cntJ = -1;
                if (s.charAt(i) == '#') {
                    cntI = 1;
                    while (i > 0 && cntI >= 0)
                        cntI += s.charAt(--i) == '#' ? 1 : -1;
                }
                if (t.charAt(j) == '#') {
                    cntJ = 1;
                    while (j > 0 && cntJ >= 0)
                        cntJ += t.charAt(--j) == '#' ? 1 : -1;
                }
                if (cntI == -1 && cntJ == -1) {
                    if (s.charAt(i) != t.charAt(j))
                        return false;
                } else
                    return cntI >= 0 && cntJ >= 0;

            }
            if (i == j)
                return true;
            else {
                int idx;
                String str;
                if (i < j) {
                    idx = j;
                    str = t;
                } else {
                    idx = i;
                    str = s;
                }
                if (str.charAt(idx) != '#')
                    return false;
                int cnt = 1;
                while (idx > 0 && cnt >= 0)
                    cnt += str.charAt(--idx) == '#' ? 1 : -1;
                return cnt >= 0;
            }
        }

        //栈 m+n+x   m+n
        public boolean backspaceCompare2(String s, String t) {
            LinkedList<Character> stackS = new LinkedList<>();
            LinkedList<Character> stackT = new LinkedList<>();
            toStack(s, stackS);
            toStack(t, stackT);
            if (stackS.size() != stackT.size())
                return false;
            else
                while (!stackS.isEmpty())
                    if (stackS.pop() != stackT.pop())
                        return false;
            return true;
        }

        private void toStack(String s, LinkedList<Character> stackS) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '#') {
                    if (!stackS.isEmpty())
                        stackS.pop();
                } else
                    stackS.push(c);
            }
        }

        //StringBuilder 作栈
        public boolean backspaceCompare3(String s, String t) {
            return reBuild(s).equals(reBuild(t));
        }

        private String reBuild(String s) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '#') {
                    if (sb.length() > 0)
                        sb.deleteCharAt(sb.length() - 1);
                } else
                    sb.append(c);
            }

            return sb.toString();
        }

        //官解双指针
        public boolean backspaceCompare4(String s, String t) {
            //i >= 0 || j >= 0，这里「||」用的妙，如果用「&&」，最后还得再重复写一层“收尾”逻辑。
            for (int i = s.length() - 1, j = t.length() - 1, skipS = 0, skipT = 0; i >= 0 || j >= 0; i--, j--) {
                while (i >= 0) {
                    if (s.charAt(i) == '#') {
                        skipS++;
                        i--;
                    } else if (skipS > 0) {
                        skipS--;
                        i--;
                    } else {
                        //这里并没有再 skipS-- ，如果用了那就变成了 -1 ，下一次遍历得重新初始化为 0。
                        break;
                    }
                }
                while (j >= 0) {
                    if (t.charAt(j) == '#') {
                        skipT++;
                        j--;
                    } else if (skipT > 0) {
                        skipT--;
                        j--;
                    } else {
                        break;
                    }
                }

                if (i >= 0 && j >= 0) {
                    if (s.charAt(i) != t.charAt(j))
                        return false;
                } else
                    return !(i >= 0 || j >= 0);
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}