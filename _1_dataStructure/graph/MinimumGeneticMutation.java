//<p>基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 <code>'A'</code>、<code>'C'</code>、<code>'G'</code> 和 <code>'T'</code> 之一。</p>
//
//<p>假设我们需要调查从基因序列&nbsp;<code>start</code> 变为 <code>end</code> 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。</p>
//
//<ul> 
// <li>例如，<code>"AACCGGTT" --&gt; "AACCGGTA"</code> 就是一次基因变化。</li> 
//</ul>
//
//<p>另有一个基因库 <code>bank</code> 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 <code>bank</code> 中）</p>
//
//<p>给你两个基因序列 <code>start</code> 和 <code>end</code> ，以及一个基因库 <code>bank</code> ，请你找出并返回能够使&nbsp;<code>start</code> 变化为 <code>end</code> 所需的最少变化次数。如果无法完成此基因变化，返回 <code>-1</code> 。</p>
//
//<p>注意：起始基因序列&nbsp;<code>start</code> 默认是有效的，但是它并不一定会出现在基因库中。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>start.length == 8</code></li> 
// <li><code>end.length == 8</code></li> 
// <li><code>0 &lt;= bank.length &lt;= 10</code></li> 
// <li><code>bank[i].length == 8</code></li> 
// <li><code>start</code>、<code>end</code> 和 <code>bank[i]</code> 仅由字符 <code>['A', 'C', 'G', 'T']</code> 组成</li> 
//</ul>
//
//<div><li>👍 233</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.*;

//433.最小基因变化
//开题时间：2023-01-12 11:58:24
public class MinimumGeneticMutation {
    public static void main(String[] args) {
        Solution solution = new MinimumGeneticMutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //bfs
        public int minMutation(String startGene, String endGene, String[] bank) {
            boolean isEndGeneIncluded = false;
            for (String s : bank)
                if (s.equals(endGene)) {
                    isEndGeneIncluded = true;
                    break;
                }
            if (!isEndGeneIncluded)
                return -1;

            Queue<String> q = new LinkedList<>();
            q.offer(startGene);
            Set<String> set = new HashSet<>(Arrays.asList(bank));

            int step = 0;
            while (!q.isEmpty()) {
                for (int i = q.size(); i > 0; i--) {
                    String poll = q.poll();
                    if (poll.equals(endGene))
                        return step;

                    Iterator<String> it = set.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (isOneMutation(poll, next)) {
                            q.offer(next);
                            it.remove();
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        private boolean isOneMutation(String start, String end) {
            int mutation = 0;
            for (int i = 0; i < start.length(); i++)
                if (start.charAt(i) != end.charAt(i))
                    if (++mutation > 1)
                        return false;
            return mutation == 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}