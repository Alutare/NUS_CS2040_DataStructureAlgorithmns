import java.io.*;
import java.util.*;

class UFDSsol {
    static class DSU {
        int[] par; // Parent array
        int[] nxt; // Next array
        int[] cnt; // Count of elements in each set
        long[] sm; // Sum of elements in each set

        // Initialize the sets
        DSU(int n) {
            this.par = new int[n + 1];
            this.nxt = new int[n + 1];
            this.cnt = new int[n + 1];
            this.sm = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                par[i] = i;
                nxt[i] = i;
                cnt[i] = 1;
                sm[i] = i;
            }
        }

        // Find the representative of the set
        int find(int p) {
            int temp = nxt[p];
            while (temp != par[temp]) {
                temp = par[temp];
            }
            nxt[p] = temp;
            return temp;
        }

        // Union two sets
        void union(int p, int q) {
            int x = find(p);
            int y = find(q);
            if (x != y) { // If not in the same set
                par[x] = y;
                nxt[p] = y;
                cnt[y] += cnt[x];
                sm[y] += sm[x];
            }
        }

        // Move element p to the set containing q
        void move(int p, int q) {
            int x = find(p);
            int y = find(q);
            if (x != y) { // If not in the same set
                nxt[p] = y;
                cnt[x]--;
                cnt[y]++;
                sm[x] -= p;
                sm[y] += p;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            DSU dsu = new DSU(n);

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());

                if (cmd == 1) {
                    int p = Integer.parseInt(st.nextToken());
                    int q = Integer.parseInt(st.nextToken());
                    dsu.union(p, q);
                } else if (cmd == 2) {
                    int p = Integer.parseInt(st.nextToken());
                    int q = Integer.parseInt(st.nextToken());
                    dsu.move(p, q);
                } else if (cmd == 3) {
                    int p = Integer.parseInt(st.nextToken());
                    int rep = dsu.find(p);
                    System.out.println(dsu.cnt[rep] + " " + dsu.sm[rep]);
                }
            }
        }
    }
}
