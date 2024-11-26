import java.io.*;
import java.util.*;

class UFDS {
    private int[] parent;

    public UFDS(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int n) {
        if (n == parent[n]) return n;
        return parent[n] = find(parent[n]); // Path compression
    }

    public boolean isSameSet(int n, int m) {
        return find(n) == find(m);
    }

    public void join(int n, int m) {
        parent[find(n)] = find(m); // Union operation
    }
}

class LostMap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        UFDS ufds = new UFDS(n);

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(line[j]);
                if (i < j) { // Only add each edge once
                    edges.add(new Edge(i, j, weight));
                }
            }
        }

        // Sort edges by weight
        edges.sort(Comparator.comparingInt(edge -> edge.weight));

        for (Edge edge : edges) {
            if (!ufds.isSameSet(edge.u, edge.v)) {
                pw.println((edge.u + 1) + " " + (edge.v + 1));
                ufds.join(edge.u, edge.v);
            }
        }

        pw.flush();
    }
}

class Edge {
    int u, v, weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }
}
