import java.util.*;
import java.io.*;

public class Main {

    private static int[] solution(int V, int E, int K, List<int[]>[] arr) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[K] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        pq.add(new int[]{K, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 이미 처리된 노드라면 continue
            if (cur[1] > dist[cur[0]]) continue;

            for (int i = 0; i < arr[cur[0]].size(); i++) {
                int[] next = arr[cur[0]].get(i);
                int nextCost = cur[1] + next[1];

                if (nextCost < dist[next[0]]) {
                    dist[next[0]] = nextCost;
                    pq.add(new int[]{next[0], nextCost});
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

        List<int[]>[] arr = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++)
            arr[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())]
                    .add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int[] res = solution(V, E, K, arr);

        for (int i = 1; i <= V; i++)
            bw.write(res[i] == Integer.MAX_VALUE ? "INF\n" : res[i] + "\n");

        // System.gc();
        bw.flush();
        bw.close();
    }
}