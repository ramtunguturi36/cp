import java.util.*;

class ConnectedComponents {
    private int[] parent;
    private int[] size;

    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(size, 1);

        int components = n;
        for (int[] edge : edges) {
            int root1 = find(edge[0]);
            int root2 = find(edge[1]);
            
            if (root1 != root2) {
                // Union by size (smaller tree merges into larger tree)
                if (size[root1] < size[root2]) {
                    parent[root1] = root2;
                    size[root2] += size[root1];
                } else {
                    parent[root2] = root1;
                    size[root1] += size[root2];
                }
                components--;
            }
        }
        return components;
    }

    private int find(int node) {
        while (parent[node] >= 0) {
            node = parent[node];
        }
        return node;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of nodes
        int e = sc.nextInt(); // Number of edges
        int[][] edges = new int[e][2];
        
        for (int i = 0; i < e; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
        }
        
        ConnectedComponents solver = new ConnectedComponents();
        System.out.println(solver.countComponents(n, edges));
    }
}