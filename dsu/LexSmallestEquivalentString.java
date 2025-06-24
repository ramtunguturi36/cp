import java.util.*;

class LexSmallestEquivalentString {
    private static class UnionFind {
        private final int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]); // Path compression
            }
            return parent[i];
        }

        private void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI < rootJ) {
                parent[rootJ] = rootI; // Smaller root becomes parent
            } else {
                parent[rootI] = rootJ;
            }
        }
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        StringBuilder sb = new StringBuilder();

        // Unify equivalent characters
        for (int i = 0; i < s1.length(); i++) {
            int charS1 = s1.charAt(i) - 'a';
            int charS2 = s2.charAt(i) - 'a';
            uf.union(charS1, charS2);
        }

        // Build the result by finding the smallest equivalent for each character
        for (int i = 0; i < baseStr.length(); i++) {
            int smallestChar = uf.find(baseStr.charAt(i) - 'a');
            sb.append((char) (smallestChar + 'a'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        String T = sc.next();

        LexSmallestEquivalentString solver = new LexSmallestEquivalentString();
        System.out.println(solver.smallestEquivalentString(A, B, T));
    }
}