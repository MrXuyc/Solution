package com.mrxyc.solution.leecodesecondstep.deep;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

/**
 * 岛屿数量
 */
public class Solution200 {

    public static void main(String[] args) {
        Solution200 solution200 = new Solution200();
        //char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        char[][] grid = new char[][]{{'1', '1', '1'}, {'0', '1', '0'}, {'1', '1', '1'}};
        System.out.println(solution200.numIslands(grid));
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }


    public int numIslands1(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int nr = grid.length;
        int nc = grid[0].length;
        int res = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        return res;
    }

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int gridN = grid.length;
        int gridM = grid[0].length;
        boolean[][] visited = new boolean[gridN][gridM];
        List<List<Pair<Integer, Integer>>> lands = new ArrayList<>();
        for (int i = 0; i < gridN; i++) {
            for (int j = 0; j < gridM; j++) {
                List<Pair<Integer, Integer>> land = new ArrayList<>();
                //如果已经访问 就跳出
                if (visited[i][j]) {
                    continue;
                }
                //判断相邻的节点是陆地还是海水
                //如果是陆地继续递归
                if ('1' == grid[i][j]) {
                    deep(grid, visited, i, j, land);
                }
                //把最后的结果land放到lands
                if (!land.isEmpty()) {
                    lands.add(land);
                }
            }
        }
        return lands.size();
    }

    private void deep(char[][] grid, boolean[][] visited, int i, int j, List<Pair<Integer, Integer>> land) {
        if (visited[i][j]) {
            return;
        }
        char cur = grid[i][j];
        if ('1' == cur) {
            //添加visited
            visited[i][j] = true;
            //把自己加进去
            land.add(new Pair<>(i, j));
            //递归 上下左右
            if (i - 1 >= 0 && !visited[i - 1][j]) {
                deep(grid, visited, i - 1, j, land);
            }
            if (i + 1 < grid.length && !visited[i + 1][j]) {
                deep(grid, visited, i + 1, j, land);
            }
            if (j - 1 >= 0 && !visited[i][j - 1]) {
                deep(grid, visited, i, j - 1, land);
            }
            if (j + 1 < grid[0].length && !visited[i][j + 1]) {
                deep(grid, visited, i, j + 1, land);
            }
        }
    }

}
