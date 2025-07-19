import java.util.*;
import java.io.*;

public class Main {

    private static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int[] solution(int V, int E, int K, List<Edge>[] arr) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[K] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        pq.add(new Edge(K, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 이미 처리된 노드라면 continue
            if (cur.cost > dist[cur.to]) continue;

            for (int i = 0; i < arr[cur.to].size(); i++) {
                Edge next = arr[cur.to].get(i);
                int nextCost = cur.cost + next.cost;

                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.add(new Edge(next.to, nextCost));
                }
            }
        }

        return dist;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        int K = Integer.parseInt(br.readLine()); // 시작 정점

        List<Edge>[] arr = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++)
            arr[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())]
                    .add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] res = solution(V, E, K, arr);

        for (int i = 1; i <= V; i++)
            bw.write(res[i] == Integer.MAX_VALUE ? "INF\n" : res[i] + "\n");

        // System.gc();
        bw.flush();
        bw.close();
    }
}