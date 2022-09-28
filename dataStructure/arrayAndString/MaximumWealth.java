package org.example.leetcode.problems.dataStructure.arrayAndString;

//1672. 最富有客户的资产总量
public class MaximumWealth {
    public int maximumWealth(int[][] accounts) {
        int max=Integer.MIN_VALUE;
        for (int[] account : accounts) {
            int cur=0;
            for (int asset : account) {
                cur+=asset;
            }
            max=Math.max(max,cur);
        }
        return max;
    }
}
