import java.io.*;
import java.util.*;

public class Main {
    private static char[][] arr;

    private static Pos[] chocoMilks;
    private static boolean[] visited;

    private static int ans = 0;
    private static int chocoMilkCnt = 0;
    private static Pos home;

    private static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        arr = new char[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                arr[i][j] = st.nextToken().charAt(0);
        }

        System.out.println(solution(N, M, H));
    }

    private static int solution(int N, int M, int H) {
        home = new Pos(-1, -1);
        chocoMilks = new Pos[10];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == '1') home = new Pos(i, j);
                else if (arr[i][j] == '2')
                    chocoMilks[chocoMilkCnt++] = new Pos(i, j);
            }
        }

        if (chocoMilkCnt == 0) return 0;

        visited = new boolean[chocoMilkCnt];

        for (int i = 0; i < chocoMilkCnt; i++) {
            int dist = calDistance(home, chocoMilks[i]);
            if (dist <= M) {
                visited[i] = true;
                backtrack(i, 1, M - dist + H, H);
                visited[i] = false;
            }
        }

        return ans;
    }

    private static void backtrack(int idx, int cnt, int hp, int H) {
        if (calDistance(home, chocoMilks[idx]) <= hp)
            ans = Math.max(ans, cnt);

        for (int i = 0; i < chocoMilkCnt; i++) {
            int dist = calDistance(chocoMilks[idx], chocoMilks[i]);
            if (!visited[i] && dist <= hp) {
                visited[i] = true;
                backtrack(i, cnt + 1, hp - dist + H, H);
                visited[i] = false;
            }
        }
    }

    // 맨헤튼 거리
    private static int calDistance(Pos p1, Pos p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}