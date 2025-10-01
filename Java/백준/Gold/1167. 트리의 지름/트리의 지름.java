import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static final long[] maxDist = new long[2];

    private static List<Node>[] g;

    private static class Node {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    /**
     * 1. 한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 한 끝점
     * 2. 1에서 구한 지점에서 가장 먼 지점까지의 거리를 구함 => 지름의 다른 끝 점
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        if (N == 1) {
            System.out.print(0);
            return;
        }

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            while(true) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1) break;
                int c = Integer.parseInt(st.nextToken());

                g[a].add(new Node(b, c));
                g[b].add(new Node(a, c));
            }
        }

        System.out.print(solution());
    }

    private static long solution() {
        bfs(1);
        bfs((int)maxDist[0]);

        return maxDist[1];
    }

    private static void bfs(int start) {
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 0));

        byte[] visited = new byte[N + 1];
        visited[start] = 1;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if (maxDist[1] < cur.cost) {
                maxDist[0] = cur.to;
                maxDist[1] = cur.cost;
            }

            for (Node next : g[cur.to]) {
                if (visited[next.to] == 1) continue;

                visited[next.to] = 1;
                q.add(new Node(next.to, cur.cost + next.cost));
            }
        }
    }
}