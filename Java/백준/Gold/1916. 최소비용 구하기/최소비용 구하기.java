import java.util.*;
import java.io.*;

public class Main {
    private static class Node {
        int to, cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Node>[] g = new ArrayList[N];
        for (int i = 0; i < N; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            g[a].add(new Node(b, c));
            //g[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(solution(g, N, s, e));
    }

    private static int solution(List<Node>[] g, int N, int s, int e) {
        int[] visited = new int[N];
        Arrays.fill(visited, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to] <= cur.cost) continue;
            else visited[cur.to] = cur.cost;

            for (Node next : g[cur.to]) {
                if (visited[next.to] > cur.cost + next.cost)
                    pq.add(new Node(next.to, cur.cost + next.cost));
            }
        }

        return visited[e];
    }
}