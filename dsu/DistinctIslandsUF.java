import java.util.*;

class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void unionfind(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;

        if (rank[px] > rank[py]) {
            parent[py] = px;
            rank[px] += rank[py];
        } else {
            parent[px] = py;
            rank[py] += rank[px];
        }
    }
}

public class DistinctIslandsUF {

    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols);
        List<int[]> landPositions = new ArrayList<>();

        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int index = i * cols + j;
                    landPositions.add(new int[]{i, j});

                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && nj >= 0 && ni < rows && nj < cols && grid[ni][nj] == 1) {
                            int nIndex = ni * cols + nj;
                            if (uf.find(index) != uf.find(nIndex)) {
                                uf.unionfind(index, nIndex);
                            }
                        }
                    }
                }
            }
        }

        Map<Integer, List<int[]>> islandMap = new HashMap<>();
        for (int[] pos : landPositions) {
            int root = uf.find(pos[0] * cols + pos[1]);
            islandMap.computeIfAbsent(root, k -> new ArrayList<>()).add(pos);
        }

        Set<String> uniqueIslands = new HashSet<>();
        for (List<int[]> island : islandMap.values()) {
            int[] base = island.get(0);
            List<String> shape = new ArrayList<>();
            for (int[] cell : island) {
                int dx = cell[0] - base[0];
                int dy = cell[1] - base[1];
                shape.add(dx + "," + dy);
            }
            Collections.sort(shape);
            uniqueIslands.add(String.join(";", shape));
        }

        return uniqueIslands.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println(numDistinctIslands(grid));
    }
}
