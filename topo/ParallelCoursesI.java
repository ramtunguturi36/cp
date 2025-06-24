import java.util.*;

public class ParallelCoursesI {
    public static int minimumSemesters(int n, int[][] relations) {
        // Create adjacency list and in-degree count
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Build the graph and in-degree count
        for (int[] relation : relations) {
            int prev = relation[0];
            int next = relation[1];
            graph.get(prev).add(next);
            inDegree[next]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        // Add all courses with no prerequisites to the queue
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int semesters = 0;
        int coursesTaken = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            // Take all available courses this semester
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                coursesTaken++;
                
                // Reduce in-degree for neighbors
                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
            semesters++;
        }
        
        return coursesTaken == n ? semesters : -1;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of courses
        System.out.print("Enter the number of courses (n): ");
        int n = scanner.nextInt();
        
        // Read number of relations
        System.out.print("Enter the number of prerequisites: ");
        int m = scanner.nextInt();
        
        int[][] relations = new int[m][2];
        
        // Read each prerequisite pair
        System.out.println("Enter prerequisites in format [prevCourse nextCourse]:");
        for (int i = 0; i < m; i++) {
            System.out.print("Prerequisite " + (i+1) + ": ");
            relations[i][0] = scanner.nextInt();
            relations[i][1] = scanner.nextInt();
        }
        
        // Calculate and print result
        int result = minimumSemesters(n, relations);
        System.out.println("Minimum number of semesters needed: " + result);
        
        scanner.close();
    }
}