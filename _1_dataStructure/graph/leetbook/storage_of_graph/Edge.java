package org.example.leetcode.problems._1_dataStructure.graph.leetbook.storage_of_graph;

public class Edge {
    int end;
    int weight;

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "end=" + end +
                ", weight=" + weight +
                '}';
    }
}
