import java.util.*;

public class ParallelCoursesII {
    public static int minimumSemesters(int n, int[][] relations, int k) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];

        for (int i = 0; i <n+1; i++)
            graph.add(new ArrayList<>());

        for (int[] relation : relations){
            graph.get(relation[0]).add(relation[1]);
            inDegree[relation[1]]++;

        }

        PriorityQueue<Integer> availableCourses = new PriorityQueue<>();
        for (int i = 1; i <n+1; i++)
            if (inDegree[i] == 0)
                availableCourses.offer(i);

        int semesters = 0, coursesTaken = 0;

        while (coursesTaken < n) {
            int take = Math.min(availableCourses.size(), k);
            List<Integer> takenThisSemester = new ArrayList<>();

            for (int i = 0; i < take; i++) {
                int course = availableCourses.poll();
                takenThisSemester.add(course);
                coursesTaken++;
            }

            for (int course : takenThisSemester) {
                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0)
                        availableCourses.offer(neighbor);
                }
            }

            semesters++;
        }

        return semesters;
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

        int k = sc.nextInt();

        System.out.println(minimumSemesters(n, relations, k));
    }
}
