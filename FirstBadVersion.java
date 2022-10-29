//<p>你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。</p>
//
//<p>假设你有 <code>n</code> 个版本 <code>[1, 2, ..., n]</code>，你想找出导致之后所有版本出错的第一个错误的版本。</p>
//
//<p>你可以通过调用&nbsp;<code>bool isBadVersion(version)</code>&nbsp;接口来判断版本号 <code>version</code> 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5, bad = 4
//<strong>输出：</strong>4
//<strong>解释：</strong>
//<span><code>调用 isBadVersion(3) -&gt; false 
//调用 isBadVersion(5)&nbsp;-&gt; true 
//调用 isBadVersion(4)&nbsp;-&gt; true</code></span>
//<span><code>所以，4 是第一个错误的版本。</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1, bad = 1
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= bad &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>二分查找</li><li>交互</li></div></div><br><div><li>👍 815</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Random;

//278.第一个错误的版本
//开题时间：2022-10-29 11:11:48
public class FirstBadVersion {
    public static void main(String[] args) {
        Solution solution = new FirstBadVersion().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int l = 1;
            long r = (long) n + 1;
            while (l < r) {
                int mid = (int) (l + (r - l) / 2);
                if (isBadVersion(mid))
                    r = mid;
                else
                    l = mid + 1;
            }
            return l;
        }

        public int firstBadVersion2(int n) {
            int l = 1;
            int r = n;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid))
                    r = mid;
                else
                    l = mid + 1;
            }
            return l;
        }

        public int firstBadVersion3(int n) {
            int l = 1;
            int r = n;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid))
                    r = mid - 1;
                else
                    l = mid + 1;
            }
            return l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

class VersionControl {
    public boolean isBadVersion(int version) {
        return new Random().nextBoolean();
    }
}