import java.util.*;

public class fenwik{

    public static void init(int[] BIT, int n, int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }

    public static void update(int[] nums, int[] BIT, int n, int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        init(BIT, n, i, diff);
    }

    public static int getSum(int[] BIT, int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public static int sumRange(int[] BIT, int i, int j) {
        return getSum(BIT, j) - getSum(BIT, i - 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int q = scan.nextInt();

        int[] nums = new int[n];
        int[] BIT = new int[n + 1];

        for (int i = 0; i < n; i++) {
            nums[i] = scan.nextInt();
            init(BIT, n, i, nums[i]);
        }

        while (q-- > 0) {
            int opt = scan.nextInt();
            if (opt == 1) {
                int s1 = scan.nextInt();
                int s2 = scan.nextInt();
                System.out.println(sumRange(BIT, s1, s2));
            } else {
                int ind = scan.nextInt();
                int val = scan.nextInt();
                update(nums, BIT, n, ind, val);
            }
        }

        scan.close();
    }
}
