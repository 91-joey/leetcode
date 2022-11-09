//<p>这是一个<strong>交互问题</strong>。</p>
//
//<p>您有一个<strong>升序</strong>整数数组，其<strong>长度未知</strong>。您没有访问数组的权限，但是可以使用&nbsp;<code>ArrayReader&nbsp;</code>接口访问它。你可以调用&nbsp;<code>ArrayReader.get(i)</code>:</p>
//
//<ul> 
// <li> <p>返回数组第<code>i<sup>th</sup></code>个索引(<strong>0-indexed</strong>)处的值(即<code>secret[i]</code>)，或者</p> </li> 
// <li> <p>如果&nbsp;<code>i</code>&nbsp; 超出了数组的边界，则返回&nbsp;<code>2<sup>31</sup>&nbsp;- 1</code></p> </li> 
//</ul>
//
//<p>你也会得到一个整数 <code>target</code>。</p>
//
//<p>如果存在<code>secret[k] == target</code>，请返回索引&nbsp;<code>k</code>&nbsp;的值；否则返回&nbsp;<code>-1</code></p>
//
//<p>你必须写一个时间复杂度为&nbsp;<code>O(log n)</code>&nbsp;的算法。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>secret</code></span> = [-1,0,3,5,9,12], <span><code>target</code></span> = 9
//<strong>输出:</strong> 4
//<strong>解释:</strong> 9 存在在 nums 中，下标为 4
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入:</strong> <span><code>secret</code></span> = [-1,0,3,5,9,12], <span><code>target</code></span> = 2
//<strong>输出:</strong> -1
//<strong>解释:</strong> 2 不在数组中所以返回 -1</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= secret.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>4</sup>&nbsp;&lt;= secret[i], target &lt;= 10<sup>4</sup></code></li> 
// <li><code>secret</code>&nbsp;严格递增</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>交互</li></div></div><br><div><li>👍 59</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

//702.搜索长度未知的有序数组
//开题时间：2022-11-02 12:16:56
public class SearchInASortedArrayOfUnknownSize {
    public static void main(String[] args) {
        Solution solution = new SearchInASortedArrayOfUnknownSize().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * // This is ArrayReader's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface ArrayReader {
     * public int get(int index) {}
     * }
     */

    class Solution {
        //单二分    log(max(len))
        public int search(ArrayReader reader, int target) {
            for (int l = 0, r = 9999; l <= r; ) {
                int mid = l + r >> 1;
                int i = reader.get(mid);
                if (i == target)
                    return mid;
                else if (i < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return -1;
        }

        //☆☆☆☆☆ 双二分（先确定查找区间，再二分）  log(target)
        public int search2(ArrayReader reader, int target) {
            int l = 0, r = 1;
            while (target > reader.get(r)) {
                l = r;
                r <<= 1;
            }

            while (l <= r) {
                int mid = l + r >> 1;
                int i = reader.get(mid);
                if (i == target)
                    return mid;
                else if (i < target)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    interface ArrayReader {
        int get(int index);
    }
}