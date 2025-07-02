import java.util.*;

public class CourseSchedule_I {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int finished = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            finished++;

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0)
                    queue.offer(next);
            }
        }

        return finished == numCourses;
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

        System.out.println(canFinish(numCourses, prerequisites));
    }
}

