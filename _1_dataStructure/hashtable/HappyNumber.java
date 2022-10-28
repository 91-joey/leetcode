//<p>编写一个算法来判断一个数 <code>n</code> 是不是快乐数。</p>
//
//<p><strong>「快乐数」</strong>&nbsp;定义为：</p>
//
//<ul> 
// <li>对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。</li> 
// <li>然后重复这个过程直到这个数变为 1，也可能是 <strong>无限循环</strong> 但始终变不到 1。</li> 
// <li>如果这个过程 <strong>结果为</strong>&nbsp;1，那么这个数就是快乐数。</li> 
//</ul>
//
//<p>如果 <code>n</code> 是 <em>快乐数</em> 就返回 <code>true</code> ；不是，则返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 19
//<strong>输出：</strong>true
//<strong>解释：
//</strong>1<sup>2</sup> + 9<sup>2</sup> = 82
//8<sup>2</sup> + 2<sup>2</sup> = 68
//6<sup>2</sup> + 8<sup>2</sup> = 100
//1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>数学</li><li>双指针</li></div></div><br><div><li>👍 1059</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.*;

//202.快乐数
//开题时间：2022-09-04 12:23:44
public class HappyNumber {
    public static void main(String[] args) {
        Solution solution = new HappyNumber().new Solution();
//        System.out.println(solution.isHappy(2));
        solution.getCycleNumbers();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//    2
//    4
//    16
//    37
//    58
//    89
//    145
//    42
//    20
//    4
        //1.哈希表 logn    logn
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (!set.contains(n)) {
                if (n == 1) return true;
                set.add(n);
                n = getNext(n);
            }
            return false;
        }

        //2.双指针 logn    1
        public boolean isHappy2(int n) {
            int slow = n;
            int fast = getNext(n);
            while (fast != 1 && slow != fast) {
                slow = getNext(slow);
                fast = getNext(getNext(fast));
            }
            return fast == 1;
        }

        //3.数学  logn    1
        private static Set<Integer> cycleNums = new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

        public boolean isHappy3(int n) {
            while (!cycleNums.contains(n)) {
                if (n == 1) return true;
                n = getNext(n);
            }
            return false;
        }

        private int getNext(int n) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            return sum;
        }

        public void getCycleNumbers() {
            List<Set<Integer>> sets = new ArrayList<>();
            for (int i = 2; i < 243; i++) {
                Set<Integer> set = new HashSet<>();
                int n = i;
                while (n != 1 && !set.contains(n)) {
                    set.add(n);
                    n = getNext(n);
                }
                if (n != 1) {
                    System.out.println(set);
                    sets.add(set);
                }
            }
            Set<Integer> set = sets.get(0);
            for (int i = 1; i < sets.size(); i++) {
                int finalI = i;
                set.removeIf(integer -> !sets.get(finalI).contains(integer));
            }
            System.out.println("common cycle : " + set);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}