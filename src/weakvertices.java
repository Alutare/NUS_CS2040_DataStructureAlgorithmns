import java.util.*;

class WeakVertices {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            int n = sc.nextInt();  // number of vertices
            if (n == -1) break;  // end of input
            
            // Reading the adjacency matrix
            int[][] adjMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = sc.nextInt();
                }
            }
            
            // Find weak vertices
            List<Integer> weakVertices = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if (!isPartOfTriangle(i, adjMatrix, n)) {
                    weakVertices.add(i);
                }
            }
            
            // Print weak vertices for this graph
            for (int v : weakVertices) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        
        sc.close();
    }
    
    // Function to check if vertex `i` is part of any triangle
    private static boolean isPartOfTriangle(int i, int[][] adjMatrix, int n) {
        // Check all pairs of neighbors j and k of i
        for (int j = 0; j < n; j++) {
            if (adjMatrix[i][j] == 1) {  // j is a neighbor of i
                for (int k = j + 1; k < n; k++) {
                    if (adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {
                        // Found a triangle (i, j, k)
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
