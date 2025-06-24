import java.util.*;

public class ParallelCoursesII {
    public static int minimumSemesters(int n, int[][] relations, int k) {
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
        
        PriorityQueue<Integer> availableCourses = new PriorityQueue<>();
        // Add all courses with no prerequisites to the available courses
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                availableCourses.offer(i);
            }
        }
        
        int semesters = 0;
        int coursesTaken = 0;
        
        while (coursesTaken < n) {
            // Determine how many courses we can take this semester (up to k)
            int take = Math.min(availableCourses.size(), k);
            if (take == 0) {
                // Shouldn't happen as per problem statement (test cases guarantee solution exists)
                return -1;
            }
            
            List<Integer> takenThisSemester = new ArrayList<>();
            for (int i = 0; i < take; i++) {
                int course = availableCourses.poll();
                takenThisSemester.add(course);
                coursesTaken++;
            }
            
            // Update in-degrees and add newly available courses
            for (int course : takenThisSemester) {
                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        availableCourses.offer(neighbor);
                    }
                }
            }
            
            semesters++;
        }
        
        return semesters;
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
        
        // Read maximum courses per semester
        System.out.print("Enter maximum courses per semester (k): ");
        int k = scanner.nextInt();
        
        // Calculate and print result
        int result = minimumSemesters(n, relations, k);
        System.out.println("Minimum number of semesters needed: " + result);
        
        scanner.close();
    }
}