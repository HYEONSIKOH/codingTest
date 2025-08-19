import java.io.*;
import java.util.*;

public class Main {
    private static int[] arr;
    private static int[] ans;

    private static int max = Integer.MIN_VALUE;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        solution();
    }

    private static void solution() {
        if (max + 2 > 2 * N) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(arr);
        ans = new int[N * 2];
        Arrays.fill(ans, -1);

        for (int i = 0; i < N; i++) {
            int num = arr[i];
            ans[0] = num;
            ans[1 + num] = num;
            backtrack(1, (1 << i));
            ans[0] = -1;
            ans[1 + num] = -1;
        }

        System.out.println(-1);
    }

    // idx: ans에서 내가 둬야할 index, n: arr에서 내가 선택한 index
    private static void backtrack(int idx, int visited) {
        while (idx < 2 * N && ans[idx] != -1)
            idx++;

        if (idx >= 2 * N && visited == (1 << N) - 1) {
            for (int i = 0; i < 2 * N; i++)
                System.out.print(ans[i] + " ");

            System.exit(0);
        }

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            if ((visited & (1 << i)) == 0
                    && num + idx + 1 < 2 * N && ans[num + idx + 1] == -1) {
                ans[idx] = num;
                ans[idx + num + 1] = num;
                backtrack(idx + 1, visited | (1 << i));
                ans[idx] = -1;
                ans[idx + num + 1] = -1;
            }
        }
    }
}