package org.example.leetcode.problems._1_dataStructure.graph.leetbook.storage_of_graph;

import java.util.ArrayList;
import java.util.List;

//链式前向星存图法（数组模拟链表，适用于稀疏图)
public class ArrayLinkedListGraph extends Graph {
    //O(m)，m为边数
    int[] head;//顶点对应链表的头边
    int[] end;//边的终点
    int[] weight;//边的权值
    int[] next;//边的下一个边
    int idx = 0;//边的索引

    public ArrayLinkedListGraph(int size) {
        super(size);
        head = new int[size];
        int edgeSize = size * (size - 1);
        end = new int[edgeSize];
        weight = new int[edgeSize];
        next = new int[edgeSize];
    }

    @Override
    //O(1)，链表头插法
    public void add(int start, int end, int weight) {
        this.end[idx] = end;
        this.weight[idx] = weight;
        next[idx] = head[start];
        head[start] = idx++;
    }

    @Override
    //O(出度)
    public boolean isConnected(int start, int end) {
        for (int i = head[start]; i != 0; i = next[i])
            if (this.end[i] == end)
                return true;
        return false;
    }

    @Override
    //O(出度)
    public List<Edge> outEdges(int start) {
        ArrayList<Edge> list = new ArrayList<>();
        for (int i = head[start]; i != 0; i = next[i])
            list.add(new Edge(end[i], weight[i]));
        return list;
    }
}
