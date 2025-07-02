import java.util.*;

public class ParallelCoursesI {
    public static int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];

        for (int i = 0; i <n+1; i++)
            graph.add(new ArrayList<>());

        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inDegree[relation[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <n+1; i++)
            if (inDegree[i] == 0)
                queue.offer(i);

        int semesters = 0, coursesTaken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                coursesTaken++;

                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0)
                        queue.offer(neighbor);
                }
            }
            semesters++;
        }

        return coursesTaken == n ? semesters : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] relations = new int[m][2];
        for (int i = 0; i < m; i++) {
            relations[i][0] = sc.nextInt();
            relations[i][1] = sc.nextInt();
        }

        System.out.println(minimumSemesters(n, relations));
    }
}
