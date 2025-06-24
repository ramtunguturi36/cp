import java.util.*;

public class DistinctIslandsUF {
    private int[] size;
    private int[] parent;
    private int rows, cols;

    public int find(int i) {
        while (parent[i] >= 0) {
            i = parent[i];
        }
        return i;
    }

    public void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) return;
        
        if (size[rootI] < size[rootJ]) {
            size[rootJ] += size[rootI];
            parent[rootI] = rootJ;
        } else {
            size[rootI] += size[rootJ];
            parent[rootJ] = rootI;
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    public int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        rows = grid.length;
        cols = grid[0].length;
        size = new int[rows * cols];
        parent = new int[rows * cols];
        Arrays.fill(parent, -1);
        Arrays.fill(size, 1);

        List<int[]> landPositions = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int index = i * cols + j;
                    landPositions.add(new int[]{i, j});
                    
                    // Check and union with 4-directional neighbors
                    if (isValid(i - 1, j) && grid[i - 1][j] == 1) union(index, index - cols);
                    if (isValid(i, j - 1) && grid[i][j - 1] == 1) union(index, index - 1);
                    if (isValid(i + 1, j) && grid[i + 1][j] == 1) union(index, index + cols);
                    if (isValid(i, j + 1) && grid[i][j + 1] == 1) union(index, index + 1);
                }
            }
        }

        Map<Integer, Queue<int[]>> islandMap = new HashMap<>();
        for (int[] pos : landPositions) {
            int root = find(pos[0] * cols + pos[1]);
            islandMap.computeIfAbsent(root, k -> new LinkedList<>()).add(pos);
        }

        Set<String> uniqueIslands = new HashSet<>();
        for (Queue<int[]> queue : islandMap.values()) {
            int[] base = queue.peek();
            StringBuilder shape = new StringBuilder();
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                shape.append(cell[0] - base[0]).append(",");
                shape.append(cell[1] - base[1]).append(";");
            }
            uniqueIslands.add(shape.toString());
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
        System.out.println(new DistinctIslandsUF().numIslands(grid));
    }
}