import java.util.*;
import java.io.*;

public class Main {

    private static int[] solution(int V, int K, List<int[]>[] arr) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[K] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

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

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        List<int[]>[] arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            arr[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[s].add(new int[]{e, w});
            arr[e].add(new int[]{s, w}); // undirected
        }

        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        int[] dFrom1  = solution(N, 1,  arr); // 1번에서 출발
        int[] dFromP1 = solution(N, p1, arr);    // p1에서 출발
        int[] dFromP2 = solution(N, p2, arr);    // p2에서 출발

        final int INF = Integer.MAX_VALUE;
        long route1 = (dFrom1[p1] == INF || dFromP1[p2] == INF || dFromP2[N] == INF) 
                ? Long.MAX_VALUE : (long)dFrom1[p1] + dFromP1[p2] + dFromP2[N];
        long route2 = (dFrom1[p2] == INF || dFromP2[p1] == INF || dFromP1[N] == INF) 
                ? Long.MAX_VALUE : (long)dFrom1[p2] + dFromP2[p1] + dFromP1[N];

        long ans = Math.min(route1, route2);
        bw.write(ans == Long.MAX_VALUE ? "-1\n" : ans + "\n");
        bw.flush();
        bw.close();
    }
}