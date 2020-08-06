package com.mrxyc.solution.leecodesecondstep.graph;

/**
 * 判断二分图
 */
public class Solution785 {

    public static void main(String[] args) {
        Solution785 solution785 = new Solution785();
        System.out.println(solution785.isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
    }


    private static int RED = 1;
    private static int GREEN = 2;
    private static int UNCOLOR = 0;
    private static int[] color;
    private boolean valid = true;

    //深搜  双色标记
    public boolean isBipartite(int[][] graph) {
        color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == UNCOLOR) {
                deep(i, graph, RED);
            }
        }
        return valid;
    }

    public void deep(int index, int[][] graph, int colorType) {
        int[] curGraph = graph[index];
        color[index] = colorType;
        int nextColor = colorType == RED ? GREEN : RED;
        for (int i = 0; i < curGraph.length; i++) {
            if (color[curGraph[i]] == colorType) {
                valid = false;
                return;
            } else if (color[curGraph[i]] == UNCOLOR) {
                color[curGraph[i]] = nextColor;
                deep(curGraph[i], graph, nextColor);
            }
        }
    }
}
