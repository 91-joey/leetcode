//<p>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code> 。每个拨轮可以自由旋转：例如把 <code>'9'</code> 变为&nbsp;<code>'0'</code>，<code>'0'</code> 变为 <code>'9'</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>
//
//<p>锁的初始数字为 <code>'0000'</code> ，一个代表四个拨轮的数字的字符串。</p>
//
//<p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>
//
//<p>字符串 <code>target</code> 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入：</strong>deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//<strong>输出：</strong>6
//<strong>解释：</strong>
//可能的移动序列为 "0000" -&gt; "1000" -&gt; "1100" -&gt; "1200" -&gt; "1201" -&gt; "1202" -&gt; "0202"。
//注意 "0000" -&gt; "0001" -&gt; "0002" -&gt; "0102" -&gt; "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> deadends = ["8888"], target = "0009"
//<strong>输出：</strong>1
//<strong>解释：</strong>把最后一位反向旋转一次即可 "0000" -&gt; "0009"。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
//<strong>输出：</strong>-1
//<strong>解释：</strong>无法旋转到目标数字且不被锁定。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li> 
// <li><code><font face="monospace">deadends[i].length == 4</font></code></li> 
// <li><code><font face="monospace">target.length == 4</font></code></li> 
// <li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li> 
// <li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 526</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.LinkedList;
import java.util.Queue;

//752.打开转盘锁
//开题时间：2022-08-16 11:42:37
//1.自解（①int[] -> String②int[][][][] steps用于记录已访问节点和计算步数）
public class OpenTheLock {
    public static void main(String[] args) {
        Solution solution = new OpenTheLock().new Solution();
        System.out.println(-1 % 10);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private final int[][] ROTATIONS = {
                {1, 0, 0, 0},
                {-1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, -1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, -1, 0},
                {0, 0, 0, 1},
                {0, 0, 0, -1},
        };

        public int openLock(String[] deadends, String target) {
            int[][][][] steps = new int[10][10][10][10];
            Queue<int[]> queue = new LinkedList<>();
            int[] start = {0, 0, 0, 0};
            if(isInDeadends(deadends,start)){
                return -1;
            }
            queue.add(start);
            while (!queue.isEmpty()) {
                int[] head = queue.poll();
                String headString = "";
                for (int digit : head) {
                    headString += digit;
                }
                if (headString.equals(target)) {
                    return steps[head[0]][head[1]][head[2]][head[3]];
                }

                for (int[] rotation : ROTATIONS) {
//                    -1 -> 9
//                    10 -> 0
                    int a = getRotatedDigit(rotation, head, 0);
                    int b = getRotatedDigit(rotation, head, 1);
                    int c = getRotatedDigit(rotation, head, 2);
                    int d = getRotatedDigit(rotation, head, 3);
                    int[] child = {a, b, c, d};
                    if (!(a == 0 && b == 0 && c == 0 && d == 0) && steps[a][b][c][d] == 0 && !isInDeadends(deadends, child)) {
                        steps[a][b][c][d] = steps[head[0]][head[1]][head[2]][head[3]] + 1;
                        queue.add(child);
                    }
                }
            }
            return -1;
        }

        private int getRotatedDigit(int[] rotation, int[] start, int index) {
            return start[index] + rotation[index] == -1 ? 9 : (start[index] + rotation[index]) % 10;
        }

        private boolean isInDeadends(String[] deadends, int[] digits) {
            String s = "";
            for (int digit : digits) {
                s += digit;
            }
            for (String deadend : deadends) {
                if (s.equals(deadend)) {
                    return true;
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}