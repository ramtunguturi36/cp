import java.util.*;


class Trie {
    Trie[] children = new Trie[26];
    boolean isWord;
}

class Solution {
    Trie root = new Trie();
    String res = "";

    public String longestWord(String[] words) {
        for (String word : words) addWord(word);
        for (String word : words) searchPrefix(word);
        return res;
    }

    private void searchPrefix(String word) {
        Trie cur = root;
        for (char c : word.toCharArray()) {
            cur = cur.children[c - 'a'];
            if (!cur.isWord) return;
        }
        if (res.length() < word.length() || 
            (res.length() == word.length() && res.compareTo(word) > 0)) {
            res = word;
        }
    }

    private void addWord(String word) {
        Trie cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new Trie();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }
}



class LongestWord {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String[] dict = sc.nextLine().split(" ");
        System.out.println(new Solution().longestWord(dict));
    }
}