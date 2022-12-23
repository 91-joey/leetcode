package org.example.leetcode.problems._1_dataStructure.graph.leetbook.unionfind;

//优化的「QuickUnion并查集」
public class QuickUnionOpt extends QuickUnion {
    int[] rank;

    //O(n)
    public QuickUnionOpt(int size) {
        super(size);
        rank = new int[size];
    }

    //O(log n)  路径压缩
    @Override
    public int find(int x) {
        if (x == root[x])
            return x;
        return root[x] = find(root[x]);
    }

    //O(log n)  按秩合并
    @Override
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY)
            if (rank[rootX] == rank[rootY]) {
                root[rootX] = rootY;
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY])
                root[rootX] = rootY;
            else
                root[rootY] = rootX;
    }
}
