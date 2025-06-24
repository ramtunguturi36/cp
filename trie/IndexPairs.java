import java.util.*;

class Solution {
    public int[][] indexPairs(String text, String[] words) {
        Trie trie = new Trie();
        for (String s : words) {
            Trie cur = trie;
            for (char c : s.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new Trie();
                }
                cur = cur.children[c - 'a'];
            }
            cur.end = true;
        }

        int len = text.length();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Trie cur = trie;
            char cc = text.charAt(i);
            int j = i;
            while (cur.children[cc - 'a'] != null) {
                cur = cur.children[cc - 'a'];
                if (cur.end) {
                    list.add(new int[]{i, j});
                }
                j++;
                if (j == len) {
                    break;
                } else {
                    cc = text.charAt(j);
                }
            }
        }

        int size = list.size();
        int[][] res = new int[size][2];
        int i = 0;
        for (int[] r : list) {
            res[i] = r;
            i++;
        }
        return res;
    }
}

class Trie {
    Trie[] children;
    boolean end;
    
    public Trie() {
        end = false;
        children = new Trie[26];
    }
}

class IndexPairs {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String org = sc.nextLine();
        String[] dict = sc.nextLine().split(" ");
        int[][] res = new Solution().indexPairs(org, dict);
        for (int[] ans : res) {
            System.out.println(ans[0] + " " + ans[1]);
        }
    }
}