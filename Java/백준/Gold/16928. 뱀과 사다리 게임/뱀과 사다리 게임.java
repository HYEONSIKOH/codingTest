import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] graph = new int[101];

    public static void dfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        Arrays.fill(visited, false);
        q.add(new int[]{1, 0});

        while(!q.isEmpty()) {
            int cur = q.peek()[0];
            int dist = q.peek()[1];
            visited[cur] = true;
            q.poll();

            for (int i = 1; i <= 6; i++) {
                int next = cur + i;
                if (next > 100) continue;

                int nextCur = graph[next] == -1 ? next : graph[next];
                if (visited[nextCur]) continue;
                else if (nextCur == 100) {
                    System.out.println(dist + 1);
                    return;
                }
                else q.add(new int[]{nextCur, dist + 1});
            }
        }

    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // -1로 채우기
        Arrays.fill(graph, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a] = b;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a] = b;
        }

        dfs();
    }
}
