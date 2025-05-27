import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static boolean[] visited;
    private static List<Integer> ans = new ArrayList<>();
    private static int[] arr;

    private static void dfs(int node, int startNode) {
        // 다음 노드가 방문을 안했을 경우
        if (!visited[arr[node]]) {
            visited[arr[node]] = true;
            dfs(arr[node], startNode);
            visited[arr[node]] = false;
        }

        // 시작지점과 똑같을 경우 (사이클)
        if (arr[node] == startNode) ans.add(startNode);
    }

    public static void solution(int N, int[] arr) {
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        System.out.println(ans.size());
        for (int i : ans) System.out.println(i);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        solution(N, arr);
    }
}
