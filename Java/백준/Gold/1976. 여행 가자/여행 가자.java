import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int[] parent;

    // 루트 노드 찾기
    public static int find(int x) {
        if (x == parent[x]) return x; // 같으면 루트노드

        parent[x] = find(parent[x]); // 재귀로 부모노드 접근
        return parent[x];
    }

    // 합치기
    public static void union(int x, int y) {
        // 각 부모노드를 비교
        x = find(x);
        y = find(y);

        if (x == y) return; // 이미 같은 집합
        else parent[y] = x; // 합한다
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int isConnected = Integer.parseInt(st.nextToken());
                if (isConnected == 1) {
                    union(i, j);
                    union(j, i);
                }
            }
        }

        int[] ansArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            ansArr[i] = Integer.parseInt(st.nextToken()) - 1;

        boolean isPossible = true;
        for (int i = 0; i < M - 1; i++) {
            if (find(ansArr[i]) != find(ansArr[i + 1])) {
                isPossible = false;
                break;
            }
        }

        System.out.println(isPossible ? "YES" : "NO");
    }
}