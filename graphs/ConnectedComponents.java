// import java.util.*;

// class ConnectedComponents {
//     int V;
//     ArrayList<ArrayList<Integer>> adjListArray;

//     ConnectedComponents(int V) {
//         this.V = V;
//         adjListArray = new ArrayList<>();
//         for (int i = 0; i < V; i++) {
//             adjListArray.add(i, new ArrayList<>());
//         }
//     }

//     void addEdge(int src, int dest) {
//         adjListArray.get(src).add(dest);
//         adjListArray.get(dest).add(src);
//     }

//     void DFSUtil(int v, boolean[] visited) {
//         visited[v] = true;
//         System.out.print(v + " ");
//         for (int x : adjListArray.get(v)) {
//             if (!visited[x]) {
//                 DFSUtil(x, visited);
//             }
//         }
//     }

//     void connectedComponents() {
//         boolean[] visited = new boolean[V];
//         for (int v = 0; v < V; ++v) {
//             if (!visited[v]) {
//                 DFSUtil(v, visited);
//                 System.out.println();
//             }
//         }
//     }

//     public static void main(String args[]) {
//         Scanner sc = new Scanner(System.in);
        
//         System.out.println("Enter number of vertices: ");
//         int v = sc.nextInt();
        
//         System.out.println("Enter number of edges: ");
//         int e = sc.nextInt();
        
//         ConnectedComponents g = new ConnectedComponents(v);
        
//         System.out.println("Enter edges (pair of vertices): ");
//         for (int i = 0; i < e; i++) {
//             int end1 = sc.nextInt();
//             int end2 = sc.nextInt();
//             g.addEdge(end1, end2);
//         }
        
//         System.out.println("Following are connected components:");
//         g.connectedComponents();
        
//         sc.close();
//     }
// }

import java.util.*;

public class ConnectedComponents {
    static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

    static void connectedComponents(int V, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, adjList, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.println("Enter number of edges: ");
        int E = sc.nextInt();

        // Initialize adjacency list in main
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        System.out.println("Enter edges (pair of vertices): ");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(adjList, u, v);
        }

        System.out.println("Following are connected components:");
        connectedComponents(V, adjList);

        sc.close();
    }
}
