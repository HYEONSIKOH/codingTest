import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int n, m;
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

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            int a, b, c;

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 0) union(b, c);
            else {
                if (find(b) == find(c)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
