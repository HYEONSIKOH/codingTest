import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int changeIndex(int N, int x, int y) {
        return x * N + y;
    }

    private static int findEdge (int N, int L, int R, List<Integer>[] g, int[][] arr) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int idx = changeIndex(N, i, j);

                for (int ni = 0; ni < 4; ni++) {
                    int nx = i + dx[ni];
                    int ny = j + dy[ni];

                    // 행렬 밖으로 나가면 X
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    int minus = Math.abs(arr[i][j] - arr[nx][ny]);
                    if (L <= minus && minus <= R) {
                        int nidx = changeIndex(N, nx, ny);
                        g[idx].add(nidx);
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    private static List<Integer> bfs(boolean[] visited, List<Integer>[] g, int[][] arr, int start, int N) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> union = new ArrayList<>();
        q.add(start);
        visited[start] = true;
        union.add(start);

        while (!q.isEmpty()) {
            int next = q.poll();
            for (int i = 0; i < g[next].size(); i++) {
                int nextIdx = g[next].get(i);
                if (!visited[nextIdx]) {
                    visited[nextIdx] = true;
                    q.add(nextIdx);
                    union.add(nextIdx);
                }
            }
        }
        return union;
    }

    private static void solution(int N, int L, int R, int[][] arr) {
        int answer = 0;

        while (true) {
            boolean[] visited = new boolean[N * N];
            List<Integer>[] g = new ArrayList[N * N];

            for (int i = 0; i < N * N; i++) g[i] = new ArrayList<>();

            // 연합 찾기 (간선 & 노드 찾기)
            int cnt = findEdge(N, L, R, g, arr);

            // 연합이 없다면, 종료
            if (cnt == 0) break;
            else answer++;

            // 연합 찾기
            for (int i = 0; i < N * N; i++) {
                if (!visited[i]) {
                    List<Integer> union = bfs(visited, g, arr, i, N);
                    if (union.size() > 1) {
                        int sum = 0;
                        for (int idx : union) {
                            sum += arr[idx / N][idx % N];
                        }
                        int avg = sum / union.size();
                        for (int idx : union) {
                            arr[idx / N][idx % N] = avg;
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < N * N; i++) {
//            List<Integer> l = g[i];
//            System.out.print(i +"| ");
//            for (Integer I : l)
//                System.out.print(I + " ");
//            System.out.println();
//        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        solution(N, L, R, arr);
    }
}
