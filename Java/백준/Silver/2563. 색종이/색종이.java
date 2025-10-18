import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;
    private static final boolean[][] visited = new boolean[101][101];

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.print(solution());
    }

    private static int solution() {
        for (int k = 0; k < N; k++) {
            int x = arr[k][0];
            int y = arr[k][1];
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    visited[i][j] = true;
                }
            }
        }

        int area = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                if (visited[i][j]) area++;
            }
        }

        return area;
    }
}