
import java.util.*;

public class CourseSchedule_II {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++; 
        }

        

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            order[idx++] = node;

            for (int nbr : graph.get(node)) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }

        if (idx < numCourses) {
            return new int[]{};  
        }
        return order;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCourses = sc.nextInt();
        int m = sc.nextInt();
        int[][] prerequisites = new int[m][2];

        for (int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }

        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }
}
