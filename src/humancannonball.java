import java.util.ArrayList;
import java.util.Scanner;

class HumanCannonball {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<double[]> coordinates = new ArrayList<>();

        // Read start coordinates
        coordinates.add(new double[]{sc.nextDouble(), sc.nextDouble()});

        // Read end coordinates
        coordinates.add(new double[]{sc.nextDouble(), sc.nextDouble()});

        // Read cannon positions
        int cannonCount = sc.nextInt();
        for (int i = 0; i < cannonCount; i++) {
            coordinates.add(new double[]{sc.nextDouble(), sc.nextDouble()});
        }
        sc.close();

        // Initialize adjacency matrix for graph representation
        double[][] adj_matrix = new double[cannonCount + 2][cannonCount + 2];
        adj_matrix[0][1] = computeDistance(coordinates.get(0), coordinates.get(1)) / 5.0;
        adj_matrix[1][0] = adj_matrix[0][1];

        // Calculate distances from start to cannons and from end to cannons
        for (int i = 2; i < cannonCount + 2; i++) {
            double distStart = computeDistance(coordinates.get(0), coordinates.get(i));
            adj_matrix[0][i] = distStart / 5.0;
            adj_matrix[i][0] = (Math.abs(distStart - 50) / 5.0) + 2.0;

            double distEnd = computeDistance(coordinates.get(1), coordinates.get(i));
            adj_matrix[1][i] = distEnd / 5.0;
            adj_matrix[i][1] = (Math.abs(distEnd - 50) / 5.0) + 2.0;
        }

        // Calculate distances between all cannons
        for (int i = 2; i < cannonCount + 2; i++) {
            for (int j = i + 1; j < cannonCount + 2; j++) {
                double distCannon = computeDistance(coordinates.get(i), coordinates.get(j));
                adj_matrix[i][j] = (Math.abs(distCannon - 50) / 5.0) + 2.0;
                adj_matrix[j][i] = adj_matrix[i][j];
            }
        }

        // Apply Floyd-Warshall algorithm to find shortest path
        double[][] shortestPaths = floydWarshall(adj_matrix);
        System.out.println(shortestPaths[0][1]);
    }

    static double computeDistance(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    static double[][] floydWarshall(double[][] graph) {
        int V = graph.length;
        double[][] distances = new double[V][V];

        // Initialize distances array with graph values
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                distances[i][j] = graph[i][j];
            }
        }

        // Run Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        return distances;
    }
}
