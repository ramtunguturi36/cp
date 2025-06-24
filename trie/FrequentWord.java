import java.util.*;

class Node {
    public char c;
    public boolean isWord;
    public int count;
    public Node[] children;
    public String str;

    public Node(char c) {
        this.c = c;
        this.isWord = false;
        this.count = 0;
        children = new Node[26];
        str = "";
    }
}

class Trie {
    public Node root;

    public Trie() {
        this.root = new Node('\0');
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new Node(c);
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true;
        curr.count += 1;
        curr.str = word;
    }

    public void traverse(Node root, PriorityQueue<Node> pq) {
        if (root.isWord) {
            pq.offer(root);
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                traverse(root.children[i], pq);
            }
        }
    }
}

public class FrequentWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        int p = sc.nextInt();
        String[] words = line1.split(",");

        Trie t = new Trie();
        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.count != b.count) {
                    return b.count - a.count; // Higher count first
                }
                return a.str.compareTo(b.str); // Lex order if counts are equal
            }
        );

        for (int i = 0; i < words.length; i++) {
            t.insert(words[i]);
        }

        t.traverse(t.root, pq);
        List<String> res = new ArrayList<>();
        int k = 0;
        while (k++ < p && !pq.isEmpty()) {
            res.add(pq.poll().str);
        }
        System.out.println(res);
    }
}