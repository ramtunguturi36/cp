import java.util.*;

class CourseSchedule_II {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] indegree = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            for(int node : graph.get(i)) {
                indegree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] ts = new int[numCourses];
        int i = 0;
        
        while(!q.isEmpty()) {
            int node = q.remove();
            ts[i++] = node;

            for(int nbr : graph.get(node)) {
                indegree[nbr]--;
                if(indegree[nbr] == 0) {
                    q.add(nbr);
                }
            }
        }

        if(i == 0 || i < numCourses) return new int[]{};
        return ts;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseSchedule_II cs = new CourseSchedule_II();

        int numCourses = sc.nextInt();
        int m = sc.nextInt();
        int[][] prerequisites = new int[m][2];
        for(int i = 0; i < m; i++) {
            prerequisites[i][0] = sc.nextInt();
            prerequisites[i][1] = sc.nextInt();
        }

        System.out.println(Arrays.toString(cs.findOrder(numCourses, prerequisites)));
    }
}