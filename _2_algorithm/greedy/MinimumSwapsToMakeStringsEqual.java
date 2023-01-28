//<p>有两个长度相同的字符串&nbsp;<code>s1</code> 和&nbsp;<code>s2</code>，且它们其中&nbsp;<strong>只含有</strong>&nbsp;字符&nbsp;<code>"x"</code> 和&nbsp;<code>"y"</code>，你需要通过「交换字符」的方式使这两个字符串相同。</p>
//
//<p>每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。</p>
//
//<p>交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换&nbsp;<code>s1[i]</code> 和&nbsp;<code>s2[j]</code>，但不能交换&nbsp;<code>s1[i]</code> 和&nbsp;<code>s1[j]</code>。</p>
//
//<p>最后，请你返回使 <code>s1</code> 和 <code>s2</code> 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回&nbsp;<code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "xx", s2 = "yy"
//<strong>输出：</strong>1
//<strong>解释：
//</strong>交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "xy", s2 = "yx"
//<strong>输出：</strong>2
//<strong>解释：
//</strong>交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
//交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
//注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "xx", s2 = "xy"
//<strong>输出：</strong>-1
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre><strong>输入：</strong>s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
//<strong>输出：</strong>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s1.length, s2.length &lt;= 1000</code></li> 
// <li><code>s1, s2</code>&nbsp;只包含&nbsp;<code>'x'</code>&nbsp;或&nbsp;<code>'y'</code>。</li> 
//</ul>
//
//<div><li>👍 71</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

//1247.交换字符使得字符串相同
//开题时间：2023-01-25 11:09:57
public class MinimumSwapsToMakeStringsEqual {
    public static void main(String[] args) {
        Solution solution = new MinimumSwapsToMakeStringsEqual().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 贪心
         * 求使俩字符串相同的最小交换次数，那么原来相同的字符尽量不动、一次交换尽量消除更多的不同字符对
         * （最多消除 2 对，发生在不同字符对的「不同态」相同时，即 ① s1[i]=s1[j]=x,s2[i]=s2[j]=y 或 ② s1[i]=s1[j]=y,s2[i]=s2[j]=x）
         *
         * 若能使俩字符串相同，又因为交换不改变 x、y 在俩字符串中的整体数量，所以 cntX、cntY 同为偶数
         * 1.具体的，我们先尽量对「不同态」相同的字符对进行 对角交换 ，例如：
         *  s1 x x  东北向对角交换后变为 x y 西北向对角交换的效果是一样的 y x
         *  s2 y y                   x y                         y x
         * 2.cntX、cntY 同为偶数，原来相同的字符对所占的 cntX、cntY 也同为偶数，步骤一的 cntX、cntY 也同为偶数，所以剩下的 cntX、cntY 也同为偶数时，才能使俩字符串相同
         *  设「不同态」为 「s1[i]=x,s2[i]=y」为 cntXY,
         *    「不同态」为 「s1[i]=y,s2[i]=x」为 cntYX
         *  则对「不同态」相同的字符对进行 对角交换 的次数为 cntXY/2 + cntYX/2
         *  剩余的 cntXY%2、cntYX%2，只存在同为 0/1 的情况
         *  - 同为 0 ，则不再需要交换字符
         *  - 同为 1 ，此时有 2 种方案，两者等效：
         *      - 先对一对字符对进行「垂直交换」，再进行「对角交换」,共 2 次交换
         *          s1 x y  垂直交换后变为 y y 对角交换后变为 x y
         *          s2 y x              x x              x y
         *      - 借助 相同字符对 与一对 不同字符对 进行「对角交换」，再将 相同字符对（原来是） 与另一对 不同字符对 进行「对角交换」
         *        ，借助同为 x/y 的字符对都可以实现，以 同为 x 的字符对为例：
         *          s1 x x y  垂直交换后变为 x x x 对角交换后变为 x y x
         *          s2 x y x              y y x              x y x
         *
         * 代码实现时，我们可以统一式子、多用位运算，这里就不再详细展开了，代码见吧！！
         */
        public int minimumSwap(String s1, String s2) {
            int cntXY = 0, cntYX = 0;
            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 == 'x' && c2 == 'y')
                    cntXY++;
                else if (c1 == 'y' && c2 == 'x')
                    cntYX++;
            }
            return ((cntXY ^ cntYX) & 1) != 0 ?
                    -1 :
                    (cntXY + cntYX) / 2 + (cntXY & 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}