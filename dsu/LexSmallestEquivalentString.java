import java.util.*;

class UnionFind {
    int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // Path compression
        }
        return parent[i];
    }

    public void unionfind(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (pi == pj) return;

        if (pi < pj) parent[pj] = pi; // Smaller becomes parent
        else parent[pi] = pj;
    }
}

public class LexSmallestEquivalentString {

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            uf.unionfind(a, b);
        }

        for (char c : baseStr.toCharArray()) {
            int smallest = uf.find(c - 'a');
            sb.append((char) (smallest + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        String T = sc.next();

        System.out.println(smallestEquivalentString(A, B, T));
    }
}
