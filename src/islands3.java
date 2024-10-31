import java.util.Scanner;

class MinimumIslands {
    static int rows, cols;
    static char[][] grid;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        scanner.nextLine(); // Move to the next line

        grid = new char[rows][cols];
        visited = new boolean[rows][cols];

        // Read the grid
        for (int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            grid[i] = line.toCharArray();
        }

        System.out.println(minimumIslands());
    }

    static int minimumIslands() {
        int islands = 0;

        // First pass: Count islands with current land layout
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && (grid[i][j] == 'L' || grid[i][j] == 'C')) {
                    // Start DFS from this cell
                    if (isIsland(i, j)) {
                        islands++;
                    }
                }
            }
        }

        return islands;
    }

    static boolean isIsland(int x, int y) {
        // If dfs returns true, it means we found an island
        return dfs(x, y);
    }

    static boolean dfs(int x, int y) {
        // Boundary and visited check
        if (x < 0 || x >= rows || y < 0 || y >= cols || visited[x][y] || grid[x][y] == 'W') {
            return false;
        }

        // Mark this cell as visited
        visited[x][y] = true;

        // Check if this cell is land
        boolean foundLand = (grid[x][y] == 'L');

        // Visit all 4 neighbors and check if any neighbor has land
        foundLand |= dfs(x + 1, y);
        foundLand |= dfs(x - 1, y);
        foundLand |= dfs(x, y + 1);
        foundLand |= dfs(x, y - 1);

        return foundLand;
    }
}
