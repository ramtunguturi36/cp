import java.util.*;

public class treap {

    static class TreapNode {
        int data;
        int priority;
        TreapNode left, right;

        TreapNode(int data) {
            this.data = data;
            this.priority = new Random().nextInt(1000);
            this.left = this.right = null;
        }
    }

    static int k;

    public static TreapNode rotateLeft(TreapNode root) {
        TreapNode R = root.right;
        TreapNode X = R.left;
        R.left = root;
        root.right = X;
        return R;
    }

    public static TreapNode rotateRight(TreapNode root) {
        TreapNode L = root.left;
        TreapNode Y = L.right;
        L.right = root;
        root.left = Y;
        return L;
    }

    public static TreapNode insertNode(TreapNode root, int data) {
        if (root == null) return new TreapNode(data);

        if (data < root.data) {
            root.left = insertNode(root.left, data);
            if (root.left != null && root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else {
            root.right = insertNode(root.right, data);
            if (root.right != null && root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }

        return root;
    }

    public static void findKthLargest(TreapNode root) {
        if (root == null || k <= 0) return;

        findKthLargest(root.right);
        k--;
        if (k == 0) {
            System.out.println(root.data);
            return;
        }
        findKthLargest(root.left);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   
        int p = sc.nextInt();   
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        TreapNode root = null;
        for (int a : arr) {
            root = insertNode(root, a);
        }

        k = p; 
        findKthLargest(root);

        sc.close();
    }
}
