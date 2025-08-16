import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, w;
        
        Edge(int to, int w) { 
            this.to = to; this.w = w; 
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            g[p].add(new Edge(q, r));
            g[q].add(new Edge(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            boolean[] vis = new boolean[N + 1];
            Deque<Integer> dq = new ArrayDeque<>();
            vis[v] = true;
            dq.add(v);
            int cnt = 0;

            while (!dq.isEmpty()) {
                int x = dq.poll();
                for (Edge e : g[x]) {
                    if (!vis[e.to] && e.w >= k) {
                        vis[e.to] = true;
                        dq.add(e.to);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb.toString());
    }
}