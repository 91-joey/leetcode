//<p>有 <code>n</code> 个房间，房间按从 <code>0</code> 到 <code>n - 1</code> 编号。最初，除 <code>0</code> 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。</p>
//
//<p>当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。</p>
//
//<p>给你一个数组 <code>rooms</code> 其中 <code>rooms[i]</code> 是你进入 <code>i</code> 号房间可以获得的钥匙集合。如果能进入 <strong>所有</strong> 房间返回 <code>true</code>，否则返回 <code>false</code>。</p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>rooms = [[1],[2],[3],[]]
//<strong>输出：</strong>true
//<strong>解释：</strong>
//我们从 0 号房间开始，拿到钥匙 1。
//之后我们去 1 号房间，拿到钥匙 2。
//然后我们去 2 号房间，拿到钥匙 3。
//最后我们去了 3 号房间。
//由于我们能够进入每个房间，我们返回 true。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>rooms = [[1,3],[3,0,1],[2],[0]]
//<strong>输出：</strong>false
//<strong>解释：</strong>我们不能进入 2 号房间。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == rooms.length</code></li> 
// <li><code>2 &lt;= n &lt;= 1000</code></li> 
// <li><code>0 &lt;= rooms[i].length &lt;= 1000</code></li> 
// <li><code>1 &lt;= sum(rooms[i].length) &lt;= 3000</code></li> 
// <li><code>0 &lt;= rooms[i][j] &lt; n</code></li> 
// <li>所有 <code>rooms[i]</code> 的值 <strong>互不相同</strong></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li></div></div><br><div><li>👍 268</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.QueueAndStack;

import java.util.*;

//841.钥匙和房间
//开题时间：2022-08-25 10:56:25
public class KeysAndRooms {
    public static void main(String[] args) {
        Solution solution = new KeysAndRooms().new Solution();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(1);
        list2.add(2);
        list3.add(3);
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        System.out.println(solution.canVisitAllRooms2(list));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.BFS+Queue+Set
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(0);

            while (!queue.isEmpty()) {
                rooms.get(queue.poll()).forEach(integer -> {
                    if (!visited.contains(integer)) {
                        queue.offer(integer);
                        visited.add(integer);
                    }
                });
            }

            int idx = 0;
            for (Integer integer : visited) {
                if (integer != idx++) {
                    return false;
                }
            }
            return idx == rooms.size();
        }

        //2.BFS+Queue+null
        public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(0);

            while (!queue.isEmpty()) {
                int poll = queue.poll();
                List<Integer> list = rooms.get(poll);
                rooms.set(poll, null);
                if (list != null) {
                    for (Integer integer : list) {
                        if (rooms.get(integer) != null) {
                            queue.offer(integer);
                        }
                    }
                }
            }

            for (List<Integer> room : rooms) {
                if (room != null) {
                    return false;
                }
            }
            return true;
        }

        //3.DFS+recursion+Set
        public boolean canVisitAllRooms3(List<List<Integer>> rooms) {
            Set<Integer> visited = new HashSet<>();
            visited.add(0);
            dfs(rooms, visited, 0);

            int idx = 0;
            for (Integer integer : visited) {
                if (integer != idx++) {
                    return false;
                }
            }
            return idx == rooms.size();
        }

        private void dfs(List<List<Integer>> rooms, Set<Integer> visited, int i) {
            for (Integer integer : rooms.get(i)) {
                if (!visited.contains(integer)) {
                    visited.add(integer);
                    dfs(rooms, visited, integer);
                }
            }
        }

        //4.DFS+recursion+null
        public boolean canVisitAllRooms4(List<List<Integer>> rooms) {
            dfs2(rooms, 0);

            for (List<Integer> room : rooms) {
                if (room != null) {
                    return false;
                }
            }
            return true;
        }

        private void dfs2(List<List<Integer>> rooms, int i) {
            List<Integer> list = rooms.get(i);
            rooms.set(i, null);
            for (Integer integer : list) {
                if (rooms.get(integer) != null) {
                    dfs2(rooms, integer);
                }
            }
        }

        //5.DFS+stack+null
        public boolean canVisitAllRooms5(List<List<Integer>> rooms) {
            Deque<Integer> stack = new ArrayDeque<>();
            List<Integer> list0 = rooms.get(0);
            rooms.set(0, null);
            for (Integer integer : list0) {
                if (integer != 0) {
                    stack.push(integer);
                }
            }
            while (!stack.isEmpty()) {
                int pop = stack.pop();
                List<Integer> list = rooms.get(pop);
                rooms.set(pop, null);
                if (list != null) {
                    for (Integer integer : list) {
                        if (rooms.get(integer) != null) {
                            stack.push(integer);
                        }
                    }
                }
            }

            for (List<Integer> room : rooms) {
                if (room != null) {
                    return false;
                }
            }
            return true;
        }


        //6.官解二：BFS+Queue+array+num
        public boolean canVisitAllRooms6(List<List<Integer>> rooms) {
            int size = rooms.size();
            boolean[] visited = new boolean[size];
            visited[0] = true;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(0);

            int visitedCnt = 0;
            while (!queue.isEmpty()) {
                visitedCnt++;
                rooms.get(queue.poll()).forEach(integer -> {
                    if (!visited[integer]) {
                        queue.offer(integer);
                        visited[integer] = true;
                    }
                });
            }

            return visitedCnt == size;
        }

        //☆☆☆☆☆7.官解一：DFS+recursion+null+num
        private int cnt=0;

        public boolean canVisitAllRooms7(List<List<Integer>> rooms) {
            dfs3(rooms, 0);
            return cnt == rooms.size();
        }

        private void dfs3(List<List<Integer>> rooms, int i) {
            cnt++;
            List<Integer> list = rooms.get(i);
            rooms.set(i, null);
            for (Integer integer : list) {
                if (rooms.get(integer) != null) {
                    dfs3(rooms, integer);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}