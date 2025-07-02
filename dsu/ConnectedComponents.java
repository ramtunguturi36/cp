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
            parent[x] = find(parent[x]); // Path Compression
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

public class ConnectedComponents {

    public static int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int components = n;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (uf.find(u) != uf.find(v)) {
                uf.unionfind(u, v);
                components--;
            }
        }
        return components;
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

        System.out.println(countComponents(n, edges));
    }
}
