import java.util.*;

class FindingBridges {
    private int V;
    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;

    FindingBridges(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext()) {
            int v = i.next();
            if (!visited[v]) {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u])
                    System.out.println(u + " " + v);
            }
            else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }
    }

    void bridge() {
        boolean visited[] = new boolean[V];
        int disc[] = new int[V];
        int low[] = new int[V];
        int parent[] = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = NIL;
            visited[i] = false;
        }

        for (int i = 0; i < V; i++)
            if (!visited[i])
                bridgeUtil(i, visited, disc, low, parent);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter number of vertices: ");
        int v = sc.nextInt();
        
        System.out.println("Enter number of edges: ");
        int e = sc.nextInt();
        
        FindingBridges g = new FindingBridges(v);
        
        System.out.println("Enter edges (pair of vertices): ");
        for (int i = 0; i < e; i++) {
            int end1 = sc.nextInt();
            int end2 = sc.nextInt();
            g.addEdge(end1, end2);
        }
        
        System.out.println("Bridges in graph:");
        g.bridge();
        
        sc.close();
    }
}