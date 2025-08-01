import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Edge>[] graph;
    static boolean[] visited;

    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static void init(int n) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    static int bfs(int start, int end) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited = new boolean[graph.length];
        visited[start] = true;
        q.add(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0];
            int dist = cur[1];

            if (node == end) return dist;

            for (Edge next : graph[node]) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    q.add(new int[]{next.to, dist + next.cost});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init(N);

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost)); // 트리는 양방향
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            bw.write(bfs(s, e) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}