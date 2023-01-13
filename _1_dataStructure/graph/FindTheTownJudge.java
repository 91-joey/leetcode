//<p>小镇里有 <code>n</code> 个人，按从 <code>1</code> 到 <code>n</code> 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。</p>
//
//<p>如果小镇法官真的存在，那么：</p>
//
//<ol> 
// <li>小镇法官不会信任任何人。</li> 
// <li>每个人（除了小镇法官）都信任这位小镇法官。</li> 
// <li>只有一个人同时满足属性 <strong>1</strong> 和属性 <strong>2</strong> 。</li> 
//</ol>
//
//<p>给你一个数组 <code>trust</code> ，其中 <code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示编号为 <code>a<sub>i</sub></code> 的人信任编号为 <code>b<sub>i</sub></code> 的人。</p>
//
//<p>如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2, trust = [[1,2]]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, trust = [[1,3],[2,3]]
//<strong>输出：</strong>3
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3, trust = [[1,3],[2,3],[3,1]]
//<strong>输出：</strong>-1
//</pre>
//
//&nbsp;
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 1000</code></li> 
// <li><code>0 &lt;= trust.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>trust[i].length == 2</code></li> 
// <li><code>trust</code> 中的所有<code>trust[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> <strong>互不相同</strong></li> 
// <li><code>a<sub>i</sub> != b<sub>i</sub></code></li> 
// <li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li> 
//</ul>
//
//<div><li>👍 280</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.ArrayList;

//997.找到小镇的法官
//开题时间：2023-01-13 10:29:21
public class FindTheTownJudge {
    public static void main(String[] args) {
        Solution solution = new FindTheTownJudge().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findJudgeX(int n, int[][] trust) {
            ArrayList<Integer>[] g = new ArrayList[n + 1];
            for (int i = 1; i < n + 1; i++)
                g[i] = new ArrayList<>();

            for (int[] edge : trust)
                g[edge[0]].add(edge[1]);

            for (int i = 1; i < n + 1; i++)
                if (g[i].isEmpty())
                    return i;

            return -1;
        }

        //统计出入度
        public int findJudge(int n, int[][] trust) {
            int[] inDeg = new int[n + 1];
            int[] outDeg = new int[n + 1];
            for (int[] edge : trust) {
                inDeg[edge[1]]++;
                outDeg[edge[0]]++;
            }

            for (int i = 1; i < n + 1; i++)
                if (outDeg[i] == 0 && inDeg[i] == n - 1)
                    return i;

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}