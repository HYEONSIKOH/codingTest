import java.io.*;
import java.util.*;

public class Main {
    private static int[] arr;

    private static int N;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        System.out.print(solution(X, Y));
    }

    private static int solution(int X, int Y) {
        arr = new int[(N * 2) + 1];
        Arrays.fill(arr, -1);

        arr[X] = Y - X - 1;
        arr[Y] = Y - X - 1;

        int visited = (1 << (Y - X - 1));

        backtrack(1, visited);
        return ans;
    }

    private static void backtrack(int idx, int visited) {
        while(idx <= 2 * N && arr[idx] != -1)
            idx++;

        if (idx >= 2 * N - 1 && visited == (1 << (N + 1)) - 2) {
            ans++;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if ((visited & (1 << i)) == 0
                    && i + idx + 1 <= 2 * N
                    && arr[i + idx + 1] == -1) {
                arr[idx] = i;
                arr[i + idx + 1] = i;
                backtrack(idx + 1, visited | (1 << i));
                arr[idx] = -1;
                arr[i + idx + 1] = -1;
            }
        }
    }
}