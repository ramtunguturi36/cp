import java.util.*;

class Node {
    public int data;
    public Node left;
    public Node right;
    
    public Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class Solution {
    Node lowestCommonAncestor(Node root, Node p1, Node p2) {
        if (root == null || root.data == p1.data || root.data == p2.data)
            return root;
            
        Node left = lowestCommonAncestor(root.left, p1, p2);
        Node right = lowestCommonAncestor(root.right, p1, p2);
        
        return left == null ? right : right == null ? left : root;
    }
}

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read tree nodes
        String[] arr = sc.nextLine().split(" ");
        // Read target nodes
        String[] persons = sc.nextLine().split(" ");
        
        List<Integer> values = new ArrayList<>();
        for (String s : arr) {
            values.add(Integer.parseInt(s));
        }
        
        // Build the tree
        Node root = new Node(values.get(0));
        Node p1 = new Node(Integer.parseInt(persons[0]));
        Node p2 = new Node(Integer.parseInt(persons[1]));
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        
        while (index < values.size() && !queue.isEmpty()) {
            Node current = queue.poll();
            
            if (values.get(index) != -1) {
                current.left = new Node(values.get(index));
                queue.add(current.left);
            }
            index++;
            
            if (index < values.size() && values.get(index) != -1) {
                current.right = new Node(values.get(index));
                queue.add(current.right);
            }
            index++;
        }
        
        // Find LCA
        Node result = new Solution().lowestCommonAncestor(root, p1, p2);
        System.out.println(result.data);
        
        sc.close();
    }
}