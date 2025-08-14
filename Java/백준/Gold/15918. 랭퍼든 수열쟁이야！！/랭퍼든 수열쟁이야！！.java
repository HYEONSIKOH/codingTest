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

        for (int i = 1; i <= N; i++) {
            if (2 + i <= 2 * N) {
                arr[1] = i;
                arr[2 + i] = i;
                backtrack(1, X, Y, (1 << i));
                arr[1] = -1;
                arr[2 + i] = -1;
            }
        }

        return ans;
    }

    private static void backtrack(int idx, int X, int Y, int visited) {
        while(idx <= 2 * N && arr[idx] != -1)
            idx++;

        // X번째와 Y번째가 같은 수가 아닌 경우
        if (arr[X] != -1 && arr[Y] != -1 && arr[X] != arr[Y])
            return;

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
                backtrack(idx + 1, X, Y, visited | (1 << i));
                arr[idx] = -1;
                arr[i + idx + 1] = -1;
            }
        }
    }
}